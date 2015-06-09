package cx.myhome.ckoshien.action;

import java.util.List;

import javax.annotation.Resource;

import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import cx.myhome.ckoshien.entity.Player;
import cx.myhome.ckoshien.entity.Team;
import cx.myhome.ckoshien.form.PlayerForm;
import cx.myhome.ckoshien.service.PlayerService;
import cx.myhome.ckoshien.service.TeamService;



public class PlayerAction {

	@Resource
	public PlayerService playerService;
	@Resource
	public TeamService teamService;
	@Resource
	@ActionForm
	public PlayerForm playerForm;

	public List<Team> teamList;
	public Player player;

	@Execute(validator = false)
	public String index() {
		teamList=teamService.findAllOrderById();
        return "index.jsp";
	}

	@Execute(validator = false)
	public String create(){
		teamList=teamService.findAllOrderById();
        return "create.jsp";
	}

	@Execute(validator = true,input="create?redirect=true")
	public String createComplete(){
		player = Beans.createAndCopy(Player.class, playerForm).execute();
		playerService.insert(player);
        return "createComplete.jsp";
	}
}
