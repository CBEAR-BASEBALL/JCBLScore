package cx.myhome.ckoshien.action;



import java.util.List;

import javax.annotation.Resource;

import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import cx.myhome.ckoshien.entity.Team;
import cx.myhome.ckoshien.form.TeamForm;
import cx.myhome.ckoshien.service.TeamService;

public class TeamAction {
	@Resource
	public TeamService teamService;
	@Resource
	@ActionForm
	public TeamForm teamForm;

	public List<Team> teamList;

	public Team team;

	//チーム一覧表示
	@Execute(validator = false)
	public String index() {
		teamList=teamService.findAllOrderById();
        return "index.jsp";
	}

	/*チーム新規作成 */
	@Execute(validator = false)
	public String create(){
        return "create.jsp";
	}

	@Execute(validator = false)
	public String createComplete(){
		team = Beans.createAndCopy(Team.class, teamForm).execute();
		teamService.insert(team);
        return "createComplete.jsp";
	}
}
