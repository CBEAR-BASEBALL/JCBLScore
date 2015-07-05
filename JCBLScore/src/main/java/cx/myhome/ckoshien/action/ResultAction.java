package cx.myhome.ckoshien.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import cx.myhome.ckoshien.dto.BattingResultDto;
import cx.myhome.ckoshien.dto.GameResultDto;
import cx.myhome.ckoshien.dto.PitchingResultDto;
import cx.myhome.ckoshien.entity.Game;
import cx.myhome.ckoshien.entity.League;
import cx.myhome.ckoshien.form.ResultForm;
import cx.myhome.ckoshien.logic.ResultLogic;
import cx.myhome.ckoshien.service.BattingSumService;
import cx.myhome.ckoshien.service.GameService;
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
@Resource
public GameService gameService;
public int length;
public List<GameResultDto> opponentList;
public List<GameResultDto> resultList2;
public List<BattingResultDto> averageTop10;
public List<BattingResultDto> homerunTop10;
public ResultLogic resultLogic;
public List<BattingResultDto> rbiTop10;
public List<BattingResultDto> hitTop10;
public List<PitchingResultDto> eraTop10;
public List<PitchingResultDto> winTop10;
public List<PitchingResultDto> saveTop10;
public List<PitchingResultDto> strikeOutTop10;
public List<BattingResultDto> obpTop10;
public List<BattingResultDto> twobaseTop10;
public List<BattingResultDto> slgTop10;
public List<BattingResultDto> fourBallTop10;
public List<BattingResultDto> opsTop10;
public List<BattingResultDto> nsoTop10;
public Game game;
public int regAtBats;
public int regAtPitch;
public List<Game> gameList;


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
		battingResultList=battingSumService.findByPeriod(league.beginDate, league.endDate,"average desc");
		pitchingResultList=pitchingService.findByPeriod(league.beginDate, league.endDate,"era asc");
		gameList=gameService.findByPeriod(league.beginDate, league.endDate);
		regAtBats=0;
		regAtPitch=0;
		if (gameList.size()>0){
			game=gameList.get(0);
			//最新の試合日付がリーグ戦期間内だった場合
			if(game.gameDate.compareTo(league.beginDate)>=0&&game.gameDate.compareTo(league.endDate)<=0){
				Calendar cal=Calendar.getInstance();
				Calendar cal2=Calendar.getInstance();
				cal.setTime(game.gameDate);
				cal2.setTime(league.beginDate);
				int gameMonth = cal.get(Calendar.MONTH)+1;
				int leagueMonth = cal2.get(Calendar.MONTH)+1;
				regAtBats=(gameMonth-leagueMonth+1)*7;
				regAtPitch=(gameMonth-leagueMonth+1)*3;
			}else{
			//リーグ戦期間外だった場合
				Calendar cal=Calendar.getInstance();
				Calendar cal2=Calendar.getInstance();
				cal.setTime(league.beginDate);
				cal2.setTime(league.endDate);
				int beginMonth = cal.get(Calendar.MONTH)+1;
				int endMonth = cal2.get(Calendar.MONTH)+1;
				regAtBats=(endMonth-beginMonth+1)*7;
				regAtPitch=(endMonth-beginMonth+1)*3;
			}
		}
		resultLogic=new ResultLogic();
		//打率TOP10
		averageTop10=resultLogic.returnAverageTop10(battingResultList,regAtBats);
		//HRTOP10
		homerunTop10=battingSumService.findByPeriod(league.beginDate, league.endDate,"homerun desc");
		homerunTop10=resultLogic.returnHomerunTop10(homerunTop10);
		//打点TOP10
		rbiTop10=battingSumService.findByPeriod(league.beginDate, league.endDate,"rbi desc");
		rbiTop10=resultLogic.returnRbiTop10(rbiTop10);
		//安打数TOP10
		hitTop10=battingSumService.findByPeriod(league.beginDate, league.endDate,"hit desc");
		hitTop10=resultLogic.returnHitTop10(hitTop10);
		//防御率TOP10
		eraTop10=resultLogic.returnEraTop10(pitchingResultList,regAtPitch);
		//勝利数TOP10
		winTop10=pitchingService.findByPeriod(league.beginDate, league.endDate,"win desc");
		winTop10=resultLogic.returnWinTop10(winTop10);
		//セーブTOP10
		saveTop10=pitchingService.findByPeriod(league.beginDate, league.endDate,"save desc,era asc");
		saveTop10=resultLogic.returnSaveTop10(saveTop10);
		//奪三振TOP10
		strikeOutTop10=pitchingService.findByPeriod(league.beginDate, league.endDate,"strike_out desc");
		strikeOutTop10=resultLogic.returnStrikeOutTop10(strikeOutTop10);
		//出塁率TOP10
		obpTop10=battingSumService.findByPeriod(league.beginDate, league.endDate,"obp desc");
		obpTop10=resultLogic.returnObpTop10(obpTop10,regAtBats);
		//二塁打TOP10
		twobaseTop10=battingSumService.findByPeriod(league.beginDate, league.endDate,"twobase desc");
		twobaseTop10=resultLogic.returnTwobaseTop10(twobaseTop10);
		//長打率TOP10
		slgTop10=battingSumService.findByPeriod(league.beginDate, league.endDate,"slg desc");
		slgTop10=resultLogic.returnSlgTop10(slgTop10,regAtBats);
		//最多四球TOP10
		fourBallTop10=battingSumService.findByPeriod(league.beginDate, league.endDate,"four_ball desc");
		fourBallTop10=resultLogic.returnFourBallTop10(fourBallTop10);
		//OPS TOP10
		opsTop10=battingSumService.findByPeriod(league.beginDate, league.endDate,"ops desc");
		opsTop10=resultLogic.returnOpsTop10(opsTop10,regAtBats);
		//最多四球TOP10
		nsoTop10=battingSumService.findByPeriod(league.beginDate, league.endDate,"not_strike_out asc");
		nsoTop10=resultLogic.returnNsoTop10(nsoTop10,regAtBats);
		return "stats.jsp";
	}
}
