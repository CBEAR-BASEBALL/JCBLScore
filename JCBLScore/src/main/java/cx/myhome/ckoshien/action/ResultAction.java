package cx.myhome.ckoshien.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import cx.myhome.ckoshien.dto.BattingResultDto;
import cx.myhome.ckoshien.dto.GameResultDto;
import cx.myhome.ckoshien.dto.PitchingResultDto;
import cx.myhome.ckoshien.entity.League;
import cx.myhome.ckoshien.form.ResultForm;
import cx.myhome.ckoshien.logic.ResultLogic;
import cx.myhome.ckoshien.service.BattingSumService;
import cx.myhome.ckoshien.service.LeagueService;
import cx.myhome.ckoshien.service.PitchingService;
import cx.myhome.ckoshien.service.ResultService;
import cx.myhome.ckoshien.util.HomerunComparator;
import cx.myhome.ckoshien.util.RbiComparator;

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
public List<BattingResultDto> averageTop10;
public List<BattingResultDto> homerunTop10;
public ResultLogic resultLogic;
public List<BattingResultDto> rbiTop10;


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
		System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
		resultList=resultService.findGameResult(Integer.parseInt(resultForm.id));
		resultList2=resultList;
		length=resultList.size();
		opponentList=resultService.findOpponentResult(Integer.parseInt(resultForm.id));
		battingResultList=battingSumService.findByPeriod(league.beginDate, league.endDate);
		pitchingResultList=pitchingService.findByPeriod(league.beginDate, league.endDate);
		resultLogic=new ResultLogic();
		//打率TOP10
		averageTop10=resultLogic.returnAverageTop10(battingResultList);
//		Java7のソートにバグがありexceptionが発生するのでコメントアウト
//		//HRTOP10
//		homerunTop10=new ArrayList<BattingResultDto>();
//		for(int i=0;i<battingResultList.size();i++){
//			homerunTop10.add(battingResultList.get(i));
//		}
//		Collections.sort(homerunTop10, new HomerunComparator());
//		Collections.reverse(homerunTop10);
//		homerunTop10=resultLogic.returnHomerunTop10(homerunTop10);
//		//打点TOP10
//		rbiTop10=new ArrayList<BattingResultDto>();
//		for(int i=0;i<battingResultList.size();i++){
//			rbiTop10.add(battingResultList.get(i));
//		}
//		Collections.sort(rbiTop10, new RbiComparator());
//		Collections.reverse(rbiTop10);
//		rbiTop10=resultLogic.returnRbiTop10(rbiTop10);
		return "stats.jsp";
	}
}
