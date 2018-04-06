package cx.myhome.ckoshien.action;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import cx.myhome.ckoshien.util.AccessControlUtil;
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
public String device;

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

	//@Execute(urlPattern="season/{id}",validator = false)
	@Execute(urlPattern="old/{id}",validator = false)
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
		//user-agent判定
		String userAgent = request.getHeader("user-agent");
		logger.info("UserAgent:"+userAgent);
		if(userAgent!=null){
			if(userAgent.indexOf("Android")==-1 && userAgent.indexOf("iPhone")==-1){
				device="pc";
				//mav.addObject("device", "pc");
			}else{
				System.out.println("mobile");
				//mav.addObject("device","mobile");
				device="mobile";
			}
		}

		logger.info("result/season/"+Integer.parseInt(resultForm.id));
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
		homerunTop10=resultLogic.returnHomerunTop10(battingResultList);
		//打点TOP10
		rbiTop10=resultLogic.returnRbiTop10(battingResultList);
		//安打数TOP10
		hitTop10=resultLogic.returnHitTop10(battingResultList);
		//防御率TOP10
		pitchingResultList=pitchingService.findByPeriod(league.beginDate, league.endDate,"era asc");
		eraTop10=resultLogic.returnEraTop10(pitchingResultList,regAtPitch);
		//勝利数TOP10
		winTop10=resultLogic.returnWinTop10(pitchingResultList);
		//セーブTOP10
		saveTop10=resultLogic.returnSaveTop10(pitchingResultList);
		//奪三振TOP10
		strikeOutTop10=resultLogic.returnStrikeOutTop10(pitchingResultList);
		//出塁率TOP10

		obpTop10=resultLogic.returnObpTop10(battingResultList,regAtBats);
		//二塁打TOP10
		//twobaseTop10=battingSumService.findByPeriod(league.beginDate, league.endDate,"twobase desc");
		twobaseTop10=resultLogic.returnTwobaseTop10(battingResultList);
		//長打率TOP10
		//slgTop10=battingSumService.findByPeriod(league.beginDate, league.endDate,"slg desc");

		slgTop10=resultLogic.returnSlgTop10(battingResultList,regAtBats);
		//最多四球TOP10
		fourBallTop10=resultLogic.returnFourBallTop10(battingResultList);
		//OPS TOP10
		opsTop10=resultLogic.returnOpsTop10(battingResultList,regAtBats);
		//三振率TOP10
		nsoTop10=resultLogic.returnNsoTop10(battingResultList,regAtBats);
		//本塁打率TOP10
		avgHRTop10=resultLogic.returnAvgHRTop10(battingResultList,regAtBats);
		//打点率
		avgRBITop10=resultLogic.returnAvgRBITop10(battingResultList,regAtBats);
		//ノンタイトルの行数を決定
		if(twobaseTop10.size()>=fourBallTop10.size()){
			listSize=twobaseTop10.size();
		}else{
			listSize=fourBallTop10.size();
		}
		if(listSize<=10){
			listSize=10;
		}
		request=null;
		resultLogic=null;
		tmpResultList=null;
		MemoryUtil.viewMemoryInfo();
		long t2=System.currentTimeMillis();
		logger.info(t2-t1+"ms");
		return "stats.jsp";
	}

	@Execute(urlPattern="season/{id}",validator = false)
	//@Execute(urlPattern="ajax/{id}",validator = false)
	public String seasonAjax(){
		//アクセス制限
		try {
    		InetAddress ia=InetAddress.getByName(request.getRemoteAddr());
//		    		System.out.println(ia.getHostName());
    		if(!ia.getHostName().substring(ia.getHostName().length()-3).equals(".jp")
    				&& !request.getRemoteAddr().equals("0:0:0:0:0:0:0:1")
    				&& !request.getRemoteAddr().startsWith("192.168")
		    		&& !ia.getHostName().substring(ia.getHostName().length()-4).equals(".net")
		    		&& !ia.getHostName().equals("127.0.0.1")
		    		&& !ia.getHostName().equals(request.getRemoteAddr())
		    ){
		    	logger.info("遮断:"+ia.getHostName()+":"+request.getRemotePort());
//		    	response.setStatus(HttpServletResponse.SC_NOT_FOUND);
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
		AccessControlUtil aUtil=new AccessControlUtil();
		System.out.println(aUtil.isBlockedAddress(request));
		try{
			league=leagueService.findById(Integer.parseInt(resultForm.id));
		}catch(NumberFormatException e){
			return "index&redirect=true";
		}
		//user-agent判定
		String userAgent = request.getHeader("user-agent");
		logger.info("UserAgent:"+userAgent);
//		if(userAgent!=null){
//			if(userAgent.indexOf("Android")==-1 && userAgent.indexOf("iPhone")==-1){
//				device="pc";
//				//mav.addObject("device", "pc");
//			}else{
//				System.out.println("mobile");
//				//mav.addObject("device","mobile");
//				device="mobile";
//			}
//		}

		logger.info("result/season/"+Integer.parseInt(resultForm.id));
		totalLeagueId = leagueService.findTotal().get(0).id;
//		opponentList=resultService.findOpponentResult(Integer.parseInt(resultForm.id));
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
		request=null;
		tmpResultList=null;
		long t2=System.currentTimeMillis();
		logger.info(t2-t1+"ms");
		return "ajaxStats.jsp";
	}

	@Execute(validator = false)
	public String overAll(){
		return "overAll2.jsp";
	}

	@Execute(validator = false)
	public String continuation(){
		return "continuation.jsp";
	}
}
