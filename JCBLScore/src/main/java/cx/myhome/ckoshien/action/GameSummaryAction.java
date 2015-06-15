package cx.myhome.ckoshien.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.sql.Date;
import javax.annotation.Resource;

import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import sun.org.mozilla.javascript.internal.regexp.SubString;

import cx.myhome.ckoshien.entity.BattingSum;
import cx.myhome.ckoshien.entity.Game;
import cx.myhome.ckoshien.entity.Player;
import cx.myhome.ckoshien.entity.Team;
import cx.myhome.ckoshien.form.BattingSumForm;
import cx.myhome.ckoshien.form.GameForm;
import cx.myhome.ckoshien.form.GameSummaryForm;
import cx.myhome.ckoshien.form.PlayerForm;
import cx.myhome.ckoshien.logic.GameSummaryLogic;
import cx.myhome.ckoshien.service.BattingSumService;
import cx.myhome.ckoshien.service.GameService;
import cx.myhome.ckoshien.service.PlayerService;
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
public List<Player> playerList;
@Resource
public GameService gameService;
public GameForm gameForm;
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

	@Execute(validator = false)
	public String index(){
		gameList=gameService.findAllOrderByDate();
		return "index.jsp";
	}
	@Execute(validator = false)
	public String create(){
		teamList=teamService.findAllOrderById();
		playerList=playerService.findAllOrderById();
		return "create.jsp";
	}

	@Execute(validator = true,input="create?redirect=true")
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
		return "createComplete.jsp";
	}

	@SuppressWarnings("static-access")
	@Execute(urlPattern="edit/{id}",validator=false)
	public String edit(){
		gameId=Integer.parseInt(gameSummaryForm.id);
		game=gameService.findById(gameId);
		playerList=playerService.findAllOrderById();
		teamList=teamService.findAllOrderById();
		firstBattingSumList=battingSumService.findByGameId(gameId,game.firstTeam);
		lastBattingSumList=battingSumService.findByGameId(gameId,game.lastTeam);
		logic= new GameSummaryLogic();
		gameSummaryForm=logic.convert2GameSummary(game,firstBattingSumList,lastBattingSumList,gameSummaryForm);
		return "edit.jsp";
	}

	@Execute(validator=false)
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
		gameService.update(game);
		return "";
	}
}
