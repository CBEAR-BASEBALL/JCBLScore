package cx.myhome.ckoshien.logic;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import cx.myhome.ckoshien.dto.GameListDto;
import cx.myhome.ckoshien.entity.BattingSum;
import cx.myhome.ckoshien.entity.Game;
import cx.myhome.ckoshien.entity.Pitching;
import cx.myhome.ckoshien.entity.Player;
import cx.myhome.ckoshien.form.BattingSumForm;
import cx.myhome.ckoshien.form.GameForm;
import cx.myhome.ckoshien.form.GameSummaryForm;
import cx.myhome.ckoshien.service.BattingSumService;

public class GameSummaryLogic {
	public GameSummaryForm gameSummaryForm;

	public int gameId;

	public List<GameListDto> gameList;

	public GameListDto gameListDto;

	public static final String WIN ="1";
	public static final String LOSE="2";
	public static final String SAVE="3";

	public GameSummaryForm convert2GameSummary(Game game,List<BattingSum> firstBattingSumList,List<BattingSum> lastBattingSumList,GameSummaryForm gameSummaryForm,List<Pitching> firstPitchingList,List<Pitching> lastPitchingList){
		gameId=Integer.parseInt(gameSummaryForm.id);
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
		if(game.top2nd!=null){
			gameSummaryForm.top2nd=String.valueOf(game.top2nd);
		}
		if(game.bottom2nd!=null){
			gameSummaryForm.bottom2nd=String.valueOf(game.bottom2nd);
		}
		if(game.top3rd!=null){
			gameSummaryForm.top3rd=String.valueOf(game.top3rd);
		}
		if(game.bottom3rd!=null){
			gameSummaryForm.bottom3rd=String.valueOf(game.bottom3rd);
		}
		if(game.top4th!=null){
			gameSummaryForm.top4th=String.valueOf(game.top4th);
		}
		if(game.bottom4th!=null){
			gameSummaryForm.bottom4th=String.valueOf(game.bottom4th);
		}
		if(game.top5th!=null){
			gameSummaryForm.top5th=String.valueOf(game.top5th);
		}
		if(game.bottom5th!=null){
			gameSummaryForm.bottom5th=String.valueOf(game.bottom5th);
		}

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
		gameSummaryForm.playerRecordId=new ArrayList<String>();
		addBattingSumList(gameSummaryForm,firstBattingSumList, game);
		addBattingSumList(gameSummaryForm,lastBattingSumList, game);

		gameSummaryForm.complete=new ArrayList<String>();
		gameSummaryForm.inning1=new ArrayList<String>();
		gameSummaryForm.inning2=new ArrayList<String>();
		gameSummaryForm.p_fourBall=new ArrayList<String>();
		gameSummaryForm.p_hit=new ArrayList<String>();
		gameSummaryForm.p_homerun=new ArrayList<String>();
		gameSummaryForm.p_myTeamId=new ArrayList<String>();
		gameSummaryForm.p_playerId=new ArrayList<String>();
		gameSummaryForm.p_strikeOut=new ArrayList<String>();
		gameSummaryForm.pa=new ArrayList<String>();
		gameSummaryForm.result=new ArrayList<String>();
		gameSummaryForm.runs=new ArrayList<String>();
		gameSummaryForm.shutout=new ArrayList<String>();
		addPitchingList(gameSummaryForm,firstPitchingList);
		addPitchingList(gameSummaryForm,lastPitchingList);
		return gameSummaryForm;
	}

