package cx.myhome.ckoshien.action;

import javax.annotation.Resource;

import org.seasar.struts.annotation.Execute;

import cx.myhome.ckoshien.service.PlayerService;
import cx.myhome.ckoshien.service.TeamService;

public class PlayerActon {

	@Resource
	public PlayerService playerService;
	@Resource
	public TeamService teamService;

	@Execute(validator=false)
	public String create(){

		return "createA-1.jsp";
	}
}
