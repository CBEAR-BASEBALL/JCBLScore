package cx.myhome.ckoshien.form;

import java.util.List;

import org.seasar.struts.annotation.IntRange;
import org.seasar.struts.annotation.Required;

public class GameSummaryForm {

	//試合結果関連
	public String id;
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
	@IntRange(min=0,max=40)
	public String firstRun;
	@Required
	@IntRange(min=0,max=40)
	public String lastRun;
	@Required
	@IntRange(min=0,max=20)
	public String top1st;
	@Required
	@IntRange(min=0,max=20)
	public String bottom1st;
	@IntRange(min=0,max=20)
	public String top2nd;
	@IntRange(min=0,max=20)
	public String bottom2nd;
	@IntRange(min=0,max=20)
	public String top3rd;
	@IntRange(min=0,max=20)
	public String bottom3rd;
	@IntRange(min=0,max=20)
	public String top4th;
	@IntRange(min=0,max=20)
	public String bottom4th;
	@IntRange(min=0,max=20)
	public String top5th;
	@IntRange(min=0,max=20)
	public String bottom5th;


	//打撃成績関連
	public List<String> playerRecordId;
	public List<String> playerId;
	//打席数
	@IntRange(min=1,max=8)
	public List<String> tpa;
	//打数
	@IntRange(min=0,max=8)
	public List<String> atBats;
	//安打
	@IntRange(min=0,max=8)
	public List<String> hit;
	//打点
	@IntRange(min=0,max=10)
	public List<String> rbi;
	@IntRange(min=0,max=8)
	public List<String> fourBall;
	@IntRange(min=0,max=8)
	public List<String> strikeOut;
	@IntRange(min=0,max=8)
	public List<String> twoBase;
	@IntRange(min=0,max=8)
	public List<String> homerun;
	//
	public List<String> myTeamId;
	public List<String> teamId;
	public List<String> gameId;

	//投球成績関連
	public List<String> p_playerId;
	@IntRange(min=0,max=5)
	public List<String> inning1;
	@IntRange(min=0,max=2)
	public List<String> inning2;
	@IntRange(min=1,max=48)
	public List<String> pa;
	@IntRange(min=0,max=40)
	public List<String> p_hit;
	@IntRange(min=0,max=10)
	public List<String> p_homerun;
	@IntRange(min=0,max=20)
	public List<String> p_fourBall;
	@IntRange(min=0,max=15)
	public List<String> p_strikeOut;
	@IntRange(min=0,max=40)
	public List<String> runs;
	@IntRange(min=0,max=1)
	public List<String> complete;
	@IntRange(min=0,max=1)
	public List<String> shutout;
	public List<String> result;
	public List<String> p_myTeamId;

}
