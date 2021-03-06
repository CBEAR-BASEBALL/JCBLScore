package cx.myhome.ckoshien.task;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.seasar.chronos.core.annotation.task.Task;
import org.seasar.chronos.core.annotation.trigger.CronTrigger;
import org.seasar.chronos.core.annotation.trigger.NonDelayTrigger;
import org.seasar.framework.util.ResourceUtil;

import cx.myhome.ckoshien.rest.PushbulletClient;
import cx.myhome.ckoshien.rest.SlackLogger;

@Task
@CronTrigger(expression = "0 30 */3 * * ?")
//@NonDelayTrigger
public class EzBBSTask {
	public static String filepath="../tomcat6.0/logs/jcbl/ezbbs.txt";
	private static BufferedReader br;
	private static Logger logger = Logger.getLogger("rootLogger");
	private SlackLogger slackLogger=new SlackLogger();
	private PushbulletClient bullet=new PushbulletClient(ResourceUtil.getProperties("config.properties").getProperty("PUSHBULLET_TOKEN"));

	public void doExecute() {
		logger.info("タスク開始");
		InetAddress ia = null;
		try {
			ia = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		String uri = "http://www3.ezbbs.net/36/jcbl/";
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(uri);

		try {
			HttpResponse response = client.execute(request);
			String dateStr=response.getFirstHeader("Last-Modified").toString();
			dateStr=dateStr.replaceAll("Last-Modified: ", "");
			dateStr=dateStr.replaceAll(" GMT", "");
			SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss", Locale.ENGLISH);
			sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
			Date date = sdf.parse(dateStr);
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy年MM月dd日 HH時mm分");
			System.out.println(sdf2.format(date));
			File file = new File(filepath);
			String line = null;
			if(file.exists()){
				br = new BufferedReader(new FileReader(filepath));
				try{
					line = br.readLine();
				} finally {
			        br.close();
		        }
				Date date2=sdf2.parse(line);
				if(date2.compareTo(date)==-1){
					//更新されていたときファイルを更新
					try {
						BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"Shift-JIS"));
						bw.write(sdf2.format(date));
						bw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}

					logger.info("掲示板が更新されました");

					//slackに通知
					slackLogger.info(""+sdf3.format(date)+"に掲示板が更新されました");
					if(ia.getHostAddress().equals(ResourceUtil.getProperties("config.properties").getProperty("SERVER_IP"))){
						//pushbullet通知
						bullet.sendPush("note", "JCBLスコア管理システム", ""+sdf3.format(date)+"に掲示板が更新されました",null, null, null, null, null, null, null, "jcbl", null, null);
					}else{
						logger.info("開発機");
					}

				}
			}else{
				//ファイルがないとき
				try {
					BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"Shift-JIS"));
					bw.write(sdf2.format(date));
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("タスク終了");
	}
}
