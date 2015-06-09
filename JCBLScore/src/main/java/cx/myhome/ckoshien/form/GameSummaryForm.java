package cx.myhome.ckoshien.form;

import java.util.List;

public class GameSummaryForm {

	//試合結果関連
	public String gameYear;
	public String gameMonth;
	public String gameDay;
	public String gameNumber;
	public String firstTeam;
	public String lastTeam;
	public String firstRun;
	public String lastRun;
	public String top1st;
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
	public List<Integer> playerId;
	//打数
	public List<Integer> atBats;
	//安打
	public List<Integer> hit;
	//打点
	public List<Integer> rbi;
	public List<Integer> fourBall;
	public List<Integer> strikeOut;
	public List<Integer> twoBase;
	public List<Integer> homerun;
}
