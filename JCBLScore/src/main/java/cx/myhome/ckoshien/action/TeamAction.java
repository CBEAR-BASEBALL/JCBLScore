package cx.myhome.ckoshien.action;



import java.util.List;

import javax.annotation.Resource;

import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import cx.myhome.ckoshien.dto.TeamBattingResultDto;
import cx.myhome.ckoshien.entity.League;
import cx.myhome.ckoshien.entity.Team;
import cx.myhome.ckoshien.form.TeamForm;
import cx.myhome.ckoshien.service.BattingSumService;
import cx.myhome.ckoshien.service.LeagueService;
import cx.myhome.ckoshien.service.TeamService;

public class TeamAction {
	@Resource
	public TeamService teamService;
	@Resource
	@ActionForm
	public TeamForm teamForm;

	@Resource
	public LeagueService leagueService;
	public List<Team> teamList;

	public Team team;
	public League league;

	@Resource
	public BattingSumService battingSumService;
	public List<TeamBattingResultDto> tbrDtos;
	public List<League> leagueList;

	//チーム一覧表示
	@Execute(validator = false)
	public String index() {
		teamList=teamService.findAllOrderById();
		teamForm.leagueId=leagueService.findTotal().get(0).id.toString();
        return "index.jsp";
	}

	/*チーム新規作成 */
	@Execute(validator = false)
	public String create(){
        return "create.jsp";
	}

	@Execute(validator = true,input="create.jsp")
	public String createComplete(){
		team = Beans.createAndCopy(Team.class, teamForm).execute();
		teamService.insert(team);
        return "createComplete.jsp";
	}

	@Execute(urlPattern="batting/{teamId}/{leagueId}",validator = false)
	public String batting(){
		if(teamForm.leagueId==null){
			teamForm.leagueId=leagueService.findTotal().get(0).id.toString();
		}
		try{
			league=leagueService.findById(Integer.parseInt(teamForm.leagueId));
		}catch(NumberFormatException e){
			return "index&redirect=true";
		}
		leagueList=leagueService.findTotal();
		tbrDtos=battingSumService.findTBRByPeriod(league.beginDate, league.endDate, Integer.parseInt(teamForm.teamId));
		return "result.jsp";

	}
}
