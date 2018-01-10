package cx.myhome.ckoshien.task;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.sf.orangesignal.csv.Csv;
import jp.sf.orangesignal.csv.CsvConfig;
import jp.sf.orangesignal.csv.handlers.StringArrayListHandler;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.seasar.chronos.core.annotation.task.Task;
import org.seasar.chronos.core.annotation.trigger.CronTrigger;
import org.seasar.chronos.core.annotation.trigger.NonDelayTrigger;
import org.seasar.framework.util.ResourceUtil;

import cx.myhome.ckoshien.rest.PushbulletClient;
import cx.myhome.ckoshien.rest.SlackLogger;

@Task
//@CronTrigger(expression = "0 0 */4 * * ?")
@NonDelayTrigger
public class KimetaroAccessTask {
	public List<String[]> csvData;
	private static Logger logger = Logger.getLogger("rootLogger");
	private SlackLogger slackLogger=new SlackLogger();
	private PushbulletClient bullet=new PushbulletClient(ResourceUtil.getProperties("config.properties").getProperty("PUSHBULLET_TOKEN"));
	private Element name;
	private boolean errFlg;

	public void doExecute(){
		logger.info("タスク開始");
		String ua = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36";
		String baseUrl="https://www.freeml.com";
		String targetUrl=ResourceUtil.getProperties("config.properties").getProperty("KIMETARO_URL");
		String password=ResourceUtil.getProperties("config.properties").getProperty("KIMETARO_PASS");//TODO DBから取得
		List<List<String>> list=new ArrayList<List<String>>();
		try {
			Response res1 = Jsoup.connect(targetUrl)
					.userAgent(ua)
					.execute();
			Elements elements = res1
					.parse()
					.getElementsByTag("input");
			Elements form = res1
					.parse()
					.getElementsByTag("form");
			String action=form.get(0).attr("action");

			Map<String, String> hiddens = new HashMap<String, String>();
			for (Element el : elements) {
				if (el.attr("type").equals("hidden")
						&& el.attr("name")!=null) {
					hiddens.put(el.attr("name"), el.attr("value"));
				}
			}
			hiddens.put("view_password",password);
			Response res2 = Jsoup.connect(baseUrl+action)
					.userAgent(ua)
					.cookies(res1.cookies())
					.data(hiddens)
					.followRedirects(false)
					.method(Method.POST)
					.execute();
			Document timeline = Jsoup.connect(targetUrl)
					.userAgent(ua)
					.cookies(res1.cookies())
					.get();
			Element element = null;
			List<String> row = null;
			for(int i=2;i<42;i++){
				try{
					element=timeline.getElementById("attend_table_col6").child(1).child(0).child(0).child(i);
					name=timeline.getElementById("attend_table_col6").child(0).child(0).child(0);

					row=new ArrayList<String>();
					for(int j=0;j<10;j++){
						row.add(element.child(j).child(0).text());
					}
				}catch (IndexOutOfBoundsException e) {
					break;
				}
				list.add(row);
			}
		}catch (IndexOutOfBoundsException e) {
			logger.error("ERROR", e);
		}catch (IOException e) {
			logger.error("ERROR", e);
			errFlg=true;
		}catch (Exception e){
			logger.error("ERROR", e);
			errFlg=true;
		}
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<list.size();i++){
			for(int j=0;j<list.get(i).size();j++){
				sb.append(list.get(i).get(j));
				if(j==list.get(i).size()-1){
        			sb.append("\n");
        		}else{
        			sb.append(",");
        		}
			}
		}
		File file = new File("../tomcat6.0/logs/jcbl/2gDjrP.csv");
		if(file.exists() && !errFlg){
			CsvConfig cfg = new CsvConfig();
			cfg.setQuoteDisabled(false);// デフォルトでは無効となっている囲み文字を有効にします。
			cfg.setEscapeDisabled(false);// デフォルトでは無効となっているエスケープ文字を有効にします。
			cfg.setIgnoreLeadingWhitespaces(true);// 項目値前のホワイトスペースを除去します。
			cfg.setIgnoreTrailingWhitespaces(true);// 項目値後のホワイトスペースを除去します。
			cfg.setIgnoreEmptyLines(true);// 空行を無視するようにします。
			try {
				csvData = Csv.load(file,"MS932", cfg, new StringArrayListHandler());
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(csvData.size()!=list.size()){
				logger.info("決め太郎：新規スケジュールが入力されました");
				slackLogger.info("決め太郎：新規スケジュールが入力されました");
				bullet.sendPush("note", "JCBLスコア管理システム", "決め太郎：新規スケジュールが入力されました",null, null, null, null, null, null, null, "jcbl", null, null);
			}
			StringBuilder modifyStr=new StringBuilder();
			for(int i=0;i<csvData.size();i++){
				String[] arrayStr=csvData.get(i);
				for(int j=0;j<arrayStr.length;j++){
					if(!list.get(i).get(j).equals(arrayStr[j])){
						//変更があった場合
						modifyStr.append("決め太郎："+name.child(i+1).child(0).child(0).text()+"の"+(j+1)+"列が"+arrayStr[j]+"から"+list.get(i).get(j)+"に変更されました\n");
//						logger.info("決め太郎："+name.child(i+1).child(0).child(0).text()+"の"+(j+1)+"列に変更がありました");
//						slackLogger.info("決め太郎："+name.child(i+1).child(0).child(0).text()+"の"+(j+1)+"列に変更がありました");
//						bullet.sendPush("note", "JCBLスコア管理システム", "決め太郎："+name.child(i+1).child(0).child(0).text()+"の"+(j+1)+"列が"+arrayStr[j]+"から"+list.get(i).get(j)+"に変更されました",null, null, null, null, null, null, null, "jcbl", null, null);
					}
				}
			}
			if(modifyStr.length()>0){
				logger.info(modifyStr);
				slackLogger.info(modifyStr);
				bullet.sendPush("note", "JCBLスコア管理システム",new String(modifyStr),null, null, null, null, null, null, null, "jcbl", null, null);
			}


		}
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"Shift-JIS"));
			bw.write(new String(sb));
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("タスク終了");
	}

}

