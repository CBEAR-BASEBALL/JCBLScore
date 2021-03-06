package cx.myhome.ckoshien.form;

import org.seasar.struts.annotation.Maxlength;
import org.seasar.struts.annotation.Required;

public class PlayerForm {

	/*メンバー名*/
	@Required
	public String name;
	/*チームID*/
	public String teamId;

	/*ログインID*/
	public String loginId;
	/*パスワード*/
	public String password;

	public String id;

	public String token;

	@Maxlength(maxlength=1000)
	public String comment;
}
