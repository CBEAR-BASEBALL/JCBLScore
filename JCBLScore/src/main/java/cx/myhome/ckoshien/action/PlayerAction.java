package cx.myhome.ckoshien.action;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.framework.beans.util.Beans;
import org.seasar.framework.container.annotation.tiger.Aspect;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import cx.myhome.ckoshien.dto.PlayerDto;
import cx.myhome.ckoshien.dto.PositionDto;
import cx.myhome.ckoshien.dto.TeamBattingResultDto;
import cx.myhome.ckoshien.dto.TeamHistoryDto;
import cx.myhome.ckoshien.dto.TeamPitchingResultDto;
import cx.myhome.ckoshien.dto.TitleHolderDto;
import cx.myhome.ckoshien.entity.League;
import cx.myhome.ckoshien.entity.Player;
import cx.myhome.ckoshien.entity.Team;
import cx.myhome.ckoshien.entity.TitleHolder;
import cx.myhome.ckoshien.form.PlayerForm;
import cx.myhome.ckoshien.service.BattingSumService;
import cx.myhome.ckoshien.service.LeagueService;
import cx.myhome.ckoshien.service.PitchingService;
import cx.myhome.ckoshien.service.PlayerService;
import cx.myhome.ckoshien.service.TeamHistoryService;
import cx.myhome.ckoshien.service.TeamService;
import cx.myhome.ckoshien.service.TitleHolderService;
import cx.myhome.ckoshien.util.MemoryUtil;



public class PlayerAction {

	private static final int EVENT_TYPE_AVG=1;
	private static final int EVENT_TYPE_HR=2;
	private static final int EVENT_TYPE_RBI=3;
	private static final int EVENT_TYPE_HIT=4;
	private static final int EVENT_TYPE_ERA=5;
	private static final int EVENT_TYPE_WIN=6;
	private static final int EVENT_TYPE_SAVE=7;
	private static final int EVENT_TYPE_STRIKE=8;

	@Resource
	protected PlayerService playerService;
	@Resource
	protected TeamService teamService;
	@Resource
	@ActionForm
	private PlayerForm playerForm;

	public List<Team> teamList;
	public List<PlayerDto> playerList;
	public Player player;
	@Resource
	protected BattingSumService battingSumService;
	@Resource
	protected LeagueService leagueService;
	public List<TeamBattingResultDto> pbrList;
	@Resource
	public PitchingService pitchingService;
	public List<TeamPitchingResultDto> pprList;
	public List<TeamBattingResultDto> pbrgoList;
	public List<TeamPitchingResultDto> pprgoList;
	public List<TeamBattingResultDto> tbrDtos;
	public List<League> leagueList;
	public List<TeamBattingResultDto> pbrlList;
	public List<TeamPitchingResultDto> tprDtos;
	public List<TeamPitchingResultDto> pprlList;
	public List<PositionDto> posDtos;
	@Resource
	protected HttpServletRequest request;
	@Resource
	protected HttpServletResponse response;
	@Resource
	protected TeamHistoryService teamHistoryService;
	@Resource
	protected TitleHolderService titleHolderService;
	public List<TeamHistoryDto> teamHistoryDtoList;
	public List<TitleHolderDto> titleHolderList;
	private static Logger logger = Logger.getLogger("rootLogger");

