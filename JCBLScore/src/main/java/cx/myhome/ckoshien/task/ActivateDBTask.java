package cx.myhome.ckoshien.task;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.seasar.chronos.core.annotation.task.Task;
import org.seasar.chronos.core.annotation.trigger.CronTrigger;
import org.seasar.chronos.core.annotation.trigger.NonDelayTrigger;

@Task
//@CronTrigger(expression = "0 0 * * * ?")
@NonDelayTrigger
public class ActivateDBTask {
	public void doExecute(){
		InetAddress ia = null;
		try {
			ia = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		System.out.println(ia.getHostAddress() );
		try {
			Document document = Jsoup.connect("http://localhost/JCBLScore/api/v1/result/season/36").get();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
