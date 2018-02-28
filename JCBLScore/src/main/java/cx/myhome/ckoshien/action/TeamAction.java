package cx.myhome.ckoshien.action;



import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	@Resource
	protected HttpServletRequest request;
	@Resource
	protected HttpServletResponse response;
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
		//アクセス制限
		try {
			InetAddress ia=InetAddress.getByName(request.getRemoteAddr());
			if(!ia.getHostName().substring(ia.getHostName().length()-3).equals(".jp")
					&& !request.getRemoteAddr().equals("0:0:0:0:0:0:0:1")
					&& !request.getRemoteAddr().startsWith("192.168")
					&& !ia.getHostName().substring(ia.getHostName().length()-4).equals(".net")
					&& !ia.getHostName().equals("127.0.0.1")
				    && !ia.getHostName().equals(request.getRemoteAddr())
			){
			logger.info("遮断:"+ia.getHostName()+":"+request.getRemotePort());
			//response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			try {
				response.sendError(404, "jp、netドメインのみ許可しています。"+ia.getHostName());
			} catch (IOException e) {
				logger.error(e);
			}
			return null;
		}
		logger.info(ia.getHostName()+":"+request.getRemotePort());
		} catch (Exception e1) {
			logger.warn(e1);
		}
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
		tbrDtos=battingSumService.findTBRByPeriod(league.beginDate, league.endDate, Integer.parseInt(teamForm.teamId));
		return "tbresult.jsp";

	}

	@Execute(urlPattern="pitching/{teamId}/{leagueId}",validator = false)
	public String pitching(){
		//アクセス制限
		try {
			InetAddress ia=InetAddress.getByName(request.getRemoteAddr());
			if(!ia.getHostName().substring(ia.getHostName().length()-3).equals(".jp")
				&& !request.getRemoteAddr().equals("0:0:0:0:0:0:0:1")
				&& !request.getRemoteAddr().startsWith("192.168")
				&& !ia.getHostName().substring(ia.getHostName().length()-4).equals(".net")
				&& !ia.getHostName().equals("127.0.0.1")
		    	&& !ia.getHostName().equals(request.getRemoteAddr())
			){
			logger.info("遮断:"+ia.getHostName()+":"+request.getRemotePort());
			//response.setStatus(HttpServletResponse.SC_NOT_FOUND);
 			try {
 				response.sendError(404, "jp、netドメインのみ許可しています。"+ia.getHostName());
 			} catch (IOException e) {
				logger.error(e);
			}
 			return null;
		}
		logger.info(ia.getHostName()+":"+request.getRemotePort());
		} catch (Exception e1) {
			logger.warn(e1);
		}
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