	public void addBattingSumList(GameSummaryForm gameSummaryForm,List<BattingSum> list,Game game){
		for (int i=0;i<10;i++){
			if(i<list.size()){
				gameSummaryForm.playerId.add(String.valueOf(list.get(i).playerId));
				gameSummaryForm.tpa.add(String.valueOf(list.get(i).tpa));
				gameSummaryForm.atBats.add(String.valueOf(list.get(i).atBats));
				gameSummaryForm.hit.add(String.valueOf(list.get(i).hit));
				gameSummaryForm.rbi.add(String.valueOf(list.get(i).rbi));
				gameSummaryForm.fourBall.add(String.valueOf(list.get(i).fourBall));
				gameSummaryForm.strikeOut.add(String.valueOf(list.get(i).strikeOut));
				gameSummaryForm.twoBase.add(String.valueOf(list.get(i).twobase));
				gameSummaryForm.homerun.add(String.valueOf(list.get(i).homerun));
				gameSummaryForm.myTeamId.add(String.valueOf(list.get(i).myteamId));
				gameSummaryForm.teamId.add(String.valueOf(list.get(i).teamId));
				gameSummaryForm.gameId.add(String.valueOf(game.gameId));
				gameSummaryForm.playerRecordId.add(String.valueOf(list.get(i).id));
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
				gameSummaryForm.playerRecordId.add("");
			}
		}
	}

	public void addPitchingList(GameSummaryForm gameSummaryForm,List<Pitching> list){
		for(int i=0;i<4;i++){
			if (i<list.size()){
				Double d = new Double(list.get(i).inning);
				Integer j = new Integer(d.intValue());
				Double d2 = new Double(j.doubleValue());
				int inningParts=(int)Math.round((d.doubleValue() - d2.doubleValue())*3);
				gameSummaryForm.complete.add(String.valueOf(list.get(i).complete));
				gameSummaryForm.inning1.add(String.valueOf(j));
				gameSummaryForm.inning2.add(String.valueOf(inningParts));
				gameSummaryForm.p_fourBall.add(String.valueOf(list.get(i).fourBall));
				gameSummaryForm.p_hit.add(String.valueOf(list.get(i).hit));
				gameSummaryForm.p_homerun.add(String.valueOf(list.get(i).homerun));
				gameSummaryForm.p_myTeamId.add(String.valueOf(list.get(i).myteamId));
				gameSummaryForm.p_playerId.add(String.valueOf(list.get(i).playerId));
				gameSummaryForm.p_strikeOut.add(String.valueOf(list.get(i).strikeOut));
				gameSummaryForm.pa.add(String.valueOf(list.get(i).pa));
				if (list.get(i).win==1){
					gameSummaryForm.result.add("1");
				}else if(list.get(i).lose==1){
					gameSummaryForm.result.add("2");
				}else if(list.get(i).save==1){
					gameSummaryForm.result.add("3");
				}else{
					gameSummaryForm.result.add("");
				}
				gameSummaryForm.runs.add(String.valueOf(list.get(i).runs));
				gameSummaryForm.shutout.add(String.valueOf(list.get(i).shutout));
			}else{
				gameSummaryForm.complete.add("");
				gameSummaryForm.inning1.add("");
				gameSummaryForm.inning2.add("");
				gameSummaryForm.p_fourBall.add("");
				gameSummaryForm.p_hit.add("");
				gameSummaryForm.p_homerun.add("");
				gameSummaryForm.p_myTeamId.add("");
				gameSummaryForm.p_playerId.add("");
				gameSummaryForm.p_strikeOut.add("");
				gameSummaryForm.pa.add("");
				gameSummaryForm.result.add("");
				gameSummaryForm.runs.add("");
				gameSummaryForm.shutout.add("");
			}
		}
	}

