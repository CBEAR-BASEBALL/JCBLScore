package cx.myhome.ckoshien.action;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cx.myhome.ckoshien.dto.TeamHistoryDto;
import cx.myhome.ckoshien.entity.League;
import cx.myhome.ckoshien.entity.Player;
import cx.myhome.ckoshien.entity.Team;
import cx.myhome.ckoshien.entity.TeamHistory;
import cx.myhome.ckoshien.form.TeamHistoryForm;
import cx.myhome.ckoshien.service.LeagueService;
import cx.myhome.ckoshien.service.PlayerService;
import cx.myhome.ckoshien.service.TeamHistoryService;
import cx.myhome.ckoshien.service.TeamService;

import org.apache.log4j.Logger;
import org.seasar.framework.beans.util.Beans;
import org.seasar.framework.container.annotation.tiger.Aspect;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

public class TeamHistoryAction {
	@Resource
	protected PlayerService playerService;
	public Player player;
	@Resource
	protected HttpServletRequest request;
	@Resource
	protected HttpServletResponse response;
	private static Logger logger = Logger.getLogger("rootLogger");
	@Resource
	@ActionForm
	private TeamHistoryForm teamHistoryForm;
	@Resource
	protected TeamHistoryService teamHistoryService;
	@Resource
	protected TeamService teamService;
	public List<Team> teamList;
	public List<TeamHistory> teamHistoryList;
	public List<TeamHistoryDto> teamHistoryDtoList;
	public TeamHistory teamHistory;
	public Integer recordId;
	@Resource
	protected LeagueService leagueService;
	public List<League> leagueList;

//	@Execute(validator = false)
//	public String index(){
//		return null;
//	}

	@Aspect(value="loginConfInterceptor")
	@Execute(urlPattern="create/{playerId}",validator = false)
	public String create(){
		player=playerService.findById(Integer.parseInt(teamHistoryForm.playerId));
		teamHistoryDtoList =teamHistoryService.findTeamHistoryWithSeason(Integer.parseInt(teamHistoryForm.playerId));
		teamList=teamService.findTeamOrderByLastJoinedDate();
		leagueList=leagueService.findAllOrderByIdExceptTotal();
		return "create.jsp";
	}

	@Aspect(value="loginConfInterceptor")
	@Execute(validator = false)
	public String createComplete(){
		player=playerService.findById(Integer.parseInt(teamHistoryForm.playerId));
		teamHistory=Beans.createAndCopy(TeamHistory.class, teamHistoryForm).execute();
		teamHistoryService.insert(teamHistory);
        return  "/teamHistory/show/"+teamHistoryForm.playerId+"&redirect=true";
	}

	@Execute(urlPattern="show/{playerId}",validator = false)
	public String show(){
		//アクセス制限
		try {
			InetAddress ia=InetAddress.getByName(request.getRemoteAddr());
		    System.out.println(ia.getHostName());
		    if(!ia.getHostName().substring(ia.getHostName().length()-3).equals(".jp")
		    		&& !request.getRemoteAddr().equals("0:0:0:0:0:0:0:1")
		    		&& !request.getRemoteAddr().startsWith("192.168")
		    		&& !ia.getHostName().substring(ia.getHostName().length()-4).equals(".net")
		    		&& !ia.getHostName().equals("127.0.0.1")
		    	){
		    		//logger.info("ホスト名で遮断:"+ia.getHostName()+":"+request.getRemotePort());
//				    //response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				    try {
						response.sendError(404, "許可されていないドメインです");
					} catch (IOException e) {
						logger.error(e);
					}
				    return null;
		    }
				logger.info(ia.getHostName()+":"+request.getRemotePort());
			} catch (Exception e1) {
				e1.printStackTrace();
		}
		player=new Player();
		teamHistoryDtoList=new ArrayList<TeamHistoryDto>();
		player=playerService.findById(Integer.parseInt(teamHistoryForm.playerId));
		teamHistoryDtoList=teamHistoryService.findTeamHistoryWithSeason(Integer.parseInt(teamHistoryForm.playerId));
		return "index.jsp";

	}

	@Aspect(value="loginConfInterceptor")
	@Execute(urlPattern="edit/{playerId}/{id}",validator = false)
	public String edit(){
		recordId=Integer.parseInt(teamHistoryForm.id);
		player=new Player();
		teamHistoryDtoList=new ArrayList<TeamHistoryDto>();
		teamList= new ArrayList<Team>();
		player=playerService.findById(Integer.parseInt(teamHistoryForm.playerId));
		teamHistoryDtoList=teamHistoryService.findTeamHistoryWithSeason(Integer.parseInt(teamHistoryForm.playerId));
		teamHistory = teamHistoryService.findById(Integer.parseInt(teamHistoryForm.id));
		teamList=teamService.findTeamOrderByLastJoinedDate();
		leagueList=leagueService.findAllOrderByIdExceptTotal();
		Beans.copy(teamHistory, teamHistoryForm);
		teamHistoryForm.teamId=teamHistory.teamId.toString();
		teamHistoryForm.startLeagueId=teamHistory.startLeagueId.toString();
		if(null!=teamHistory.endLeagueId){
			teamHistoryForm.endLeagueId=teamHistory.endLeagueId.toString();
		}
		return "edit.jsp";

	}

	@Aspect(value="loginConfInterceptor")
	@Execute(validator=true,input="edit/{id}",
			stopOnValidationError=false)
	public String update(){
		teamHistory=Beans.createAndCopy(TeamHistory.class,teamHistoryForm).execute();
		teamHistory.id=Integer.parseInt(teamHistoryForm.id);
		teamHistoryService.update(teamHistory);
		return "/teamHistory/show/"+teamHistoryForm.playerId+"&redirect=true";

	}
}
