package cx.myhome.ckoshien.rest;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.seasar.framework.util.ResourceUtil;

public class PushbulletLogger {
	static Logger logger = Logger.getLogger("rootLogger");

	public void info(Object message) {
		RestClient client = new RestClient();
		String uri=ResourceUtil.getProperties("config.properties").getProperty("PUSHBULLET_URI");
		HashMap<String, String> header= new HashMap<String, String>();
		header.put("Access-Token", ResourceUtil.getProperties("config.properties").getProperty("PUSHBULLET_TOKEN"));
		header.put("Content-Type", "application/json");
		PushbulletDto entity=new PushbulletDto();
		entity.setBody(message.toString());
		entity.setTitle("JCBLスコア管理システムからのお知らせ");
		entity.setType("note");
		String json=client.sendRequest(uri, "POST", entity, String.class,header);
		logger.info(json);
	}
}
