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

import cx.myhome.ckoshien.rest.SlackLogger;

@Task
@CronTrigger(expression = "0 0 */4 * * ?")
public class KimetaroAccessTask {
	public List<String[]> csvData;
	private static Logger logger = Logger.getLogger("rootLogger");
	private SlackLogger slackLogger=new SlackLogger();

	public void doExecute(){
		logger.info("タスク開始");
		String ua = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36";
		String baseUrl="http://www.freeml.com";
		String targetUrl="http://www.freeml.com/kimetaro/jLx2PS";//TODO DBから取得
		String password="2017";//TODO DBから取得
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
				element=timeline.getElementById("attend_table_col6").child(1).child(0).child(0).child(i);
				try{
					row=new ArrayList<String>();
					for(int j=0;j<10;j++){
						row.add(element.child(j).child(0).text());
					}
				}catch (IndexOutOfBoundsException e) {

				}
				list.add(row);
			}
		}catch (IndexOutOfBoundsException e) {

		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
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
		File file = new File("../tomcat6.0/logs/jcbl/jLx2PS.csv");
		if(file.exists()){
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
			}
			for(int i=0;i<csvData.size();i++){
				String[] arrayStr=csvData.get(i);
				for(int j=0;j<arrayStr.length;j++){
					if(!list.get(i).get(j).equals(arrayStr[j])){
						//変更があった場合
						logger.info("決め太郎："+(i+1)+"行"+(j+1)+"列に変更がありました");
						slackLogger.info("決め太郎："+(i+1)+"行"+(j+1)+"列に変更がありました");
					}
				}
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

