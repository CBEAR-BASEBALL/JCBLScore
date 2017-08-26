package cx.myhome.ckoshien.action.api.v1;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Hex;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.arnx.jsonic.JSON;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.util.ResponseUtil;

import cx.myhome.ckoshien.dto.LoginUserDto;
import cx.myhome.ckoshien.dto.api.AuthApiDto;
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
	public String auth() {
		HttpSession session=request.getSession(false);
		String session_id = session.getId();
		String token="";
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			byte[] digest = md.digest(session_id.getBytes());
			token=String.valueOf(Hex.encodeHex(digest));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		AuthApiDto dto=new AuthApiDto();
		dto.token=token;
		loginUserDto.setToken(token);
		String json=JSON.encode(dto);
		ResponseUtil.write(json,"application/json");
		return null;
	}

	@Execute(validator = false)
	public String index() {
		// セッションへの保存処理
		// userDtoに値を入れるだけでSessionに保存される
		if(loginValidate()==false){
			try{
				response.sendError(400);
				return null;
			}catch(IOException e){
				logger.error(e);
			}
		}
		Player player = playerService.findByLoginId(playerForm.loginId);
        //Beans.copy(user, userForm).execute();
		Beans.copy(player, this.loginUserDto).execute();
		LoginUserDto res=this.loginUserDto;
		String json=JSON.encode(res);
		ResponseUtil.write(json,"application/json");
		return null;
	}

	/**
	 * ログイン時にユーザーIDとpasswordを照合する検証メソッド
	 * @return
	 */
	public boolean loginValidate() {
		ActionMessages errors = new ActionMessages();
		//emailかpasswordが空の場合エラー
		if(playerForm.loginId.equals("")|playerForm.password.equals("")){
			//errors.add("login", new ActionMessage("errors.login"));
			return false;
		}

		//パスワード照合エラー
		Player player = playerService.findByLoginId(playerForm.loginId);
		logger.info("[API]IP:"+request.getRemoteAddr());
		logger.info("[API]ポート:"+request.getRemotePort());
		logger.info("[API]"+request.getRemoteHost());
		logger.info("[API]ログインID:"+playerForm.loginId);
		logger.info("[API]パスワード:"+playerForm.password);
		if (player!=null){
			if (!player.password.equals(playerForm.password)) {
				//errors.add("login", new ActionMessage("errors.login"));
				logger.warn("[API]一致しないパスワード:"+playerForm.password);
				return false;
			}
			if (player.authority!=1) {
				//errors.add("login", new ActionMessage("errors.login"));
				return false;
			}
			if(!playerForm.token.equals(loginUserDto.getToken())){
				logger.warn("[API]セッションの不一致");
				return false;
			}
		}else{
			//errors.add("login", new ActionMessage("errors.login"));
			logger.warn("[API]存在しないログインID:"+playerForm.loginId);
			return false;
		}

		return true;
	}
}
