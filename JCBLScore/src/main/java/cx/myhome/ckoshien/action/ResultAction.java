package cx.myhome.ckoshien.action;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
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
public List<BattingResultDto> avgHRTop10;
public List<BattingResultDto> avgRBITop10;
public Game game;
public int regAtBats;
public int regAtPitch;
public int listSize;
public List<Game> gameList;
public double regGameCount;
public List<GameResultDto> tmpResultList;
@Resource
public HttpServletRequest request;
private static Logger logger = Logger.getLogger("rootLogger");



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
		try {
    		InetAddress ia=InetAddress.getByName(request.getRemoteAddr());
//    		System.out.println(ia.getHostName());
//    		if(!ia.getHostName().substring(ia.getHostName().length()-3).equals(".jp")
//    				&& !request.getRemoteAddr().equals("0:0:0:0:0:0:0:1")
//    				&& !request.getRemoteAddr().startsWith("192.168")){
//    			//logger.info("ホスト名で遮断:"+ia.getHostName()+":"+request.getRemotePort());
//    			//response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//    			try {
//					response.sendError(404, "許可されていないドメインです");
//				} catch (IOException e) {
//					logger.error(e);
//				}
//        		return null;
//    		}
			logger.info(ia.getHostName()+":"+request.getRemotePort());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		logger.info("result/season/"+Integer.parseInt(resultForm.id));
		System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
		opponentList=resultService.findOpponentResult(Integer.parseInt(resultForm.id));
		gameList=gameService.findByPeriod(league.beginDate, league.endDate);
		regAtBats=0;
		regAtPitch=0;
		regGameCount=0;
		Date today = new Date();
		if (gameList.size()>0){
			game=gameList.get(0);
			//最新の試合日付がリーグ戦期間内だった場合
			if(today.compareTo(league.beginDate)>=0&&today.compareTo(league.endDate)<=0){
				Calendar cal=Calendar.getInstance();
				Calendar cal2=Calendar.getInstance();
				cal.setTime(game.gameDate);
				cal2.setTime(league.beginDate);
				int gameMonth = cal.get(Calendar.MONTH)+1;
				int leagueMonth = cal2.get(Calendar.MONTH)+1;
				regAtBats=(gameMonth-leagueMonth)*7;
				regAtPitch=(gameMonth-leagueMonth)*3;
				//regGameCount=(gameMonth-leagueMonth)*4/3;
				regGameCount=1.0;

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
				regGameCount=(endMonth-beginMonth+1)*4/3;
				System.out.println("リーグ戦期間外");
			}
		}
		resultList=resultService.findGameResult(Integer.parseInt(resultForm.id),regGameCount);
		tmpResultList=resultService.findGameResultByPoints(Integer.parseInt(resultForm.id),regGameCount);
		for(int i=0;i<tmpResultList.size();i++){
			resultList.add(tmpResultList.get(i));
		}
		tmpResultList=new ArrayList<GameResultDto>();
		tmpResultList=resultService.findGameResultByJCBL(Integer.parseInt(resultForm.id));
		for(int i=0;i<tmpResultList.size();i++){
			resultList.add(tmpResultList.get(i));
		}
		resultList2=resultList;
		length=resultList.size();
		resultLogic=new ResultLogic();
		long t1=System.currentTimeMillis();
		//打率TOP10
		battingResultList=battingSumService.findByPeriod(league.beginDate, league.endDate,"average desc");
		averageTop10=resultLogic.returnAverageTop10(battingResultList,regAtBats);
		//HRTOP10
		//homerunTop10=battingSumService.findByPeriod(league.beginDate, league.endDate,"homerun desc,average desc");
		homerunTop10=new ArrayList<BattingResultDto>(battingResultList);
		for(int i=0;i<homerunTop10.size();i++){
			for(int j=0;j<homerunTop10.size();j++){
				if(homerunTop10.get(i).homerun>homerunTop10.get(j).homerun){
					BattingResultDto brd=homerunTop10.get(i);
					homerunTop10.set(i, homerunTop10.get(j));
					homerunTop10.set(j,brd);
				}
			}
		}
		homerunTop10=resultLogic.returnHomerunTop10(homerunTop10);
		//打点TOP10
		//rbiTop10=battingSumService.findByPeriod(league.beginDate, league.endDate,"rbi desc,average desc");
		rbiTop10=new ArrayList<BattingResultDto>(battingResultList);;
		for(int i=0;i<rbiTop10.size();i++){
			for(int j=0;j<rbiTop10.size();j++){
				if(rbiTop10.get(i).rbi>rbiTop10.get(j).rbi){
					BattingResultDto brd=rbiTop10.get(i);
					rbiTop10.set(i, rbiTop10.get(j));
					rbiTop10.set(j,brd);
				}
			}
		}
		rbiTop10=resultLogic.returnRbiTop10(rbiTop10);
		//安打数TOP10
		//hitTop10=battingSumService.findByPeriod(league.beginDate, league.endDate,"hit desc,average desc");
		hitTop10=new ArrayList<BattingResultDto>(battingResultList);
		for(int i=0;i<hitTop10.size();i++){
			for(int j=0;j<hitTop10.size();j++){
				if(hitTop10.get(i).hit>hitTop10.get(j).hit){
					BattingResultDto brd=hitTop10.get(i);
					hitTop10.set(i, hitTop10.get(j));
					hitTop10.set(j,brd);
				}
			}
		}
		hitTop10=resultLogic.returnHitTop10(hitTop10);
		//防御率TOP10
		pitchingResultList=pitchingService.findByPeriod(league.beginDate, league.endDate,"era asc");
		eraTop10=resultLogic.returnEraTop10(pitchingResultList,regAtPitch);
		//勝利数TOP10
		//winTop10=pitchingService.findByPeriod(league.beginDate, league.endDate,"win desc,era asc");
		winTop10=new ArrayList<PitchingResultDto>(pitchingResultList);
		for(int i=0;i<winTop10.size();i++){
			for(int j=0;j<winTop10.size();j++){
				if(winTop10.get(i).win>winTop10.get(j).win){
					PitchingResultDto prd=winTop10.get(i);
					winTop10.set(i, winTop10.get(j));
					winTop10.set(j,prd);
				}
			}
		}
		winTop10=resultLogic.returnWinTop10(winTop10);
		//セーブTOP10
		//saveTop10=pitchingService.findByPeriod(league.beginDate, league.endDate,"save desc,era asc");
		saveTop10=new ArrayList<PitchingResultDto>(pitchingResultList);
		for(int i=0;i<saveTop10.size();i++){
			for(int j=0;j<saveTop10.size();j++){
				if(saveTop10.get(i).save>saveTop10.get(j).save){
					PitchingResultDto prd=saveTop10.get(i);
					saveTop10.set(i, saveTop10.get(j));
					saveTop10.set(j,prd);
				}
			}
		}
		saveTop10=resultLogic.returnSaveTop10(saveTop10);
		//奪三振TOP10
		//strikeOutTop10=pitchingService.findByPeriod(league.beginDate, league.endDate,"strike_out desc");
		strikeOutTop10=new ArrayList<PitchingResultDto>(pitchingResultList);
		for(int i=0;i<strikeOutTop10.size();i++){
			for(int j=0;j<strikeOutTop10.size();j++){
				if(strikeOutTop10.get(i).strikeOut>strikeOutTop10.get(j).strikeOut){
					PitchingResultDto prd=strikeOutTop10.get(i);
					strikeOutTop10.set(i, strikeOutTop10.get(j));
					strikeOutTop10.set(j,prd);
				}
			}
		}
		strikeOutTop10=resultLogic.returnStrikeOutTop10(strikeOutTop10);
		//出塁率TOP10
		//obpTop10=battingSumService.findByPeriod(league.beginDate, league.endDate,"obp desc");
		obpTop10=new ArrayList<BattingResultDto>(battingResultList);
		for(int i=0;i<obpTop10.size();i++){
			for(int j=0;j<obpTop10.size();j++){
				if(obpTop10.get(i).obp>obpTop10.get(j).obp){
					BattingResultDto brd=obpTop10.get(i);
					obpTop10.set(i, obpTop10.get(j));
					obpTop10.set(j,brd);
				}
			}
		}
		obpTop10=resultLogic.returnObpTop10(obpTop10,regAtBats);
		//二塁打TOP10
		//twobaseTop10=battingSumService.findByPeriod(league.beginDate, league.endDate,"twobase desc");
		twobaseTop10=new ArrayList<BattingResultDto>(battingResultList);
		for(int i=0;i<twobaseTop10.size();i++){
			for(int j=0;j<twobaseTop10.size();j++){
				if(twobaseTop10.get(i).twobase>twobaseTop10.get(j).twobase){
					BattingResultDto brd=twobaseTop10.get(i);
					twobaseTop10.set(i, twobaseTop10.get(j));
					twobaseTop10.set(j,brd);
				}
			}
		}
		twobaseTop10=resultLogic.returnTwobaseTop10(twobaseTop10);
		//長打率TOP10
		//slgTop10=battingSumService.findByPeriod(league.beginDate, league.endDate,"slg desc");
		slgTop10=new ArrayList<BattingResultDto>(battingResultList);
		for(int i=0;i<slgTop10.size();i++){
			for(int j=0;j<slgTop10.size();j++){
				if(slgTop10.get(i).slg>slgTop10.get(j).slg){
					BattingResultDto brd=slgTop10.get(i);
					slgTop10.set(i, slgTop10.get(j));
					slgTop10.set(j,brd);
				}
			}
		}
		slgTop10=resultLogic.returnSlgTop10(slgTop10,regAtBats);
		//最多四球TOP10
		//fourBallTop10=battingSumService.findByPeriod(league.beginDate, league.endDate,"four_ball desc");
		fourBallTop10=new ArrayList<BattingResultDto>(battingResultList);
		for(int i=0;i<fourBallTop10.size();i++){
			for(int j=0;j<fourBallTop10.size();j++){
				if(fourBallTop10.get(i).fourBall>fourBallTop10.get(j).fourBall){
					BattingResultDto brd=fourBallTop10.get(i);
					fourBallTop10.set(i, fourBallTop10.get(j));
					fourBallTop10.set(j,brd);
				}
			}
		}
		fourBallTop10=resultLogic.returnFourBallTop10(fourBallTop10);
		//OPS TOP10
		//opsTop10=battingSumService.findByPeriod(league.beginDate, league.endDate,"ops desc");
		opsTop10=new ArrayList<BattingResultDto>(battingResultList);
		for(int i=0;i<opsTop10.size();i++){
			for(int j=0;j<opsTop10.size();j++){
				if(opsTop10.get(i).ops>opsTop10.get(j).ops){
					BattingResultDto brd=opsTop10.get(i);
					opsTop10.set(i, opsTop10.get(j));
					opsTop10.set(j,brd);
				}
			}
		}
		opsTop10=resultLogic.returnOpsTop10(opsTop10,regAtBats);
		//三振率TOP10
		//nsoTop10=battingSumService.findByPeriod(league.beginDate, league.endDate,"not_strike_out desc");
		nsoTop10=new ArrayList<BattingResultDto>(battingResultList);
		for(int i=0;i<nsoTop10.size();i++){
			for(int j=0;j<nsoTop10.size();j++){
				if(nsoTop10.get(i).notStrikeOut>nsoTop10.get(j).notStrikeOut){
					BattingResultDto brd=nsoTop10.get(i);
					nsoTop10.set(i, nsoTop10.get(j));
					nsoTop10.set(j,brd);
				}
			}
		}
		nsoTop10=resultLogic.returnNsoTop10(nsoTop10,regAtBats);
		//本塁打率TOP10
		//avgHRTop10=battingSumService.findByPeriod(league.beginDate, league.endDate,"avg_homerun asc");
		avgHRTop10=new ArrayList<BattingResultDto>(battingResultList);
		for(int i=0;i<avgHRTop10.size();i++){
			for(int j=0;j<avgHRTop10.size();j++){
				if(avgHRTop10.get(i).avgHomerun>avgHRTop10.get(j).avgHomerun){
					BattingResultDto brd=avgHRTop10.get(i);
					avgHRTop10.set(i, avgHRTop10.get(j));
					avgHRTop10.set(j,brd);
				}
			}
		}
		avgHRTop10=resultLogic.returnAvgHRTop10(avgHRTop10,regAtBats);
		//打点率
		//avgRBITop10=battingSumService.findByPeriod(league.beginDate, league.endDate,"avg_rbi desc");
		avgRBITop10=new ArrayList<BattingResultDto>(battingResultList);
		for(int i=0;i<avgRBITop10.size();i++){
			for(int j=0;j<avgRBITop10.size();j++){
				if(avgRBITop10.get(i).avgRbi>avgRBITop10.get(j).avgRbi){
					BattingResultDto brd=avgRBITop10.get(i);
					avgRBITop10.set(i, avgRBITop10.get(j));
					avgRBITop10.set(j,brd);
				}
			}
		}
		avgRBITop10=resultLogic.returnAvgRBITop10(avgRBITop10,regAtBats);
		long t2=System.currentTimeMillis();
		logger.info(t2-t1+"ms");
		//ノンタイトルの行数を決定
		if(twobaseTop10.size()>=fourBallTop10.size()){
			listSize=twobaseTop10.size();
		}else{
			listSize=fourBallTop10.size();
		}
		if(listSize<=10){
			listSize=10;
		}
		return "stats.jsp";
	}
}
