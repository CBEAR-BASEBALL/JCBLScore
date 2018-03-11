package cx.myhome.ckoshien.action.api.v1;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.util.ResponseUtil;

import cx.myhome.ckoshien.dto.PlayerDto;
import cx.myhome.ckoshien.dto.PositionDto;
import cx.myhome.ckoshien.dto.TeamBattingResultDto;
import cx.myhome.ckoshien.dto.TeamHistoryDto;
import cx.myhome.ckoshien.dto.TeamPitchingResultDto;
import cx.myhome.ckoshien.dto.api.AutoCompleteDto;
import cx.myhome.ckoshien.entity.League;
import cx.myhome.ckoshien.entity.Player;
import cx.myhome.ckoshien.form.PlayerForm;
import cx.myhome.ckoshien.service.BattingSumService;
import cx.myhome.ckoshien.service.LeagueService;
import cx.myhome.ckoshien.service.PitchingService;
import cx.myhome.ckoshien.service.PlayerService;
import cx.myhome.ckoshien.service.TeamHistoryService;
import cx.myhome.ckoshien.util.MemoryUtil;
import net.arnx.jsonic.JSON;

public class PlayerAction {
	@Resource
	protected PlayerService playerService;

	@Resource
	@ActionForm
	protected PlayerForm playerForm;

	@Resource
	protected PitchingService pitchingService;

	@Resource
	protected HttpServletRequest request;
	@Resource
	protected HttpServletResponse response;
	@Resource
	private BattingSumService battingSumService;

	private Player player;

	@Resource
	protected TeamHistoryService teamHistoryService;

	@Resource
	private LeagueService leagueService;
	private static Logger logger = Logger.getLogger("rootLogger");

	@Execute(validator=false)
	public String search(){
		List<PlayerDto> playerList=new ArrayList<PlayerDto>();
		List<AutoCompleteDto> acDtos=new ArrayList<AutoCompleteDto>();
		if(null!=playerForm.name){
			playerList = playerService.findByLikeName(playerForm.name);
			for(int i=0;i<playerList.size();i++){
				AutoCompleteDto acDto=new AutoCompleteDto();
				acDto.value=playerList.get(i).name+"("+playerList.get(i).teamName+")";
				acDto.label=playerList.get(i).name+"("+playerList.get(i).teamName+")";
				acDto.id=playerList.get(i).id;
				acDtos.add(acDto);
			}
		}
		ResponseUtil.write(JSON.encode(acDtos));
		return null;
	}