	@Execute(validator = false)
	public String index() {
		try {
			InetAddress ia=InetAddress.getByName(request.getRemoteAddr());
		    System.out.println(ia.getHostName());
		    if(!ia.getHostName().substring(ia.getHostName().length()-3).equals(".jp")
		    		&& !request.getRemoteAddr().equals("0:0:0:0:0:0:0:1")
		    		&& !request.getRemoteAddr().startsWith("192.168")
		    		&& !ia.getHostName().substring(ia.getHostName().length()-4).equals(".net")
		    		&& !ia.getHostName().equals("127.0.0.1")
		    	){
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
		teamList=teamService.findTeamOrderByLastJoinedDate();
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
	@Execute(validator = true,input="update/{id}",stopOnValidationError=false,validate="updateValidate")
	public String updateComplete(){
		player=playerService.findById(Integer.parseInt(playerForm.id));
		player.id=Integer.parseInt(playerForm.id);
		player.name=playerForm.name;
		player.teamId=Integer.parseInt(playerForm.teamId);
		player.comment=playerForm.comment;
		playerService.update(player);
        return "index&redirect=true";
	}

	//@Execute(urlPattern="show/{id}",validator = false)
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
//		    	//logger.info("ホスト名で遮断:"+ia.getHostName()+":"+request.getRemotePort());
//		    	//response.setStatus(HttpServletResponse.SC_NOT_FOUND);
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
		long t1=System.currentTimeMillis();
		player=playerService.findById(Integer.parseInt(playerForm.id));
		pbrList=battingSumService.findPBRById(Integer.parseInt(playerForm.id));
		//タブ用の成績があるシーズンリスト(PersonalBattingResultbyLeagueId)
		pbrlList=battingSumService.findPBRLById(Integer.parseInt(playerForm.id));
		pbrgoList=battingSumService.findPBRGOById(Integer.parseInt(playerForm.id));
		pprList=pitchingService.findPPRById(Integer.parseInt(playerForm.id));
		pprgoList=pitchingService.findPPRGOById(Integer.parseInt(playerForm.id));
		tbrDtos=battingSumService.findPBRDById(Integer.parseInt(playerForm.id));
		//タブ用の投球成績明細
		tprDtos=pitchingService.findPPRDById(Integer.parseInt(playerForm.id));
		pprlList=pitchingService.findPPRLById(Integer.parseInt(playerForm.id));
		leagueList=leagueService.findAllOrderByIdExceptTotal();
		posDtos=battingSumService.countDiffensePositionById(Integer.parseInt(playerForm.id));
		teamHistoryDtoList=teamHistoryService.findTeamHistoryWithSeason(Integer.parseInt(playerForm.id));
		long t2=System.currentTimeMillis();
		logger.info("/player/show/"+playerForm.id+" "+player.name);
		MemoryUtil.viewMemoryInfo();
		logger.info(t2-t1+"ms");
		return "result.jsp";
	}

	//@Execute(urlPattern="detail/{id}",validator=false)
	public String detail(){
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
//		   	//logger.info("ホスト名で遮断:"+ia.getHostName()+":"+request.getRemotePort());
//		   	//response.setStatus(HttpServletResponse.SC_NOT_FOUND);
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
		player=playerService.findById(Integer.parseInt(playerForm.id));
		pbrList=battingSumService.findPBRById(Integer.parseInt(playerForm.id));
		pbrlList=battingSumService.findPBRLById(Integer.parseInt(playerForm.id));
		pbrgoList=battingSumService.findPBRGOById(Integer.parseInt(playerForm.id));
		pprList=pitchingService.findPPRById(Integer.parseInt(playerForm.id));
		pprgoList=pitchingService.findPPRGOById(Integer.parseInt(playerForm.id));
		tbrDtos=battingSumService.findPBRDById(Integer.parseInt(playerForm.id));
		//タブ用の投球成績明細
		tprDtos=pitchingService.findPPRDById(Integer.parseInt(playerForm.id));
		pprlList=pitchingService.findPPRLById(Integer.parseInt(playerForm.id));
		leagueList=leagueService.findAllOrderByIdExceptTotal();
		//posDtos=battingSumService.countDiffensePositionById(Integer.parseInt(playerForm.id));
		return "detail.jsp";
	}


	@Execute(urlPattern="show/{id}",validator = false)
	public String ajax(){
		long t1=System.currentTimeMillis();
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
//		    	//logger.info("ホスト名で遮断:"+ia.getHostName()+":"+request.getRemotePort());
//		    	//response.setStatus(HttpServletResponse.SC_NOT_FOUND);
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
		long t2=System.currentTimeMillis();
		player=playerService.findById(Integer.parseInt(playerForm.id));
		pbrlList=battingSumService.findPBRLById(Integer.parseInt(playerForm.id));
		pprlList=pitchingService.findPPRLById(Integer.parseInt(playerForm.id));
		teamHistoryDtoList=teamHistoryService.findTeamHistoryWithSeason(Integer.parseInt(playerForm.id));
		titleHolderList = new ArrayList<TitleHolderDto>();
		List<TitleHolderDto> tmpList = titleHolderService.findPersonalTitle(Integer.parseInt(playerForm.id));
		TitleHolderDto titleHolderDto= new TitleHolderDto();
		StringBuilder sbAvg=new StringBuilder();
		StringBuilder sbHr=new StringBuilder();
		StringBuilder sbRbi=new StringBuilder();
		StringBuilder sbHit=new StringBuilder();
		StringBuilder sbEra=new StringBuilder();
		StringBuilder sbWin=new StringBuilder();
		StringBuilder sbSave=new StringBuilder();
		StringBuilder sbStrike=new StringBuilder();
		for(int i=0;i<tmpList.size();i++){
			if(tmpList.get(i).eventType==EVENT_TYPE_AVG){
				sbAvg.append(tmpList.get(i).getYear());
				sbAvg.append(",");
				titleHolderDto.setAvgPlayer(new String(sbAvg));
			}else if(tmpList.get(i).eventType==EVENT_TYPE_HR){
				sbHr.append(tmpList.get(i).getYear());
				sbHr.append(",");
				titleHolderDto.setHrPlayer(new String(sbHr));
			}else if(tmpList.get(i).eventType==EVENT_TYPE_RBI){
				sbRbi.append(tmpList.get(i).getYear());
				sbRbi.append(",");
				titleHolderDto.setHrPlayer(new String(sbRbi));
			}else if(tmpList.get(i).eventType==EVENT_TYPE_HIT){
				sbHit.append(tmpList.get(i).getYear());
				sbHit.append(",");
				titleHolderDto.setHitPlayer(new String(sbHit));
			}else if(tmpList.get(i).eventType==EVENT_TYPE_ERA){
				sbEra.append(tmpList.get(i).getYear());
				sbEra.append(",");
				titleHolderDto.setEraPlayer(new String(sbEra));
			}else if(tmpList.get(i).eventType==EVENT_TYPE_WIN){
				sbWin.append(tmpList.get(i).getYear());
				sbWin.append(",");
				titleHolderDto.setWinPlayer(new String(sbWin));
			}else if(tmpList.get(i).eventType==EVENT_TYPE_SAVE){
				sbSave.append(tmpList.get(i).getYear());
				sbSave.append(",");
				titleHolderDto.setSavePlayer(new String(sbSave));
			}else if(tmpList.get(i).eventType==EVENT_TYPE_STRIKE){
				sbStrike.append(tmpList.get(i).getYear());
				sbStrike.append(",");
				titleHolderDto.setStrikePlayer(new String(sbStrike));
			}
		}
		titleHolderList.add(titleHolderDto);
		logger.info("/player/show/"+playerForm.id+" "+player.name);
		MemoryUtil.viewMemoryInfo();
		logger.info("lookup:"+(t2-t1)+"ms");
		return "ajaxResult.jsp";
	}

	public ActionMessages createValidate(){
		ActionMessages errors = new ActionMessages();
		List<Player> players=playerService.findByName(playerForm.name);
		if(players.size()>=1){
			errors.add("name", new ActionMessage("既に登録されています", false));
		}
		return errors;
	}

	public ActionMessages updateValidate(){
		ActionMessages errors = new ActionMessages();
		List<Player> players=playerService.findByNameExceptSelf(playerForm.name,Integer.parseInt(playerForm.id));
		if(players.size()>=1){
			errors.add("name", new ActionMessage("既に登録されています", false));
		}
		return errors;
	}
}
