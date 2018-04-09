package cx.myhome.ckoshien.action.api.v1;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.arnx.jsonic.JSON;

import org.apache.log4j.Logger;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.util.ResponseUtil;

import cx.myhome.ckoshien.dto.BattingResultDto;
import cx.myhome.ckoshien.dto.GameResultDto;
import cx.myhome.ckoshien.dto.PitchingResultDto;
import cx.myhome.ckoshien.dto.api.NonTitleDto;
import cx.myhome.ckoshien.dto.api.ResultApiDto;
import cx.myhome.ckoshien.entity.Game;
import cx.myhome.ckoshien.entity.League;
import cx.myhome.ckoshien.form.ResultForm;
import cx.myhome.ckoshien.logic.ResultLogic;
import cx.myhome.ckoshien.service.BattingSumService;
import cx.myhome.ckoshien.service.GameService;
import cx.myhome.ckoshien.service.LeagueService;
import cx.myhome.ckoshien.service.PitchingService;
import cx.myhome.ckoshien.service.ResultService;
import cx.myhome.ckoshien.util.MemoryUtil;

public class ResultAction {

@Resource
protected LeagueService leagueService;
public List<League> leagueList;
@Resource
@ActionForm
protected ResultForm resultForm;
public League league;
@Resource
protected BattingSumService battingSumService;
public List<BattingResultDto> battingResultList;
@Resource
protected PitchingService pitchingService;
public List<PitchingResultDto> pitchingResultList;
public List<GameResultDto> resultList;
@Resource
protected ResultService resultService;
@Resource
protected GameService gameService;
public int length;
public List<GameResultDto> opponentList;
public List<GameResultDto> resultList2;
public List<BattingResultDto> averageTop10;
public List<BattingResultDto> homerunTop10;
//public ResultLogic resultLogic;
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

@Resource
protected HttpServletRequest request;
@Resource
protected HttpServletResponse response;
public Integer totalLeagueId;
private static Logger logger = Logger.getLogger("rootLogger");



	@Execute(validator = false)
	public String index(){
		leagueList=leagueService.findAllOrderById();
		return "index.jsp";
	}

