package cx.myhome.ckoshien.action;

import java.util.List;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import cx.myhome.ckoshien.entity.Player;
import cx.myhome.ckoshien.entity.Team;
import cx.myhome.ckoshien.form.GameSummaryForm;
import cx.myhome.ckoshien.form.PlayerForm;
import cx.myhome.ckoshien.service.PlayerService;
import cx.myhome.ckoshien.service.TeamService;

public class GameSummaryAction {

@Resource
@ActionForm
public GameSummaryForm gameSummaryForm;
@Resource
public TeamService teamService;
@Resource
public PlayerService playerService;
public List<Team> teamList;
public List<Player> playerList;

	@Execute(validator = false)
	public String create(){
		teamList=teamService.findAllOrderById();
		playerList=playerService.findAllOrderById();
		return "create.jsp";
	}

	@Execute(validator = true,input="create?redirect=true")
	public String createComplete(){

		return "createComplete.jsp";
	}

}
