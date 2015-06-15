package cx.myhome.ckoshien.logic;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import cx.myhome.ckoshien.entity.BattingSum;
import cx.myhome.ckoshien.entity.Game;
import cx.myhome.ckoshien.entity.Player;
import cx.myhome.ckoshien.form.BattingSumForm;
import cx.myhome.ckoshien.form.GameForm;
import cx.myhome.ckoshien.form.GameSummaryForm;
import cx.myhome.ckoshien.service.BattingSumService;

public class GameSummaryLogic {
	public GameSummaryForm gameSummaryForm;

	public int gameId;

	public GameSummaryForm convert2GameSummary(Game game,List<BattingSum> firstBattingSumList,List<BattingSum> lastBattingSumList,GameSummaryForm gameSummaryForm){
		gameId=Integer.parseInt(gameSummaryForm.id);
		//firstBattingSumList=battingSumService.findByGameId(gameId,game.firstTeam);
		//lastBattingSumList=battingSumService.findByGameId(gameId,game.lastTeam);
		Calendar cal=Calendar.getInstance();
		cal.setTime(game.gameDate);
		gameSummaryForm.gameYear=String.valueOf(cal.get(Calendar.YEAR));
		gameSummaryForm.gameMonth=String.valueOf(cal.get(Calendar.MONTH)+1);
		gameSummaryForm.gameDay=String.valueOf(cal.get(Calendar.DATE));
		gameSummaryForm.gameNumber=String.valueOf(game.gameNumber);
		gameSummaryForm.firstTeam=String.valueOf(game.firstTeam);
		gameSummaryForm.lastTeam=String.valueOf(game.lastTeam);
		gameSummaryForm.firstRun=String.valueOf(game.firstRun);
		gameSummaryForm.lastRun=String.valueOf(game.lastRun);
		gameSummaryForm.top1st=String.valueOf(game.top1st);
		gameSummaryForm.bottom1st=String.valueOf(game.bottom1st);
		gameSummaryForm.top2nd=String.valueOf(game.top2nd);
		gameSummaryForm.bottom2nd=String.valueOf(game.bottom2nd);
		gameSummaryForm.top3rd=String.valueOf(game.top3rd);
		gameSummaryForm.bottom3rd=String.valueOf(game.bottom3rd);
		gameSummaryForm.top4th=String.valueOf(game.top4th);
		gameSummaryForm.bottom4th=String.valueOf(game.bottom4th);
		gameSummaryForm.top5th=String.valueOf(game.top5th);
		gameSummaryForm.bottom5th=String.valueOf(game.bottom5th);
		gameSummaryForm.playerId=new ArrayList<String>();
		gameSummaryForm.tpa=new ArrayList<String>();
		gameSummaryForm.atBats=new ArrayList<String>();
		gameSummaryForm.hit=new ArrayList<String>();
		gameSummaryForm.rbi=new ArrayList<String>();
		gameSummaryForm.fourBall=new ArrayList<String>();
		gameSummaryForm.strikeOut=new ArrayList<String>();
		gameSummaryForm.twoBase=new ArrayList<String>();
		gameSummaryForm.homerun=new ArrayList<String>();
		gameSummaryForm.myTeamId=new ArrayList<String>();
		gameSummaryForm.teamId=new ArrayList<String>();
		gameSummaryForm.gameId=new ArrayList<String>();
		for (int i=0;i<10;i++){
			if(i<firstBattingSumList.size()){
				gameSummaryForm.playerId.add(String.valueOf(firstBattingSumList.get(i).playerId));
				gameSummaryForm.tpa.add(String.valueOf(firstBattingSumList.get(i).tpa));
				gameSummaryForm.atBats.add(String.valueOf(firstBattingSumList.get(i).atBats));
				gameSummaryForm.hit.add(String.valueOf(firstBattingSumList.get(i).hit));
				gameSummaryForm.rbi.add(String.valueOf(firstBattingSumList.get(i).rbi));
				gameSummaryForm.fourBall.add(String.valueOf(firstBattingSumList.get(i).fourBall));
				gameSummaryForm.strikeOut.add(String.valueOf(firstBattingSumList.get(i).strikeOut));
				gameSummaryForm.twoBase.add(String.valueOf(firstBattingSumList.get(i).twobase));
				gameSummaryForm.homerun.add(String.valueOf(firstBattingSumList.get(i).homerun));
				gameSummaryForm.myTeamId.add(String.valueOf(firstBattingSumList.get(i).myteamId));
				gameSummaryForm.teamId.add(String.valueOf(firstBattingSumList.get(i).teamId));
				gameSummaryForm.gameId.add(String.valueOf(game.gameId));
			}else{
				gameSummaryForm.playerId.add(" ");
				gameSummaryForm.tpa.add("");
				gameSummaryForm.atBats.add("");
				gameSummaryForm.hit.add("");
				gameSummaryForm.rbi.add("");
				gameSummaryForm.fourBall.add("");
				gameSummaryForm.strikeOut.add("");
				gameSummaryForm.twoBase.add("");
				gameSummaryForm.homerun.add("");
				gameSummaryForm.myTeamId.add("");
				gameSummaryForm.teamId.add("");
				gameSummaryForm.gameId.add("");
			}
		}
		for (int i=0;i<10;i++){
			if(i<lastBattingSumList.size()){
				gameSummaryForm.playerId.add(String.valueOf(lastBattingSumList.get(i).playerId));
				gameSummaryForm.tpa.add(String.valueOf(lastBattingSumList.get(i).tpa));
				gameSummaryForm.atBats.add(String.valueOf(lastBattingSumList.get(i).atBats));
				gameSummaryForm.hit.add(String.valueOf(lastBattingSumList.get(i).hit));
				gameSummaryForm.rbi.add(String.valueOf(lastBattingSumList.get(i).rbi));
				gameSummaryForm.fourBall.add(String.valueOf(lastBattingSumList.get(i).fourBall));
				gameSummaryForm.strikeOut.add(String.valueOf(lastBattingSumList.get(i).strikeOut));
				gameSummaryForm.twoBase.add(String.valueOf(lastBattingSumList.get(i).twobase));
				gameSummaryForm.homerun.add(String.valueOf(lastBattingSumList.get(i).homerun));
				gameSummaryForm.myTeamId.add(String.valueOf(lastBattingSumList.get(i).myteamId));
				gameSummaryForm.teamId.add(String.valueOf(lastBattingSumList.get(i).teamId));
				gameSummaryForm.gameId.add(String.valueOf(game.gameId));
			}else{
				gameSummaryForm.playerId.add(" ");
				gameSummaryForm.tpa.add("");
				gameSummaryForm.atBats.add("");
				gameSummaryForm.hit.add("");
				gameSummaryForm.rbi.add("");
				gameSummaryForm.fourBall.add("");
				gameSummaryForm.strikeOut.add("");
				gameSummaryForm.twoBase.add("");
				gameSummaryForm.homerun.add("");
				gameSummaryForm.myTeamId.add("");
				gameSummaryForm.teamId.add("");
				gameSummaryForm.gameId.add("");
			}
		}
		return gameSummaryForm;
	}

