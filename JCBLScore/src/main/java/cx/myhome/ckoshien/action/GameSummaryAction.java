package cx.myhome.ckoshien.action;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.net.InetAddress;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.framework.beans.util.Beans;
import org.seasar.framework.container.annotation.tiger.Aspect;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import cx.myhome.ckoshien.dto.BattingResultDto;
import cx.myhome.ckoshien.dto.GameListDto;
import cx.myhome.ckoshien.dto.PlayerDto;
import cx.myhome.ckoshien.entity.BattingSum;
import cx.myhome.ckoshien.entity.Game;
import cx.myhome.ckoshien.entity.League;
import cx.myhome.ckoshien.entity.Pitching;
import cx.myhome.ckoshien.entity.Result;
import cx.myhome.ckoshien.entity.Team;
import cx.myhome.ckoshien.form.BattingSumForm;
import cx.myhome.ckoshien.form.GameSummaryForm;
import cx.myhome.ckoshien.logic.GameSummaryLogic;
import cx.myhome.ckoshien.service.BattingSumService;
import cx.myhome.ckoshien.service.GameService;
import cx.myhome.ckoshien.service.LeagueService;
import cx.myhome.ckoshien.service.PitchingService;
import cx.myhome.ckoshien.service.PlayerService;
import cx.myhome.ckoshien.service.ResultService;
import cx.myhome.ckoshien.service.TeamHistoryService;
import cx.myhome.ckoshien.service.TeamService;
import cx.myhome.ckoshien.util.MemoryUtil;

public class GameSummaryAction {

@Resource
@ActionForm
protected GameSummaryForm gameSummaryForm;
@Resource
protected TeamService teamService;
@Resource
protected PlayerService playerService;
public List<Team> teamList;
public List<PlayerDto> playerList;
@Resource
protected GameService gameService;
public Game game;
protected BattingSumForm battingSumForm;
public BattingSum battingSum;
@Resource
protected BattingSumService battingSumService;

public List<BattingSum> firstBattingSumList;
public List<BattingSum> lastBattingSumList;
public int gameId;
public String id;
public List<Game> gameList;
@Resource
protected PitchingService pitchingService;
public List<Pitching> firstPitchingList;
public List<Pitching> lastPitchingList;
public List<Pitching> pitchingList;
public List<BattingSum> battingSumList;
public List<BattingResultDto> battingResultList;
@Resource
protected ResultService resultService;
@Resource
protected LeagueService leagueService;
public List<League> leagueList;
public List<GameListDto> gameListDtos;
public Integer leagueId;
public League league;
public Team firstTeam;
public Team lastTeam;
@Resource
protected HttpServletRequest request;
@Resource
protected HttpServletResponse response;
public String gameDate;
@Resource
protected TeamHistoryService teamHistoryService;

private static Logger logger = Logger.getLogger("rootLogger");
//public GameListDto gameListDto;

	@Execute(validator = false)
	public String index(){
		gameListDtos=gameService.findAllGroupByGameDate();
		GameSummaryLogic logic=new GameSummaryLogic();
		gameListDtos=logic.convert2GameList(gameListDtos);
		logger.info("/gameSummary");
		MemoryUtil.viewMemoryInfo();
		return "index.jsp";
	}

	@Aspect(value="loginConfInterceptor")
	@Execute(validator = false)
	public String create(){
		teamList=teamService.findAllOrderById();
		//新規作成時は日付がわからないのでplayerテーブルから引っ張る
		playerList=playerService.findAllOrderById();
		//return "create.jsp";
		return "create2.jsp";
	}