	public BattingSum convert2BattingSum(BattingSumForm battingSumForm,BattingSum battingSum,List<Player> playerList,int i){
		battingSum.playerId=Integer.parseInt(battingSumForm.playerId.get(i));
		//空文字の場合0と解釈
		if(battingSumForm.tpa.get(i)==""){
			battingSum.tpa=0;
		}else {
			battingSum.tpa=Integer.parseInt(battingSumForm.tpa.get(i));
		}
		if(battingSumForm.atBats.get(i)==""){
			battingSum.atBats=0;
		}else{
			battingSum.atBats=Integer.parseInt(battingSumForm.atBats.get(i));
		}
		if(battingSumForm.hit.get(i)==""){
			battingSum.hit=0;
		}else{
			battingSum.hit=Integer.parseInt(battingSumForm.hit.get(i));
		}
		if(battingSumForm.rbi.get(i)==""){
			battingSum.rbi=0;
		}else{
			battingSum.rbi=Integer.parseInt(battingSumForm.rbi.get(i));
		}
		if(battingSumForm.fourBall.get(i)==""){
			battingSum.fourBall=0;
		}else{
			battingSum.fourBall=Integer.parseInt(battingSumForm.fourBall.get(i));
		}
		if(battingSumForm.strikeOut.get(i)==""){
			battingSum.strikeOut=0;
		}else{
			battingSum.strikeOut=Integer.parseInt(battingSumForm.strikeOut.get(i));
		}
		if(battingSumForm.twoBase.get(i)==""){
			battingSum.twobase=0;
		}else{
			battingSum.twobase=Integer.parseInt(battingSumForm.twoBase.get(i));
		}
		if(battingSumForm.homerun.get(i)==""){
			battingSum.homerun=0;
		}else{
			battingSum.homerun=Integer.parseInt(battingSumForm.homerun.get(i));
		}
		//所属チームIDの検索と代入
		for(int j=0;j<playerList.size();j++){
			if(playerList.get(j).id.equals(battingSum.playerId)){
				battingSum.teamId=playerList.get(j).teamId;
				break;
			}
		}
		return battingSum;
	}



	public Pitching convert2Pitching(GameSummaryForm gameSummaryForm,Pitching pitching,int i){
		pitching.playerId=Integer.parseInt(gameSummaryForm.p_playerId.get(i));
		if(gameSummaryForm.result.get(i).equals(WIN)){
			pitching.win=1;
			pitching.lose=0;
			pitching.save=0;
		}else if(gameSummaryForm.result.get(i).equals(LOSE)){
			pitching.win=0;
			pitching.save=0;
			pitching.lose=1;
		}else if(gameSummaryForm.result.get(i).equals(SAVE)){
			pitching.save=1;
			pitching.win=0;
			pitching.lose=0;
		}else{
			pitching.save=0;
			pitching.win=0;
			pitching.lose=0;
		}
		//フォーム空欄時のエラー回避
		if(gameSummaryForm.inning1.get(i).equals("")){
			gameSummaryForm.inning1.add(i,"0");
		}
		if(gameSummaryForm.inning2.get(i).equals("")){
			gameSummaryForm.inning2.add(i,"0");
		}
		pitching.inning=Double.valueOf(gameSummaryForm.inning1.get(i))+Double.valueOf(gameSummaryForm.inning2.get(i))/3;
		pitching.pa=Integer.parseInt(gameSummaryForm.pa.get(i));
		pitching.hit=Integer.parseInt(gameSummaryForm.p_hit.get(i));
		pitching.homerun=Integer.parseInt(gameSummaryForm.p_homerun.get(i));
		pitching.fourBall=Integer.parseInt(gameSummaryForm.p_fourBall.get(i));
		pitching.strikeOut=Integer.parseInt(gameSummaryForm.p_strikeOut.get(i));
		pitching.runs=Integer.parseInt(gameSummaryForm.runs.get(i));
		pitching.complete=Integer.parseInt(gameSummaryForm.complete.get(i));
		pitching.shutout=Integer.parseInt(gameSummaryForm.shutout.get(i));
		if(gameSummaryForm.p_myTeamId.get(i).equals("0")){
			pitching.myteamId=Integer.parseInt(gameSummaryForm.firstTeam);
		}else if(gameSummaryForm.p_myTeamId.get(i).equals("1")){
			pitching.myteamId=Integer.parseInt(gameSummaryForm.lastTeam);
		}
		return pitching;
	}

	public List<GameListDto> convert2GameList(List<GameListDto> list){
		gameList=new ArrayList<GameListDto>();
		for(int i=0;i<list.size();i++){
			gameListDto=new GameListDto();
			gameListDto.gameId=list.get(i).gameId;
			gameListDto.gameDate=list.get(i).gameDate;
			if(i==0){
				gameListDto.title=list.get(i).title;
			}
			if(i>0 && !list.get(i-1).leagueId.equals(list.get(i).leagueId)){
				gameListDto.title=list.get(i).title;
				System.out.println(list.get(i-1).leagueId+","+list.get(i).leagueId);
			}
			gameList.add(gameListDto);
		}
		return gameList;

	}

}
