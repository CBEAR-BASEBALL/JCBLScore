package cx.myhome.ckoshien.action;



import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.seasar.framework.beans.util.Beans;
import org.seasar.framework.container.annotation.tiger.Aspect;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import cx.myhome.ckoshien.dto.TeamBattingResultDto;
import cx.myhome.ckoshien.dto.TeamPitchingResultDto;
import cx.myhome.ckoshien.entity.League;
import cx.myhome.ckoshien.entity.Team;
import cx.myhome.ckoshien.form.TeamForm;
import cx.myhome.ckoshien.service.BattingSumService;
import cx.myhome.ckoshien.service.LeagueService;
import cx.myhome.ckoshien.service.PitchingService;
import cx.myhome.ckoshien.service.TeamService;

public class TeamAction {
	@Resource
	protected TeamService teamService;
	@Resource
	@ActionForm
	public TeamForm teamForm;

	@Resource
	protected LeagueService leagueService;
	public List<Team> teamList;

	//private Team team;
	//private League league;

	@Resource
	protected BattingSumService battingSumService;
	public List<TeamBattingResultDto> tbrDtos;
	public List<League> leagueList;
	@Resource
	protected PitchingService pitchingService;
	public List<TeamPitchingResultDto> tprDtos;
	private static Logger logger = Logger.getLogger("rootLogger");

	//チーム一覧表示
	@Execute(validator = false)
	public String index() {
		teamList=teamService.findAllOrderById();
		teamForm.leagueId=leagueService.findTotal().get(0).id.toString();
        return "index.jsp";
	}

	/*チーム新規作成 */
	@Aspect(value="loginConfInterceptor")
	@Execute(validator = false)
	public String create(){
        return "create.jsp";
	}

	@Aspect(value="loginConfInterceptor")
	@Execute(validator = true,input="create.jsp")
	public String createComplete(){
		Team team = Beans.createAndCopy(Team.class, teamForm).execute();
		team.jcblFlg=0;
		teamService.insert(team);
        return "createComplete.jsp";
	}

	@Aspect(value="loginConfInterceptor")
	@Execute(urlPattern="update/{teamId}",validator = false)
	public String update(){
		teamList=teamService.findAllOrderById();
		Team team=teamService.findById(Integer.parseInt(teamForm.teamId));
		Beans.copy(team, teamForm).execute();
		return "update.jsp";
	}

	@Aspect(value="loginConfInterceptor")
	@Execute(validator = false,input="update/{id}",stopOnValidationError=false)
	public String updateComplete(){
		Team team=teamService.findById(Integer.parseInt(teamForm.teamId));
		Beans.copy(teamForm, team).execute();
		teamService.update(team);
        return "index&redirect=true";
	}

	@Execute(urlPattern="batting/{teamId}/{leagueId}",validator = false)
	public String batting(){
		League league;
		if(teamForm.leagueId==null){
			teamForm.leagueId=leagueService.findTotal().get(0).id.toString();
		}
		try{
			league=leagueService.findById(Integer.parseInt(teamForm.leagueId));
		}catch(NumberFormatException e){
			return "index&redirect=true";
		}
		leagueList=leagueService.findTotal();
		tbrDtos=battingSumService.findTBRByPeriod(league.beginDate, league.endDate, Integer.parseInt(teamForm.teamId),league.id);
		return "tbresult.jsp";

	}

	@Execute(urlPattern="pitching/{teamId}/{leagueId}",validator = false)
	public String pitching(){
		League league;
		if(teamForm.leagueId==null){
			teamForm.leagueId=leagueService.findTotal().get(0).id.toString();
		}
		try{
			league=leagueService.findById(Integer.parseInt(teamForm.leagueId));
		}catch(NumberFormatException e){
			return "index&redirect=true";
		}
		leagueList=leagueService.findTotal();
		tprDtos=pitchingService.findTPRByPeriod(league.beginDate, league.endDate, Integer.parseInt(teamForm.teamId));
		return "tpresult.jsp";

	}
}
