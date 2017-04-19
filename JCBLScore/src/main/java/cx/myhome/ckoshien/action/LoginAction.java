package cx.myhome.ckoshien.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import cx.myhome.ckoshien.dto.LoginUserDto;
import cx.myhome.ckoshien.entity.Player;
import cx.myhome.ckoshien.form.PlayerForm;
import cx.myhome.ckoshien.service.PlayerService;

public class LoginAction {
	@Resource
	public HttpServletRequest request;
	@Resource
	public HttpServletResponse response;

	@Resource
	@ActionForm
	public PlayerForm playerForm;

	@Resource
	protected LoginUserDto loginUserDto;

	@Resource
	protected PlayerService playerService;

	//public Player player;


	static Logger logger = Logger.getLogger("rootLogger");

	@Execute(validator = false)
	public String index() {
		return "/login/index.jsp";
	}

	@Execute(validator = false, input = "/login/index.jsp", validate = "loginValidate")
	public String login() {
		// セッションへの保存処理
		// userDtoに値を入れるだけでSessionに保存される
		Player player = playerService.findByLoginId(playerForm.loginId);
        //Beans.copy(user, userForm).execute();
		Beans.copy(player, this.loginUserDto).execute();
		return "../redirect=true";
	}

	/**
	 * ログイン時にユーザーIDとpasswordを照合する検証メソッド
	 * @return
	 */
	public ActionMessages loginValidate() {
		ActionMessages errors = new ActionMessages();
		//emailかpasswordが空の場合エラー
		if(playerForm.loginId.equals("")|playerForm.password.equals("")){
			errors.add("login", new ActionMessage("errors.login"));
			return errors;
		}

		//パスワード照合エラー
		Player player = playerService.findByLoginId(playerForm.loginId);
		logger.info("IP:"+request.getRemoteAddr());
		logger.info("ポート:"+request.getRemotePort());
		logger.info(request.getRemoteHost());
		logger.info("ログインID:"+playerForm.loginId);
		logger.info("パスワード:"+playerForm.password);
		if (player!=null){
			if (!player.password.equals(playerForm.password)) {
				errors.add("login", new ActionMessage("errors.login"));
				logger.warn("一致しないパスワード:"+playerForm.password);
			}
			if (player.authority!=1) {
				errors.add("login", new ActionMessage("errors.login"));
			}
		}else{
			errors.add("login", new ActionMessage("errors.login"));
			logger.warn("存在しないログインID:"+playerForm.loginId);
		}

		return errors;
	}
}
