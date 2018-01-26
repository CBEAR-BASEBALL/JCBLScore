package cx.myhome.ckoshien.action.api.v1;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.seasar.framework.util.ResourceUtil;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import cx.myhome.ckoshien.dto.PlayerDto;
import cx.myhome.ckoshien.dto.TeamBattingResultDto;
import cx.myhome.ckoshien.dto.TeamPitchingResultDto;
import cx.myhome.ckoshien.dto.api.MessageDto;
import cx.myhome.ckoshien.dto.api.ReplyMessageDto;
import cx.myhome.ckoshien.entity.Player;
import cx.myhome.ckoshien.form.LineBotForm;
import cx.myhome.ckoshien.rest.RestClient;
import cx.myhome.ckoshien.service.BattingSumService;
import cx.myhome.ckoshien.service.LeagueService;
import cx.myhome.ckoshien.service.PitchingService;
import cx.myhome.ckoshien.service.PlayerService;

public class LineBotAction {
	@Resource
	@ActionForm
	private LineBotForm lineBotForm;

	private static Logger logger = Logger.getLogger("rootLogger");
	@Resource
	protected HttpServletRequest request;
	@Resource
	protected HttpServletResponse response;
	@Resource
	protected LeagueService leagueService;
	@Resource
	protected PlayerService playerService;
	@Resource
	protected BattingSumService battingSumService;

	private String playerName;
	@Resource
	private PitchingService pitchingService;

	@Execute(validator=false)
	public String index(){
		try {
			InetAddress ia=InetAddress.getByName(request.getRemoteAddr());
			logger.info("[API]"+ia.getHostName()+":"+request.getRemotePort());
		} catch (UnknownHostException e) {
			logger.error("ERROR",e);
		}
		for(int i=0;i<lineBotForm.events.size();i++){
			if(i>10){
				//同時処理数10まで
				break;
			}
			if(null!=lineBotForm.events.get(i).message){
				logger.info(lineBotForm.events.get(i).message.text);
				logger.info("userId:"+lineBotForm.events.get(i).source.userId);
				logger.info("groupId:"+lineBotForm.events.get(i).source.groopId);
				logger.info("roomId:"+lineBotForm.events.get(i).source.roomId);
			}else{
				logger.info(lineBotForm.events.get(i).type);
				//logger.warn("messageがnullです。");
				break;
			}
			RestClient client = new RestClient();
			String uri=ResourceUtil.getProperties("config.properties").getProperty("LINE_URI");
			String channelAccessToken=ResourceUtil.getProperties("config.properties").getProperty("LINE_CHANNEL_ACCESS_TOKEN");
			HashMap<String, String> header= new HashMap<String, String>();
			header.put("Authorization", "Bearer "+channelAccessToken);
			ReplyMessageDto entity=new ReplyMessageDto();
			entity.setReplyToken(lineBotForm.events.get(i).replyToken);
			MessageDto message=new MessageDto();
			List<MessageDto> messageList=new ArrayList<MessageDto>();
			Integer leagueId = whichLeagueIsToday();
			message.text="";
			if(null!=lineBotForm.events){
				if(lineBotForm.events.get(i).message.type.equals("text")){
					if(lineBotForm.events.get(i).message.text.indexOf("の打撃成績")!=-1){
						playerName=lineBotForm.events.get(i).message.text.substring(0, lineBotForm.events.get(i).message.text.indexOf("の打撃成績"));
						message=searchPlayer(playerName,"打撃成績");
						message.type="text";
					}else if(lineBotForm.events.get(i).message.text.indexOf("の投球成績")!=-1){
						playerName=lineBotForm.events.get(i).message.text.substring(0, lineBotForm.events.get(i).message.text.indexOf("の投球成績"));
						message=searchPlayer(playerName,"投球成績");
						message.type="text";
					}else{
						message.text="認識できませんでした。\nコマンド例：ドワーフの打撃成績";
						message.type="text";
					}
				}else{
					//テキストタイプ以外
					message.text="申し訳ありません。\nテキスト以外は対応しておりません。";
					message.type="text";
				}
			}
			messageList.add(message);
			entity.setMessages(messageList);
			System.out.println(entity.toString());
			Object obj=client.sendRequest(uri, "POST", entity, Object.class,header);
			System.out.println(obj.toString());
		}

		return null;
	}

