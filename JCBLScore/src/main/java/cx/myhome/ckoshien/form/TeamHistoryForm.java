package cx.myhome.ckoshien.form;

import org.seasar.struts.annotation.Required;

public class TeamHistoryForm {

	/*プレイヤーID*/
	public String playerId;
	/*レコードID*/
	public String id;
	/*チームID*/
	@Required
	public String teamId;
	/*開始シーズン*/
	@Required
	public String startLeagueId;
	/*終了シーズン*/
	public String endLeagueId;

	}