	@Aspect(value="loginConfInterceptor")
	@Execute(validator = true,input="create",
			stopOnValidationError=false,
			validate="dateValidateInsert",
			removeActionForm=true)
	public String createComplete(){
		game = Beans.createAndCopy(Game.class, gameSummaryForm).execute();
		leagueList=leagueService.findAllOrderByIdExceptTotal();
		//リーグID検索
		for (int i=0;i<leagueList.size();i++){
			int j=game.gameDate.compareTo(leagueList.get(i).beginDate);
			int k=game.gameDate.compareTo(leagueList.get(i).endDate);
			if(j>=0 && k<=0){
				game.leagueId=leagueList.get(i).id;
				break;
			}
		}
		//playerList=playerService.findAllOrderById();
		//leagueIdを渡して所属チームIDを引っ張る
		// チーム履歴で照合できない場合はplayerテーブルの情報
		playerList=teamHistoryService.findPlayerWithTeamHistory(game.leagueId);
		gameService.insert(game);
		battingSumForm = Beans.createAndCopy(BattingSumForm.class, gameSummaryForm).execute();
		for(int i=0;i<battingSumForm.atBats.size();i++){
			battingSum=new BattingSum();
			if (!battingSumForm.playerId.get(i).equals("")||!battingSumForm.tpa.get(i).equals("")){
				battingSum.gameId=game.gameId;
				GameSummaryLogic logic=new GameSummaryLogic();
				battingSum=logic.convert2BattingSum(battingSumForm, battingSum, playerList,i);
				if (battingSumForm.myTeamId.get(i).equals("0")){
					//先攻の場合先攻チームIDを代入
					battingSum.myteamId=game.firstTeam;
				}else if(battingSumForm.myTeamId.get(i).equals("1")){
					//後攻の場合後攻チームIDを代入
					battingSum.myteamId=game.lastTeam;
				}
				battingSumService.insert(battingSum);
			}
		}
		for(int i=0;i<gameSummaryForm.p_playerId.size();i++){
			Pitching pitching=new Pitching();
			if(!gameSummaryForm.p_playerId.get(i).equals("")){
				GameSummaryLogic logic=new GameSummaryLogic();
				pitching=logic.convert2Pitching(gameSummaryForm,pitching, i);
				logger.info(pitching.inning);
				logger.info(gameSummaryForm.inning2.get(i));
				if(gameSummaryForm.p_myTeamId.get(i).equals("0")){
					pitching.myteamId=Integer.parseInt(gameSummaryForm.firstTeam);
				}else if(gameSummaryForm.p_myTeamId.get(i).equals("1")){
					pitching.myteamId=Integer.parseInt(gameSummaryForm.lastTeam);
				}
				for(int j=0;j<playerList.size();j++){
					if(playerList.get(j).id.equals(pitching.playerId)){
						pitching.teamId=playerList.get(j).teamId;
						break;
					}
				}
				pitching.gameId=game.gameId;
				pitchingService.insert(pitching);
			}
		}
		//resultテーブルinsert
		Result result=new Result();
		Result result2=new Result();
		result.gameId=game.gameId;
		result2.gameId=game.gameId;
		result.teamId=game.firstTeam;
		result2.teamId=game.lastTeam;
		result.opponent=game.lastTeam;
		result2.opponent=game.firstTeam;
		if(Integer.parseInt(gameSummaryForm.firstRun)>Integer.parseInt(gameSummaryForm.lastRun)){
			result.win=1;
			result.lose=0;
			result.draw=0;
			result2.win=0;
			result2.lose=1;
			result2.draw=0;
		}else if(Integer.parseInt(gameSummaryForm.firstRun)<Integer.parseInt(gameSummaryForm.lastRun)){
			result.win=0;
			result.lose=1;
			result.draw=0;
			result2.win=1;
			result2.lose=0;
			result2.draw=0;
		}else if(Integer.parseInt(gameSummaryForm.firstRun)==Integer.parseInt(gameSummaryForm.lastRun)){
			result.win=0;
			result.lose=0;
			result.draw=1;
			result2.win=0;
			result2.lose=0;
			result2.draw=1;
		}
		result.leagueId=game.leagueId;
		result2.leagueId=game.leagueId;
		resultService.insert(result);
		resultService.insert(result2);
		MemoryUtil.viewMemoryInfo();
		return "index&redirect=true";
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
		gameId=Integer.parseInt(gameSummaryForm.id);
		game=gameService.findById(gameId);
		//playerList=playerService.findAllOrderById();
		firstTeam=teamService.findById(game.firstTeam);
		lastTeam=teamService.findById(game.lastTeam);
		//teamList=teamService.findAllOrderById();
		firstPitchingList=pitchingService.findByGameId(gameId,game.firstTeam);
		lastPitchingList=pitchingService.findByGameId(gameId,game.lastTeam);
		firstBattingSumList=battingSumService.findByGameId(gameId,game.firstTeam);
		lastBattingSumList=battingSumService.findByGameId(gameId,game.lastTeam);
		//logic= new GameSummaryLogic();
		//gameSummaryForm=logic.convert2GameSummary(game,firstBattingSumList,lastBattingSumList,gameSummaryForm,firstPitchingList,lastPitchingList);
		logger.info("/gameSummary/show/"+gameSummaryForm.id);
		MemoryUtil.viewMemoryInfo();
		return "show.jsp";
	}