	@Execute(urlPattern="show/{id}",validator = false)
	public String show(){
		//アクセス制限
		try {
			InetAddress ia=InetAddress.getByName(request.getRemoteAddr());
		    System.out.println(ia.getHostName());
		    if(!ia.getHostName().substring(ia.getHostName().length()-3).equals(".jp")
		    		&& !request.getRemoteAddr().equals("0:0:0:0:0:0:0:1")
		    		&& !request.getRemoteAddr().startsWith("192.168")
		    		&& !ia.getHostName().substring(ia.getHostName().length()-4).equals(".net")
		    		&& !ia.getHostName().equals("127.0.0.1")
		    	){
//		    	//logger.info("ホスト名で遮断:"+ia.getHostName()+":"+request.getRemotePort());
//		    	//response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		    	try {
					response.sendError(404, "許可されていないドメインです");
				} catch (IOException e) {
					logger.error(e);
				}
		    	return null;
		    }
				logger.info(ia.getHostName()+":"+request.getRemotePort());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		long t1=System.currentTimeMillis();
		//player=playerService.findById(Integer.parseInt(playerForm.id));
		List<TeamBattingResultDto> playerBattingResultList = battingSumService.findPBRById(Integer.parseInt(playerForm.id));
		//タブ用の成績があるシーズンリスト(PersonalBattingResultbyLeagueId)
		List<TeamBattingResultDto> personalBattingResultList = battingSumService.findPBRLById(Integer.parseInt(playerForm.id));
		List<TeamBattingResultDto> personalBattingResultGroupByList = battingSumService.findPBRGOById(Integer.parseInt(playerForm.id));
		List<TeamPitchingResultDto> pprList = pitchingService.findPPRById(Integer.parseInt(playerForm.id));
		List<TeamPitchingResultDto> pprgoList = pitchingService.findPPRGOById(Integer.parseInt(playerForm.id));
		List<TeamBattingResultDto> tbrDtos = battingSumService.findPBRDById(Integer.parseInt(playerForm.id));
		//タブ用の投球成績明細
		List<TeamPitchingResultDto> tprDtos = pitchingService.findPPRDById(Integer.parseInt(playerForm.id));
		//List<TeamPitchingResultDto> pprlList = pitchingService.findPPRLById(Integer.parseInt(playerForm.id));
		//List<League> leagueList = leagueService.findAllOrderByIdExceptTotal();
		List<PositionDto> posDtos = battingSumService.countDiffensePositionById(Integer.parseInt(playerForm.id));
		//List<TeamHistoryDto> teamHistoryDtoList = teamHistoryService.findTeamHistoryWithSeason(Integer.parseInt(playerForm.id));
		player=playerService.findById(Integer.parseInt(playerForm.id));
		//List<TeamBattingResultDto> pbrList = battingSumService.findPBRById(Integer.parseInt(playerForm.id));
		//pprList=pitchingService.findPPRById(Integer.parseInt(playerForm.id));
		//pprgoList=pitchingService.findPPRGOById(Integer.parseInt(playerForm.id));
		//tbrDtos=battingSumService.findPBRDById(Integer.parseInt(playerForm.id));
		//タブ用の投球成績明細
		//tprDtos=pitchingService.findPPRDById(Integer.parseInt(playerForm.id));
		//pprlList=pitchingService.findPPRLById(Integer.parseInt(playerForm.id));
		//leagueList=leagueService.findAllOrderByIdExceptTotal();
		//API用にデータ加工
		HashMap<Integer,List<TeamBattingResultDto>> battingSeasonMap=new HashMap<Integer,List<TeamBattingResultDto>>();
		List<TeamBattingResultDto> tbrDtoTmp=new ArrayList<TeamBattingResultDto>();
		for(int i=0;i<tbrDtos.size();i++){
			tbrDtoTmp=battingSeasonMap.get(tbrDtos.get(i).leagueId);
			if(tbrDtoTmp!=null){
				if(tbrDtos.get(i).playerId!=null && tbrDtos.get(i).leagueId!=null
						&& (tbrDtos.get(i).gameNumber!=null || tbrDtos.get(i).gameDate==null)){
					tbrDtoTmp.add(tbrDtos.get(i));
				}
			}else{
				tbrDtoTmp=new ArrayList<TeamBattingResultDto>();
				tbrDtoTmp.add(tbrDtos.get(i));
			}
			battingSeasonMap.put(tbrDtos.get(i).leagueId, tbrDtoTmp);
		}
		HashMap<Integer,List<TeamPitchingResultDto>> pitchingSeasonMap=new HashMap<Integer,List<TeamPitchingResultDto>>();
		List<TeamPitchingResultDto> tprDtoTmp=new ArrayList<TeamPitchingResultDto>();
		for(int i=0;i<tprDtos.size();i++){
			tprDtoTmp=pitchingSeasonMap.get(tprDtos.get(i).leagueId);
			if(tprDtoTmp!=null){
				if(tprDtos.get(i).playerId!=null && tprDtos.get(i).leagueId!=null
						&& (tprDtos.get(i).gameNumber!=null || tprDtos.get(i).gameDate==null)){
					tprDtoTmp.add(tprDtos.get(i));
				}
			}else{
				tprDtoTmp=new ArrayList<TeamPitchingResultDto>();
				tprDtoTmp.add(tprDtos.get(i));
			}
			pitchingSeasonMap.put(tprDtos.get(i).leagueId, tprDtoTmp);
		}



		HashMap<String, Object>map=new HashMap<String, Object>();
		map.put("personalBattingResultList", personalBattingResultList);
		map.put("personalBattingResultGroupByOpponent" , personalBattingResultGroupByList);
		map.put("personalPitchingResultList", pprList);
		map.put("playerBattingResultList", playerBattingResultList);
		map.put("tbrDtos", battingSeasonMap);
		map.put("tprDtos", pitchingSeasonMap);
		map.put("pprgoList", pprgoList);
		map.put("posDtos", posDtos);
		String json=JSON.encode(map);
		ResponseUtil.write(json);
		long t2=System.currentTimeMillis();
		logger.info("/api/player/show/"+playerForm.id+" "+player.name);
		MemoryUtil.viewMemoryInfo();
		logger.info(t2-t1+"ms");
		return null;
	}

}