	public Integer whichLeagueIsToday(){
		Date today = new Date();
		return leagueService.findIdByDate(today).id;
	}

	public MessageDto searchPlayer(String playerName,String mode){
		logger.info(playerName);
		MessageDto message=new MessageDto();
		message.text="";
		List<Player> playerList=playerService.findByName(playerName);
		if(playerList.size()==0){
			List<PlayerDto> playerLikeList=playerService.findByLikeName(playerName);
			//候補を提示する
			message.text="もしかして(要完全一致)："+playerLikeList.size()+"件\n";
			for(int j=0;j<playerLikeList.size();j++){
				message.text=message.text+playerLikeList.get(j).name+"\n";
			}
			logger.info(message.text);
		}else if(playerList.size()==0){
			//見つかりませんでした
			message.text="見つかりませんでした";
		}else{
			if(mode.equals("打撃成績")){
				List<TeamBattingResultDto> teamBattingResultDtos = battingSumService.findPBRLById(playerList.get(0).id);
				if(teamBattingResultDtos.size()==0){
					message.text=message.text+"成績がありませんでした";
					return message;
				}
				for(int j=0;j<teamBattingResultDtos.size();j++){
					message.text=message.text+teamBattingResultDtos.get(j).title+":";
					message.text=message.text+"打率："+teamBattingResultDtos.get(j).average+"\n";
					message.text=message.text+teamBattingResultDtos.get(j).atBats+"打数";
					message.text=message.text+teamBattingResultDtos.get(j).hit+"安打";
					message.text=message.text+teamBattingResultDtos.get(j).homerun+"HR";
					message.text=message.text+teamBattingResultDtos.get(j).rbi+"打点\n";
					message.text=message.text+teamBattingResultDtos.get(j).fourBall+"四球";
					message.text=message.text+teamBattingResultDtos.get(j).strikeOut+"三振";
					message.text=message.text+teamBattingResultDtos.get(j).twobase+"二塁打\n";
				}
			}else if(mode.equals("投球成績")){
				List<TeamPitchingResultDto>teamPitchingResultDtos=pitchingService.findPPRLById(playerList.get(0).id);
				if(teamPitchingResultDtos.size()==0){
					message.text=message.text+"成績がありませんでした";
					return message;
				}
				for(int j=0;j<teamPitchingResultDtos.size();j++){
					message.text=message.text+teamPitchingResultDtos.get(j).title+":\n";
					message.text=message.text+"防御率："+teamPitchingResultDtos.get(j).era+"\n";
					message.text=message.text+teamPitchingResultDtos.get(j).gameCount+"登板";
					message.text=message.text+teamPitchingResultDtos.get(j).win+"勝";
					message.text=message.text+teamPitchingResultDtos.get(j).lose+"敗";
					message.text=message.text+teamPitchingResultDtos.get(j).save+"S\n";
					message.text=message.text+teamPitchingResultDtos.get(j).inning+"投球回";
					message.text=message.text+teamPitchingResultDtos.get(j).hit+"被安打";
					message.text=message.text+teamPitchingResultDtos.get(j).strikeOut+"奪三振\n";
					message.text=message.text+teamPitchingResultDtos.get(j).fourBall+"与四球";
					message.text=message.text+teamPitchingResultDtos.get(j).runs+"失点";
					message.text=message.text+teamPitchingResultDtos.get(j).complete+"完投";
					message.text=message.text+teamPitchingResultDtos.get(j).shutout+"完封\n";

				}
			}

		}
		return message;
	}


}