	@Aspect(value="loginConfInterceptor")
	@Execute(urlPattern="edit/{id}",validator = false)
	public String edit(){
		gameId=Integer.parseInt(gameSummaryForm.id);
		game=gameService.findById(gameId);
		//playerList=playerService.findAllOrderById();
		//チーム履歴テーブル実装による仕様変更
		playerList=teamHistoryService.findPlayerWithTeamHistory(game.leagueId);
		teamList=teamService.findAllOrderById();
		firstPitchingList=pitchingService.findByGameId(gameId,game.firstTeam);
		lastPitchingList=pitchingService.findByGameId(gameId,game.lastTeam);
		firstBattingSumList=battingSumService.findByGameId(gameId,game.firstTeam);
		lastBattingSumList=battingSumService.findByGameId(gameId,game.lastTeam);
		GameSummaryLogic logic= new GameSummaryLogic();
		gameSummaryForm=logic.convert2GameSummary(game,firstBattingSumList,lastBattingSumList,gameSummaryForm,firstPitchingList,lastPitchingList);
		gameDate=game.gameDate.toString().toString().replace("-", "/");
		MemoryUtil.viewMemoryInfo();
		//return "edit.jsp";
		return "edit2.jsp";
	}

	@Aspect(value="loginConfInterceptor")
	@Execute(validator=true,input="edit/{id}",
			stopOnValidationError=false,
			validate="dateValidateUpdate",
			removeActionForm=false)
	public String updateComplete(){
			//StringBuilder sb = new StringBuilder();
			Game game=new Game();
			game=gameService.findById(Integer.parseInt(gameSummaryForm.id));
			//game.gameId=Integer.parseInt(gameSummaryForm.id);
			leagueList=leagueService.findAllOrderByIdExceptTotal();
			//リーグID検索
			for (int i=0;i<leagueList.size();i++){
				int j=game.gameDate.compareTo(leagueList.get(i).beginDate);
				int k=game.gameDate.compareTo(leagueList.get(i).endDate);
				if(j>=0 && k<=0){
					game.leagueId=leagueList.get(i).id;
					break;
				}
			}
			//gameService.update(game);
			//playerList=playerService.findAllOrderById();
			//leagueIdを渡して所属チームIDを引っ張る
			// チーム履歴で照合できない場合はplayerテーブルの情報
			playerList=teamHistoryService.findPlayerWithTeamHistory(game.leagueId);
			battingSumForm = Beans.createAndCopy(BattingSumForm.class, gameSummaryForm).execute();

			//GAME_IDに紐づく打撃成績をテーブルから全て削除
			battingSumList=battingSumService.findByGameIdAll(game.gameId);
			for(int i=0;i<battingSumList.size();i++){
				battingSumService.delete(battingSumList.get(i));
			}
			for(int i=0;i<battingSumForm.atBats.size();i++){
				battingSum=new BattingSum();
				if (!battingSumForm.playerId.get(i).equals("")||!battingSumForm.tpa.get(i).equals("")){
					battingSum.gameId=game.gameId;
					GameSummaryLogic logic=new GameSummaryLogic();
					battingSum=logic.convert2BattingSum(battingSumForm, battingSum, playerList,i);
					if (battingSumForm.myTeamId.get(i).equals("0")){
						//先攻の場合先攻チームIDを代入
						battingSum.myteamId=Integer.parseInt(gameSummaryForm.firstTeam);
						System.out.println("gameSummaryForm.firstTeam");
					}else if(battingSumForm.myTeamId.get(i).equals("1")){
						//後攻の場合後攻チームIDを代入
						battingSum.myteamId=Integer.parseInt(gameSummaryForm.lastTeam);
						System.out.println("gameSummaryForm.lastTeam");
					}
					battingSumService.insert(battingSum);
				}
			}
			//GAME_IDに紐づく投手成績を全て削除
			pitchingList=pitchingService.findByGameIdAll(game.gameId);
			for(int i=0;i<pitchingList.size();i++){
				pitchingService.delete(pitchingList.get(i));
			}
			for(int i=0;i<gameSummaryForm.p_playerId.size();i++){
				if(!gameSummaryForm.p_playerId.get(i).equals("")){
					Pitching pitching=pitchingService.findById(Integer.parseInt(gameSummaryForm.p_playerId.get(i)));
					if(pitching ==null){
						pitching=new Pitching();
					}
					GameSummaryLogic logic=new GameSummaryLogic();
					pitching=logic.convert2Pitching(gameSummaryForm,pitching, i);
					logger.info(pitching.inning);
					logger.info(gameSummaryForm.inning2.get(i));
					for(int j=0;j<playerList.size();j++){
						if(playerList.get(j).id.equals(pitching.playerId)){
							pitching.teamId=playerList.get(j).teamId;
							break;
						}
					}
					pitching.gameId=game.gameId;
					pitchingService.insert(pitching);
				}
			}
			//resultテーブルupdate
			Result result=new Result();
			Result result2=new Result();
			result.id=resultService.findById(game.firstTeam,game.gameId).id;
			result2.id=resultService.findById(game.lastTeam,game.gameId).id;
			//game = Beans.createAndCopy(Game.class, gameSummaryForm).execute();
			game.firstTeam=Integer.parseInt(gameSummaryForm.firstTeam);
			game.lastTeam=Integer.parseInt(gameSummaryForm.lastTeam);
			result.gameId=game.gameId;
			result2.gameId=game.gameId;
			result.teamId=game.firstTeam;
			result2.teamId=game.lastTeam;
			result.opponent=game.lastTeam;
			result2.opponent=game.firstTeam;
			if(Integer.parseInt(gameSummaryForm.firstRun)>Integer.parseInt(gameSummaryForm.lastRun)){
				result.win=1;
				result.lose=0;
				result.draw=0;
				result2.win=0;
				result2.lose=1;
				result2.draw=0;
			}else if(Integer.parseInt(gameSummaryForm.firstRun)<Integer.parseInt(gameSummaryForm.lastRun)){
				result.win=0;
				result.lose=1;
				result.draw=0;
				result2.win=1;
				result2.lose=0;
				result2.draw=0;
			}else if(Integer.parseInt(gameSummaryForm.firstRun)==Integer.parseInt(gameSummaryForm.lastRun)){
				result.win=0;
				result.lose=0;
				result.draw=1;
				result2.win=0;
				result2.lose=0;
				result2.draw=1;
			}
			result.leagueId=game.leagueId;
			result2.leagueId=game.leagueId;
			resultService.update(result);
			resultService.update(result2);
			gameService.update(game);
			MemoryUtil.viewMemoryInfo();
		return "";
	}