	public BattingSum convert2BattingSum(BattingSumForm battingSumForm,BattingSum battingSum,List<Player> playerList,int i){
		battingSum.playerId=Integer.parseInt(battingSumForm.playerId.get(i));
		//battingSum.gameId=game.gameId;
		battingSum.tpa=Integer.parseInt(battingSumForm.tpa.get(i));
		battingSum.atBats=Integer.parseInt(battingSumForm.atBats.get(i));
		battingSum.hit=Integer.parseInt(battingSumForm.hit.get(i));
		battingSum.rbi=Integer.parseInt(battingSumForm.rbi.get(i));
		battingSum.fourBall=Integer.parseInt(battingSumForm.fourBall.get(i));
		battingSum.strikeOut=Integer.parseInt(battingSumForm.strikeOut.get(i));
		battingSum.twobase=Integer.parseInt(battingSumForm.twoBase.get(i));
		battingSum.homerun=Integer.parseInt(battingSumForm.homerun.get(i));
		//所属チームIDの検索と代入
		for(int j=0;j<playerList.size();j++){
			System.out.println(playerList.get(j).id);
			System.out.println(battingSum.playerId);
			if(playerList.get(j).id.equals(battingSum.playerId)){
				battingSum.teamId=playerList.get(j).teamId;
				break;
			}
		}

		return battingSum;
	}

}