	@Execute(urlPattern="season/{id}",validator = false)
	public String season(){
		long t0=System.currentTimeMillis();
		//アクセス制限
		try {
    		InetAddress ia=InetAddress.getByName(request.getRemoteAddr());
//    		System.out.println(ia.getHostName());
    		if(!ia.getHostName().substring(ia.getHostName().length()-3).equals(".jp")
    				&& !request.getRemoteAddr().equals("0:0:0:0:0:0:0:1")
    				&& !request.getRemoteAddr().startsWith("192.168")
    				&& !ia.getHostName().substring(ia.getHostName().length()-4).equals(".net")
    				&& !ia.getHostName().equals("127.0.0.1")
    				&& !ia.getHostName().equals(request.getRemoteAddr())
    			){
    			logger.info("遮断:"+ia.getHostName()+":"+request.getRemotePort());
//    			//response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    			try {
					response.sendError(404, "jp、netドメインのみ許可しています。"+ia.getHostName());
				} catch (IOException e) {
					logger.error(e);
				}
        		return null;
    		}
			logger.info(ia.getHostName()+":"+request.getRemotePort());
		} catch (Exception e1) {
			logger.warn(e1);
		}
		long t1=System.currentTimeMillis();
		logger.info("lookup:"+(t1-t0));
		try{
			league=leagueService.findById(Integer.parseInt(resultForm.id));
		}catch(NumberFormatException e){
			return "index&redirect=true";
		}
		logger.info("[API]result/season/"+Integer.parseInt(resultForm.id));
		//System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
		totalLeagueId = leagueService.findTotal().get(0).id;
		opponentList=resultService.findOpponentResult(Integer.parseInt(resultForm.id));
		gameList=gameService.findByPeriod(league.beginDate, league.endDate);
		regAtBats=0;
		regAtPitch=0;
		double regGameCount=0;
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
		List<GameResultDto> tmpResultList = resultService.findGameResultByPoints(Integer.parseInt(resultForm.id),regGameCount);
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
		ResultLogic resultLogic=new ResultLogic();
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
		eraTop10=new ArrayList<PitchingResultDto>();
		pitchingResultList=pitchingService.findByPeriod(league.beginDate, league.endDate,"era asc");
		for(int i=0;i<pitchingResultList.size();i++){
			if(pitchingResultList.get(i).inning>=regAtPitch){
				eraTop10.add(pitchingResultList.get(i));
			}
		}
		//eraTop10=resultLogic.returnEraTop10(pitchingResultList,regAtPitch);
		eraTop10=resultLogic.returnEraTop10(eraTop10,regAtPitch);
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
				if(avgHRTop10.get(i).avgHomerun<avgHRTop10.get(j).avgHomerun){
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
		//ノンタイトルの行数を決定
		if(twobaseTop10.size()>=fourBallTop10.size()){
			listSize=twobaseTop10.size();
		}else{
			listSize=fourBallTop10.size();
		}
		if(listSize<=10){
			listSize=10;
		}
		long t3=System.currentTimeMillis();
		//API用にデータ加工
		List<NonTitleDto> nonTitleList=new ArrayList<NonTitleDto>();
		for(int i=0;i<listSize;i++){
			NonTitleDto ntd= new NonTitleDto();
			if(i<avgHRTop10.size()){
				ntd.avgHR=avgHRTop10.get(i).avgHomerun;
				ntd.avgHRName=avgHRTop10.get(i).name;
				ntd.avgHRRank=avgHRTop10.get(i).rank;
			}
			if(i<avgRBITop10.size()){
				ntd.avgRbi=avgRBITop10.get(i).avgRbi;
				ntd.avgRbiName=avgRBITop10.get(i).name;
				ntd.avgRbiRank=avgRBITop10.get(i).rank;
			}
			if(i<fourBallTop10.size()){
				ntd.fourball=fourBallTop10.get(i).fourBall;
				ntd.fourballName=fourBallTop10.get(i).name;
				ntd.fourballRank=fourBallTop10.get(i).rank;
			}
			if(i<obpTop10.size()){
				ntd.obp=obpTop10.get(i).obp;
				ntd.obpName=obpTop10.get(i).name;
				ntd.obpRank=obpTop10.get(i).rank;
			}
			if(i<opsTop10.size()){
				ntd.ops=opsTop10.get(i).ops;
				ntd.opsName=opsTop10.get(i).name;
				ntd.opsRank=opsTop10.get(i).rank;
			}
			if(i<slgTop10.size()){
				ntd.slg=slgTop10.get(i).slg;
				ntd.slgName=slgTop10.get(i).name;
				ntd.slgRank=slgTop10.get(i).rank;
			}
			if(i<nsoTop10.size()){
				ntd.strikeout=nsoTop10.get(i).notStrikeOut;
				ntd.strikeoutName=nsoTop10.get(i).name;
				ntd.strikeoutRank=nsoTop10.get(i).rank;
			}
			if(i<twobaseTop10.size()){
				ntd.twobase=twobaseTop10.get(i).twobase;
				ntd.twobaseName=twobaseTop10.get(i).name;
				ntd.twobaseRank=twobaseTop10.get(i).rank;
			}
			nonTitleList.add(ntd);
		}

		int recordCountTop=0;
		for(int i=0;i<resultList.size();i++){
			resultList.get(i).rank=i+1;
			if(i==0){
				recordCountTop=resultList.get(0).win-resultList.get(0).lose;
			}
			if(i>0){
				//貯金
				int recordCount = resultList.get(i).win-resultList.get(i).lose;
				//ゲーム差
				resultList.get(i).gameBehind= ((double)recordCountTop-(double)recordCount)/2;

			}
		}
		for(int i=0;i<resultList.size();i++){
			resultList.get(i).opponentMap=new HashMap<Integer,String>();
			for(int j=0;j<opponentList.size();j++){
				if(resultList.get(i).teamId==opponentList.get(j).teamId){
					//HashMap<Integer,String> oMap=new HashMap<Integer,String>();
					//oMap.put(resultList.get(i).teamId,opponentList.get(j).win+"-"+opponentList.get(j).lose+"("+opponentList.get(j).draw+")");
					resultList.get(i).opponentMap.put(opponentList.get(j).opponent,opponentList.get(j).win+"-"+opponentList.get(j).lose+"("+opponentList.get(j).draw+")");
				}
			}
		}
		//API出力処理
		ResultApiDto res=new ResultApiDto();
		res.averageTop10=averageTop10;
		res.avgHRTop10=avgHRTop10;
		res.avgRBITop10=avgRBITop10;
		res.eraTop10=eraTop10;
		res.fourBallTop10=fourBallTop10;
		res.hitTop10=hitTop10;
		res.homerunTop10=homerunTop10;
		res.nsoTop10=nsoTop10;
		res.obpTop10=obpTop10;
		res.opsTop10=opsTop10;
		res.rbiTop10=rbiTop10;
		res.regAtBats=regAtBats;
		res.regAtPitch=regAtPitch;
		res.winTop10=winTop10;
		res.saveTop10=saveTop10;
		res.strikeOutTop10=strikeOutTop10;
		res.listSize=listSize;
		res.battingResultList=battingResultList;
		res.pitchingResultList=pitchingResultList;
		res.twobaseTop10=twobaseTop10;
		res.slgTop10=slgTop10;
		res.resultList=resultList;
		//res.resultList2=resultList2;
		//res.opponentList=opponentList2;
		res.length=length;
		res.league=league;
		res.totalLeagueId=totalLeagueId;
		res.nonTitle=nonTitleList;
		String json=JSON.encode(res);
		ResponseUtil.write(json,"application/json");
		//メモリ解放処理
		request=null;
		res=null;
		resultLogic=null;
		tmpResultList=null;
		MemoryUtil.viewMemoryInfo();
		long t2=System.currentTimeMillis();
		logger.info("[API] "+ (t2-t1) +"ms");
		logger.info("[APIデータ加工] "+ (t2-t3) +"ms");
		return null;
	}

