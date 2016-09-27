package cx.myhome.ckoshien.rest;

import java.util.HashMap;

import org.seasar.framework.util.ResourceUtil;

public class SlackLogger {

	public void info(Object message) {
		RestClient client = new RestClient();
		String uri=ResourceUtil.getProperties("config.properties").getProperty("SLACK_URI");
		HashMap<String, String> header= new HashMap<String, String>();
		SlackDto entity=new SlackDto();
		entity.setText(message.toString());
		entity.setUsername("JCBLScoreBot");
		String json=client.sendRequest(uri, "POST", entity, String.class,header);
		System.out.println(json);
	}

}
