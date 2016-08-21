package cx.myhome.ckoshien.form;

import org.seasar.struts.annotation.Required;

public class TeamForm {

	/*チーム名*/
	@Required
	public String teamName;
	@Required
	public String shortName;
	public String teamId;
	public String leagueId;
}