	@Execute(validator = false)
	public String overAll(){
		long t0=System.currentTimeMillis();
		//アクセス制限
		try {
    		InetAddress ia=InetAddress.getByName(request.getRemoteAddr());
//    		System.out.println(ia.getHostName());
    		if(!ia.getHostName().substring(ia.getHostName().length()-3).equals(".jp")
    				&& !request.getRemoteAddr().equals("0:0:0:0:0:0:0:1")
    				&& !request.getRemoteAddr().startsWith("192.168")
    				&& !ia.getHostName().substring(ia.getHostName().length()-4).equals(".net")
    				&& !ia.getHostName().equals("127.0.0.1")
    				&& !ia.getHostName().equals(request.getRemoteAddr())
    			){
    			logger.info("遮断:"+ia.getHostName()+":"+request.getRemotePort());
//    			//response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    			try {
					response.sendError(404, "jp、netドメインのみ許可しています。"+ia.getHostName());
				} catch (IOException e) {
					logger.error(e);
				}
        		return null;
    		}
			logger.info(ia.getHostName()+":"+request.getRemotePort());
		} catch (Exception e1) {
			logger.warn(e1);
		}
		long t1=System.currentTimeMillis();
		logger.info("lookup:"+(t1-t0));
		battingResultList=battingSumService.findOverAll();
		pitchingResultList=pitchingService.findOverAll();
		long t2=System.currentTimeMillis();
		//打率TOP10
		//battingResultList=battingSumService.findByPeriod(league.beginDate, league.endDate,"average desc");
		ResultLogic resultLogic=new ResultLogic();
		regAtBats=100;
		regAtPitch=36;
		averageTop10=new ArrayList<BattingResultDto>(battingResultList);
		for(int i=0;i<averageTop10.size();i++){
			for(int j=0;j<averageTop10.size();j++){
				if(averageTop10.get(i).average>averageTop10.get(j).average){
					BattingResultDto brd=averageTop10.get(i);
					averageTop10.set(i, averageTop10.get(j));
					averageTop10.set(j,brd);
				}
			}
		}
		averageTop10=resultLogic.returnAverageTop10(averageTop10,regAtBats);
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
				//pitchingResultList=pitchingService.findByPeriod(league.beginDate, league.endDate,"era asc");
				eraTop10=new ArrayList<PitchingResultDto>(pitchingResultList);
				for(int i=0;i<eraTop10.size();i++){
					for(int j=0;j<eraTop10.size();j++){
						if(eraTop10.get(i).era!=null && eraTop10.get(j).era!=null){
							if(eraTop10.get(i).era<eraTop10.get(j).era){
								PitchingResultDto prd=eraTop10.get(i);
								eraTop10.set(i, eraTop10.get(j));
								eraTop10.set(j,prd);
							}
						}
					}
				}
				eraTop10=resultLogic.returnEraTop10(eraTop10,regAtPitch);
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
						if(avgHRTop10.get(i).avgHomerun<avgHRTop10.get(j).avgHomerun){
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
				//ノンタイトルの行数を決定
				if(twobaseTop10.size()>=fourBallTop10.size()){
					listSize=twobaseTop10.size();
				}else{
					listSize=fourBallTop10.size();
				}
				if(listSize<=10){
					listSize=10;
				}
				long t3=System.currentTimeMillis();
				//API用にデータ加工
				List<NonTitleDto> nonTitleList=new ArrayList<NonTitleDto>();
				for(int i=0;i<listSize;i++){
					NonTitleDto ntd= new NonTitleDto();
					if(i<avgHRTop10.size()){
						ntd.avgHR=avgHRTop10.get(i).avgHomerun;
						ntd.avgHRName=avgHRTop10.get(i).name;
						ntd.avgHRRank=avgHRTop10.get(i).rank;
					}
					if(i<avgRBITop10.size()){
						ntd.avgRbi=avgRBITop10.get(i).avgRbi;
						ntd.avgRbiName=avgRBITop10.get(i).name;
						ntd.avgRbiRank=avgRBITop10.get(i).rank;
					}
					if(i<fourBallTop10.size()){
						ntd.fourball=fourBallTop10.get(i).fourBall;
						ntd.fourballName=fourBallTop10.get(i).name;
						ntd.fourballRank=fourBallTop10.get(i).rank;
					}
					if(i<obpTop10.size()){
						ntd.obp=obpTop10.get(i).obp;
						ntd.obpName=obpTop10.get(i).name;
						ntd.obpRank=obpTop10.get(i).rank;
					}
					if(i<opsTop10.size()){
						ntd.ops=opsTop10.get(i).ops;
						ntd.opsName=opsTop10.get(i).name;
						ntd.opsRank=opsTop10.get(i).rank;
					}
					if(i<slgTop10.size()){
						ntd.slg=slgTop10.get(i).slg;
						ntd.slgName=slgTop10.get(i).name;
						ntd.slgRank=slgTop10.get(i).rank;
					}
					if(i<nsoTop10.size()){
						ntd.strikeout=nsoTop10.get(i).notStrikeOut;
						ntd.strikeoutName=nsoTop10.get(i).name;
						ntd.strikeoutRank=nsoTop10.get(i).rank;
					}
					if(i<twobaseTop10.size()){
						ntd.twobase=twobaseTop10.get(i).twobase;
						ntd.twobaseName=twobaseTop10.get(i).name;
						ntd.twobaseRank=twobaseTop10.get(i).rank;
					}
					nonTitleList.add(ntd);
				}

				int recordCountTop=0;
//				for(int i=0;i<resultList.size();i++){
//					resultList.get(i).rank=i+1;
//					if(i==0){
//						recordCountTop=resultList.get(0).win-resultList.get(0).lose;
//					}
//					if(i>0){
//						//貯金
//						int recordCount = resultList.get(i).win-resultList.get(i).lose;
//						//ゲーム差
//						resultList.get(i).gameBehind= ((double)recordCountTop-(double)recordCount)/2;
//
//					}
//				}
//				for(int i=0;i<resultList.size();i++){
//					resultList.get(i).opponentMap=new HashMap<Integer,String>();
//					for(int j=0;j<opponentList.size();j++){
//						if(resultList.get(i).teamId==opponentList.get(j).teamId){
//							//HashMap<Integer,String> oMap=new HashMap<Integer,String>();
//							//oMap.put(resultList.get(i).teamId,opponentList.get(j).win+"-"+opponentList.get(j).lose+"("+opponentList.get(j).draw+")");
//							resultList.get(i).opponentMap.put(opponentList.get(j).opponent,opponentList.get(j).win+"-"+opponentList.get(j).lose+"("+opponentList.get(j).draw+")");
//						}
//					}
//				}

		HashMap<String,Object>map=new HashMap<String,Object>();
		map.put("regAtBats", regAtBats);
		map.put("regAtPitch", regAtPitch);
		map.put("battingResultList", battingResultList);
		map.put("pitchingResultList", pitchingResultList);
		map.put("averageTop10", averageTop10);
		map.put("homerunTop10", homerunTop10);
		map.put("rbiTop10", rbiTop10);
		map.put("hitTop10", hitTop10);
		map.put("eraTop10", eraTop10);
		map.put("winTop10", winTop10);
		map.put("saveTop10", saveTop10);
		map.put("strikeOutTop10", strikeOutTop10);
		map.put("nonTitle", nonTitleList);
//		ResultApiDto res=new ResultApiDto();
//		res.battingResultList=battingResultList;
//		res.pitchingResultList=pitchingResultList;
		String json=JSON.encode(map);
		ResponseUtil.write(json,"application/json");
		map=null;
		MemoryUtil.viewMemoryInfo();
		long t4=System.currentTimeMillis();
		logger.info("[SQL] "+ (t2-t1) +"ms");
		logger.info("[API] "+ (t3-t2) +"ms");
		logger.info("[APIデータ加工] "+ (t4-t3) +"ms");
		return null;
	}