	@Execute(urlPattern="date/{id}",validator=false)
	public String date(){
		long t0=System.currentTimeMillis();
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
		logger.info("lookup:"+(t1-t0)+"mS");
		gameId=Integer.parseInt(gameSummaryForm.id);
		Date gameDate=gameService.findById(gameId).gameDate;
		gameList=gameService.findByDateOrderByDate(gameDate);
		league=leagueService.findIdByDate(gameDate);
		gameListDtos=new ArrayList<GameListDto>();
		for(int i=0;i<gameList.size();i++){
			GameListDto gameListDto=new GameListDto();
			game=gameList.get(i);
			gameListDto = Beans.createAndCopy(GameListDto.class, game).execute();
			//通算本塁打数
			gameListDto.homerun=battingSumService.findHomerun(league.beginDate, game.gameDate, game.gameId, game.gameNumber);
			//その試合の本数を算出
			for(int k=0;k<gameListDto.homerun.size();k++){
				battingSum=battingSumService.findByPlayerIdAndGameId(gameListDto.homerun.get(k).playerId, game.gameId);
				gameListDto.homerun.get(k).homerunCount=battingSum.homerun;
			}
			//引き分けの場合勝敗なし
			if(game.firstRun!=game.lastRun){
				pitchingList=pitchingService.findByGameIdAll(game.gameId);
				Integer winnerId=null;
				Integer loserId=null;
				Integer saverId=null;
				for(int j=0;j<pitchingList.size();j++){
					if(pitchingList.get(j).win==1){
						winnerId=pitchingList.get(j).playerId;
					}else if(pitchingList.get(j).lose==1){
						loserId=pitchingList.get(j).playerId;
					}else if(pitchingList.get(j).save==1){
						saverId=pitchingList.get(j).playerId;
					}
				}
				if(null!=winnerId){
					gameListDto.win=pitchingService.findByIdAndDate(winnerId, league.beginDate, game.gameDate, game.gameNumber);
				}
				if(null!=loserId){
					gameListDto.lose=pitchingService.findByIdAndDate(loserId, league.beginDate, game.gameDate, game.gameNumber);
				}
				if(null!=saverId){
					gameListDto.save=pitchingService.findByIdAndDate(saverId, league.beginDate, game.gameDate, game.gameNumber);
				}
			}
			gameListDtos.add(gameListDto);
		}
		long t2=System.currentTimeMillis();
		logger.info((t2-t1)+"ms");
		logger.info("/gameSummary/date/"+gameSummaryForm.id);
		MemoryUtil.viewMemoryInfo();
		return "index2.jsp";
	}

