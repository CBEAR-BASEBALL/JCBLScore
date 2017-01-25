package cx.myhome.ckoshien.action;

import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import javax.annotation.Resource;
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
import cx.myhome.ckoshien.service.TeamService;

public class GameSummaryAction {

@Resource
@ActionForm
public GameSummaryForm gameSummaryForm;
@Resource
public TeamService teamService;
@Resource
public PlayerService playerService;
public List<Team> teamList;
public List<PlayerDto> playerList;
@Resource
public GameService gameService;
public Game game;
public BattingSumForm battingSumForm;
public BattingSum battingSum;
@Resource
public BattingSumService battingSumService;
public GameSummaryLogic logic;
public List<BattingSum> firstBattingSumList;
public List<BattingSum> lastBattingSumList;
public int gameId;
public String id;
public List<Game> gameList;
public Pitching pitching;
@Resource
public PitchingService pitchingService;
public List<Pitching> firstPitchingList;
public List<Pitching> lastPitchingList;
public List<Pitching> pitchingList;
public List<BattingSum> battingSumList;
public List<BattingResultDto> battingResultList;
@Resource
public ResultService resultService;
public Result result;
public Result result2;
@Resource
public LeagueService leagueService;
public List<League> leagueList;
public Date gameDate;
public List<GameListDto> gameListDtos;
public Integer leagueId;
public League league;
public Team firstTeam;
public Team lastTeam;
public GameListDto gameListDto;
public Integer winnerId;
public Integer loserId;
public Integer saverId;

	@Execute(validator = false)
	public String index(){
		gameListDtos=gameService.findAllGroupByGameDate();
		logic=new GameSummaryLogic();
		gameListDtos=logic.convert2GameList(gameListDtos);
		return "index.jsp";
	}

	@Aspect(value="loginConfInterceptor")
	@Execute(validator = false)
	public String create(){
		teamList=teamService.findAllOrderById();
		playerList=playerService.findAllOrderById();
		return "create.jsp";
	}

	@Aspect(value="loginConfInterceptor")
	@Execute(validator = true,input="create",
			stopOnValidationError=false,
			validate="dateValidate",
			removeActionForm=true)
	public String createComplete(){
		playerList=playerService.findAllOrderById();
		StringBuilder sb = new StringBuilder();
		//日付を連結
		sb.append(gameSummaryForm.gameYear);
		sb.append("/");
		sb.append(gameSummaryForm.gameMonth);
		sb.append("/");
		sb.append(gameSummaryForm.gameDay);
		gameSummaryForm.gameDate=new String(sb);
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

		gameService.insert(game);
		battingSumForm = Beans.createAndCopy(BattingSumForm.class, gameSummaryForm).execute();
		for(int i=0;i<battingSumForm.atBats.size();i++){
			battingSum=new BattingSum();
			if (!battingSumForm.playerId.get(i).equals("")||!battingSumForm.tpa.get(i).equals("")){
				battingSum.gameId=game.gameId;
				logic=new GameSummaryLogic();
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
			pitching=new Pitching();
			if(!gameSummaryForm.p_playerId.get(i).equals("")){
				logic=new GameSummaryLogic();
				pitching=logic.convert2Pitching(gameSummaryForm,pitching, i);

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
		result=new Result();
		result2=new Result();
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
		return "index&redirect=true";
	}

	@Execute(urlPattern="show/{id}",validator = false)
	public String show(){
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
		return "show.jsp";
	}

	@Aspect(value="loginConfInterceptor")
	@Execute(urlPattern="edit/{id}",validator = false)
	public String edit(){
		gameId=Integer.parseInt(gameSummaryForm.id);
		game=gameService.findById(gameId);
		playerList=playerService.findAllOrderById();
		teamList=teamService.findAllOrderById();
		firstPitchingList=pitchingService.findByGameId(gameId,game.firstTeam);
		lastPitchingList=pitchingService.findByGameId(gameId,game.lastTeam);
		firstBattingSumList=battingSumService.findByGameId(gameId,game.firstTeam);
		lastBattingSumList=battingSumService.findByGameId(gameId,game.lastTeam);
		logic= new GameSummaryLogic();
		gameSummaryForm=logic.convert2GameSummary(game,firstBattingSumList,lastBattingSumList,gameSummaryForm,firstPitchingList,lastPitchingList);
		return "edit.jsp";
	}

	@Aspect(value="loginConfInterceptor")
	@Execute(validator=true,input="edit/{id}",
			stopOnValidationError=false,
			validate="dateValidate",
			removeActionForm=true)
	public String updateComplete(){
		StringBuilder sb = new StringBuilder();
		//日付を連結
		sb.append(gameSummaryForm.gameYear);
		sb.append("/");
		sb.append(gameSummaryForm.gameMonth);
		sb.append("/");
		sb.append(gameSummaryForm.gameDay);
		gameSummaryForm.gameDate=new String(sb);
		game = Beans.createAndCopy(Game.class, gameSummaryForm).execute();
		game.gameId=Integer.parseInt(gameSummaryForm.id);
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
		gameService.update(game);
		playerList=playerService.findAllOrderById();
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
				logic=new GameSummaryLogic();
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
		//GAME_IDに紐づく投手成績を全て削除
		pitchingList=pitchingService.findByGameIdAll(game.gameId);
		for(int i=0;i<pitchingList.size();i++){
			pitchingService.delete(pitchingList.get(i));
		}
		for(int i=0;i<gameSummaryForm.p_playerId.size();i++){
			if(!gameSummaryForm.p_playerId.get(i).equals("")){
				pitching=pitchingService.findById(Integer.parseInt(gameSummaryForm.p_playerId.get(i)));
				pitching=new Pitching();
				logic=new GameSummaryLogic();
				pitching=logic.convert2Pitching(gameSummaryForm,pitching, i);
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
		result=new Result();
		result2=new Result();
		result.id=resultService.findById(game.firstTeam,game.gameId).id;
		result2.id=resultService.findById(game.lastTeam,game.gameId).id;
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
		return "";
	}

	@Execute(urlPattern="date/{id}",validator=false)
	public String date(){
		gameId=Integer.parseInt(gameSummaryForm.id);
		gameDate=gameService.findById(gameId).gameDate;
		gameList=gameService.findByDateOrderByDate(gameDate);
		league=leagueService.findIdByDate(gameDate);
		gameListDtos=new ArrayList<GameListDto>();
		for(int i=0;i<gameList.size();i++){
			gameListDto=new GameListDto();
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
				winnerId=null;
				loserId=null;
				saverId=null;
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
		return "index2.jsp";
	}

	public ActionMessages dateValidate(){
		StringBuilder sb = new StringBuilder();
		//日付を連結
		sb.append(gameSummaryForm.gameYear);
		sb.append("/");
		sb.append(gameSummaryForm.gameMonth);
		sb.append("/");
		sb.append(gameSummaryForm.gameDay);
		gameSummaryForm.gameDate=new String(sb);
		game = Beans.createAndCopy(Game.class, gameSummaryForm).execute();
		league=leagueService.findIdByDate(game.gameDate);
		ActionMessages errors = new ActionMessages();
		if(league==null){
			errors.add("gameDate", new ActionMessage("試合日付がリーグ戦期間外です", false));
		}
		return errors;
	}
}
