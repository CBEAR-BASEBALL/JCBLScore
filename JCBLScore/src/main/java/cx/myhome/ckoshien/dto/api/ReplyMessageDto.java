package cx.myhome.ckoshien.dto.api;

import java.util.List;

public class ReplyMessageDto {
	private String replyToken;
	private List<MessageDto> messages;
	public String getReplyToken() {
		return replyToken;
	}
	public void setReplyToken(String replyToken) {
		this.replyToken = replyToken;
	}
	public List<MessageDto> getMessages() {
		return messages;
	}
	public void setMessages(List<MessageDto> messages) {
		this.messages = messages;
	}



}