	@Execute(validator = false)
	public String continuation(){
		long t0=System.currentTimeMillis();
		//アクセス制限
		try {
    		InetAddress ia=InetAddress.getByName(request.getRemoteAddr());
//    		System.out.println(ia.getHostName());
    		if(!ia.getHostName().substring(ia.getHostName().length()-3).equals(".jp")
    				&& !request.getRemoteAddr().equals("0:0:0:0:0:0:0:1")
    				&& !request.getRemoteAddr().startsWith("192.168")
    				&& !ia.getHostName().substring(ia.getHostName().length()-4).equals(".net")
    				&& !ia.getHostName().equals("127.0.0.1")
    				&& !ia.getHostName().equals(request.getRemoteAddr())
    			){
    			logger.info("遮断:"+ia.getHostName()+":"+request.getRemotePort());
//    			//response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    			try {
					response.sendError(404, "jp、netドメインのみ許可しています。"+ia.getHostName());
				} catch (IOException e) {
					logger.error(e);
				}
        		return null;
    		}
			logger.info(ia.getHostName()+":"+request.getRemotePort());
		} catch (Exception e1) {
			logger.warn(e1);
		}
		long t1=System.currentTimeMillis();
		Calendar cal=Calendar.getInstance();
		cal.setTime(new Date());
		logger.info("lookup:"+(t1-t0));
		battingResultList=battingSumService.continueBatting(cal.get(Calendar.YEAR)-1);
		pitchingResultList=pitchingService.continuePitching(cal.get(Calendar.YEAR)-1);
		//API出力処理
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("battingResultList", battingResultList);
		map.put("pitchingResultList", pitchingResultList);

		String json=JSON.encode(map);
		ResponseUtil.write(json,"application/json");
		map=null;
		battingResultList=null;
		MemoryUtil.viewMemoryInfo();
		long t2=System.currentTimeMillis();
		logger.info("[API] "+ (t2-t1) +"ms");
		return null;
	}
}
