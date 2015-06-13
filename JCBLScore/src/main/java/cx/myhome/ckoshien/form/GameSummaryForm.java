package cx.myhome.ckoshien.form;

import java.util.List;

import org.seasar.struts.annotation.Required;

public class GameSummaryForm {

	//試合結果関連
	@Required
	public String gameYear;
	@Required
	public String gameMonth;
	@Required
	public String gameDay;
	public String gameDate;
	@Required
	public String gameNumber;
	@Required
	public String firstTeam;
	@Required
	public String lastTeam;
	@Required
	public String firstRun;
	@Required
	public String lastRun;
	@Required
	public String top1st;
	@Required
	public String bottom1st;
	public String top2nd;
	public String bottom2nd;
	public String top3rd;
	public String bottom3rd;
	public String top4th;
	public String bottom4th;
	public String top5th;
	public String bottom5th;


	//打撃成績関連
	public List<String> playerId;
	//打席数
	public List<String> tpa;
	//打数
	public List<String> atBats;
	//安打
	public List<String> hit;
	//打点
	public List<String> rbi;
	public List<String> fourBall;
	public List<String> strikeOut;
	public List<String> twoBase;
	public List<String> homerun;
	//
	public List<String> myTeamId;
	public List<String> teamId;
	public List<String> gameId;
}
