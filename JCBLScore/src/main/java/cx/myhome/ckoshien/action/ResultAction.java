package cx.myhome.ckoshien.action;

import java.util.List;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import cx.myhome.ckoshien.dto.BattingResultDto;
import cx.myhome.ckoshien.dto.GameResultDto;
import cx.myhome.ckoshien.dto.PitchingResultDto;
import cx.myhome.ckoshien.entity.League;
import cx.myhome.ckoshien.form.ResultForm;
import cx.myhome.ckoshien.service.BattingSumService;
import cx.myhome.ckoshien.service.LeagueService;
import cx.myhome.ckoshien.service.PitchingService;
import cx.myhome.ckoshien.service.ResultService;

public class ResultAction {

@Resource
public LeagueService leagueService;
public List<League> leagueList;
@Resource
@ActionForm
public ResultForm resultForm;
public League league;
@Resource
public BattingSumService battingSumService;
public List<BattingResultDto> battingResultList;
@Resource
public PitchingService pitchingService;
public List<PitchingResultDto> pitchingResultList;
public List<GameResultDto> resultList;
@Resource
public ResultService resultService;
public int length;
public List<GameResultDto> opponentList;
public List<GameResultDto> resultList2;


	@Execute(validator = false)
	public String index(){
		leagueList=leagueService.findAllOrderById();
		return "index.jsp";
	}

	@Execute(urlPattern="season/{id}",validator = false)
	public String season(){
		try{
			league=leagueService.findById(Integer.parseInt(resultForm.id));
		}catch(NumberFormatException e){
			return "index&redirect=true";
		}

		resultList=resultService.findGameResult(Integer.parseInt(resultForm.id));
		resultList2=resultList;
		length=resultList.size();
		opponentList=resultService.findOpponentResult(Integer.parseInt(resultForm.id));
		battingResultList=battingSumService.findByPeriod(league.beginDate, league.endDate);
		pitchingResultList=pitchingService.findByPeriod(league.beginDate, league.endDate);
		return "stats.jsp";
	}
}
