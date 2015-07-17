package cx.myhome.ckoshien.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.framework.beans.util.Beans;
import org.seasar.framework.container.annotation.tiger.Aspect;
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
	public List<Player> playerList;
	public Player player;

	@Execute(validator = false)
	public String index() {
		teamList=teamService.findAllOrderById();
		playerList=playerService.findAllOrderById();
        return "index.jsp";
	}

	@Aspect(value="loginConfInterceptor")
	@Execute(validator = false)
	public String create(){
		teamList=teamService.findAllOrderById();
		playerList=playerService.findAllOrderById();
        return "create.jsp";
	}

	@Aspect(value="loginConfInterceptor")
	@Execute(validator = true,input="create",stopOnValidationError=false,validate="createValidate")
	public String createComplete(){
		player = Beans.createAndCopy(Player.class, playerForm).execute();
		playerService.insert(player);
        return "index&redirect=true";
	}

	@Aspect(value="loginConfInterceptor")
	@Execute(urlPattern="update/{id}",validator = false)
	public String update(){
		teamList=teamService.findAllOrderById();
		player=playerService.findById(Integer.parseInt(playerForm.id));
		Beans.copy(player, playerForm).execute();
		playerList=playerService.findAllOrderById();
        return "update.jsp";
	}

	@Aspect(value="loginConfInterceptor")
	@Execute(validator = true,input="update",stopOnValidationError=false,validate="createValidate")
	public String updateComplete(){
		player=playerService.findById(Integer.parseInt(playerForm.id));
		player.id=Integer.parseInt(playerForm.id);
		player.name=playerForm.name;
		player.teamId=Integer.parseInt(playerForm.teamId);
		playerService.update(player);
        return "index&redirect=true";
	}

	public ActionMessages createValidate(){
		ActionMessages errors = new ActionMessages();
		player=playerService.findByNameAndTeamId(playerForm.name, Integer.parseInt(playerForm.teamId));
		if(player!=null){
			errors.add("name", new ActionMessage("既に登録されています", false));
		}
		return errors;
	}
}