	public ActionMessages dateValidateInsert(){
		//game = Beans.createAndCopy(Game.class, gameSummaryForm).execute();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		try {
			league=leagueService.findIdByDate(sdf.parse(gameSummaryForm.gameDate));
		} catch (Exception e) {
			logger.error("ERROR", e);
		}
		//レコードの存在チェック
		Game oldGame=null;
		try {
			oldGame = gameService.findByDateAndNumber(sdf.parse(gameSummaryForm.gameDate), Integer.parseInt(gameSummaryForm.gameNumber));
		} catch (Exception e) {
			logger.error("ERROR",e);
		}
		ActionMessages errors = new ActionMessages();
		if(oldGame!=null){
			errors.add("gameDate", new ActionMessage("日付と試合番号が重複しています", false));
		}
		if(league==null){
			errors.add("gameDate", new ActionMessage("試合日付がリーグ戦期間外です", false));
		}
		if(gameSummaryForm.firstTeam.equals(gameSummaryForm.lastTeam)){
			errors.add("teamId", new ActionMessage("先攻後攻のチームIDが同じです", false));
		}
		return errors;
	}

	public ActionMessages dateValidateUpdate(){
		//game = Beans.createAndCopy(Game.class, gameSummaryForm).execute();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		try {
			league=leagueService.findIdByDate(sdf.parse(gameSummaryForm.gameDate));
		} catch (Exception e) {
			logger.error("ERROR", e);
		}
		//レコードの存在チェック
		Game oldGame=null;
		try {
			oldGame = gameService.findByDateAndNumber(sdf.parse(gameSummaryForm.gameDate), Integer.parseInt(gameSummaryForm.gameNumber));
		} catch (Exception e) {
			logger.error("ERROR",e);
		}
		ActionMessages errors = new ActionMessages();
		if(oldGame!=null && oldGame.gameId==Integer.parseInt(gameSummaryForm.id)){
			//レコードが存在し、かつgameIdが同じときupdate
		}else{
			errors.add("gameDate", new ActionMessage("日付と試合番号が重複しています", false));
		}
		if(league==null){
			errors.add("gameDate", new ActionMessage("試合日付がリーグ戦期間外です", false));
		}
		if(gameSummaryForm.firstTeam.equals(gameSummaryForm.lastTeam)){
			errors.add("teamId", new ActionMessage("先攻後攻のチームIDが同じです", false));
		}
		return errors;
	}
}
