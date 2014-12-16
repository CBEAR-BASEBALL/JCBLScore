package cx.myhome.ckoshien.action;

import java.util.List;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import cx.myhome.ckoshien.entity.BattingDetail;
import cx.myhome.ckoshien.form.GameDetailForm;
import cx.myhome.ckoshien.form.TeamForm;
import cx.myhome.ckoshien.service.BattingDetailService;

public class GameDetailAction {

	@Resource
	@ActionForm
	public GameDetailForm gameDetailForm;

	@Resource
	public BattingDetailService battingDetailService;

	List<BattingDetail> firstAttackList;
	List<BattingDetail> LastAttackList;

	@Execute(validator = false)
	public String edit(int teamId, int gameId){
		firstAttackList=battingDetailService.findGameDetail(teamId, gameId);
        return "edit.jsp";
	}
	@Execute(validator = false)
	public String update(){
        return "update.jsp";
	}
}
