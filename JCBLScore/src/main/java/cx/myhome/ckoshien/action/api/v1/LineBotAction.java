package cx.myhome.ckoshien.action.api.v1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.seasar.framework.util.ResourceUtil;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import cx.myhome.ckoshien.dto.api.MessageDto;
import cx.myhome.ckoshien.dto.api.ReplyMessageDto;
import cx.myhome.ckoshien.form.LineBotForm;
import cx.myhome.ckoshien.rest.RestClient;

public class LineBotAction {
	@Resource
	@ActionForm
	private LineBotForm lineBotForm;

	@Execute(validator=false)
	public String index(){
		for(int i=0;i<lineBotForm.events.size();i++){
			if(i>10){
				//同時処理数10まで
				break;
			}
			RestClient client = new RestClient();
			String uri=ResourceUtil.getProperties("config.properties").getProperty("LINE_URI");
			String channelAccessToken=ResourceUtil.getProperties("config.properties").getProperty("LINE_CHANNEL_ACCESS_TOKEN");
			HashMap<String, String> header= new HashMap<String, String>();
			header.put("Authorization", "Bearer "+channelAccessToken);
			//header.put("Content-Type","application/json");
			ReplyMessageDto entity=new ReplyMessageDto();
			entity.setReplyToken(lineBotForm.events.get(i).replyToken);
			MessageDto message=new MessageDto();
			List<MessageDto> messageList=new ArrayList<MessageDto>();
			if(lineBotForm.events.get(i).message.type.equals("text")){
				message.text=lineBotForm.events.get(i).message.text;
				message.type="text";
			}else{
				message.text="テキスト以外は対応しておりません。";
				message.type="text";
			}
			messageList.add(message);
			entity.setMessages(messageList);
			System.out.println(entity.toString());
			Object obj=client.sendRequest(uri, "POST", entity, Object.class,header);
			System.out.println(obj.toString());
		}

		return null;
	}

}
