package cx.myhome.ckoshien.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.framework.beans.util.Beans;
import org.seasar.framework.container.annotation.tiger.Aspect;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import cx.myhome.ckoshien.dto.BattingResultDto;
import cx.myhome.ckoshien.dto.PlayerDto;
import cx.myhome.ckoshien.dto.TeamBattingResultDto;
import cx.myhome.ckoshien.dto.TeamPitchingResultDto;
import cx.myhome.ckoshien.entity.League;
import cx.myhome.ckoshien.entity.Player;
import cx.myhome.ckoshien.entity.Team;
import cx.myhome.ckoshien.form.PlayerForm;
import cx.myhome.ckoshien.service.BattingSumService;
import cx.myhome.ckoshien.service.LeagueService;
import cx.myhome.ckoshien.service.PitchingService;
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
	public List<PlayerDto> playerList;
	public Player player;
	@Resource
	public BattingSumService battingSumService;
	@Resource
	public LeagueService leagueService;
	public List<TeamBattingResultDto> pbrList;
	@Resource
	public PitchingService pitchingService;
	public List<TeamPitchingResultDto> pprList;
	public List<TeamBattingResultDto> pbrgoList;
	public List<TeamPitchingResultDto> pprgoList;
	public List<TeamBattingResultDto> tbrDtos;
	public List<League> leagueList;
	public List<TeamBattingResultDto> pbrlList;

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
	@Execute(validator = true,input="update/{id}",stopOnValidationError=false,validate="createValidate")
	public String updateComplete(){
		player=playerService.findById(Integer.parseInt(playerForm.id));
		player.id=Integer.parseInt(playerForm.id);
		player.name=playerForm.name;
		player.teamId=Integer.parseInt(playerForm.teamId);
		player.comment=playerForm.comment;
		playerService.update(player);
        return "index&redirect=true";
	}

	@Execute(urlPattern="show/{id}",validator = false)
	public String show(){
		player=playerService.findById(Integer.parseInt(playerForm.id));
		pbrList=battingSumService.findPBRById(Integer.parseInt(playerForm.id));
		pbrlList=battingSumService.findPBRLById(Integer.parseInt(playerForm.id));
		pbrgoList=battingSumService.findPBRGOById(Integer.parseInt(playerForm.id));
        pprList=pitchingService.findPPRById(Integer.parseInt(playerForm.id));
        pprgoList=pitchingService.findPPRGOById(Integer.parseInt(playerForm.id));
        tbrDtos=battingSumService.findPBRDById(Integer.parseInt(playerForm.id));
        leagueList=leagueService.findAllOrderByIdExceptTotal();
        return "result.jsp";
	}

	public ActionMessages createValidate(){
		ActionMessages errors = new ActionMessages();
		player=playerService.findByNameAndTeamId(playerForm.name, Integer.parseInt(playerForm.teamId));
		if(player!=null && playerForm.comment.equals(player.comment)){
			/* 既に選手リストに登録されていて、
			 * DBのコメントとフォームのコメントが同じ場合エラー
			 */
			errors.add("name", new ActionMessage("既に登録されています", false));
		}
		return errors;
	}
}
