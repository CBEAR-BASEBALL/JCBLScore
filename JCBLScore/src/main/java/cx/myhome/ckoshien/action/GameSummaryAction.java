package cx.myhome.ckoshien.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.sql.Date;
import javax.annotation.Resource;

import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import sun.org.mozilla.javascript.internal.regexp.SubString;

import cx.myhome.ckoshien.dto.BattingResultDto;
import cx.myhome.ckoshien.entity.BattingSum;
import cx.myhome.ckoshien.entity.Game;
import cx.myhome.ckoshien.entity.Pitching;
import cx.myhome.ckoshien.entity.Player;
import cx.myhome.ckoshien.entity.Team;
import cx.myhome.ckoshien.form.BattingSumForm;
import cx.myhome.ckoshien.form.GameSummaryForm;
import cx.myhome.ckoshien.form.PlayerForm;
import cx.myhome.ckoshien.logic.GameSummaryLogic;
import cx.myhome.ckoshien.service.BattingSumService;
import cx.myhome.ckoshien.service.GameService;
import cx.myhome.ckoshien.service.PitchingService;
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


	@Execute(validator = false)
	public String index() throws ParseException{
		gameList=gameService.findAllOrderByDate();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		battingResultList=battingSumService.findByPeriod(format.parse("2015-01-01"), format.parse("2015-06-30"));
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
				pitchingService.insert(pitching);
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
		firstPitchingList=pitchingService.findByGameId(gameId,game.firstTeam);
		lastPitchingList=pitchingService.findByGameId(gameId,game.lastTeam);
		firstBattingSumList=battingSumService.findByGameId(gameId,game.firstTeam);
		lastBattingSumList=battingSumService.findByGameId(gameId,game.lastTeam);
		logic= new GameSummaryLogic();
		gameSummaryForm=logic.convert2GameSummary(game,firstBattingSumList,lastBattingSumList,gameSummaryForm,firstPitchingList,lastPitchingList);
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
		return "";
	}
}
