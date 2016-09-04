package cx.myhome.ckoshien.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import cx.myhome.ckoshien.dto.PlayerDto;
import cx.myhome.ckoshien.entity.MSchedule;
import cx.myhome.ckoshien.entity.TSchedule;
import cx.myhome.ckoshien.form.PlanForm;
import cx.myhome.ckoshien.service.MScheduleService;
import cx.myhome.ckoshien.service.PlayerService;
import cx.myhome.ckoshien.service.TScheduleService;

public class PlanAction {

	@Resource
	public MScheduleService mScheduleService;
	@Resource
	public TScheduleService tScheduleService;
	public List<MSchedule> mScheduleList;
	@Resource
	@ActionForm
	public PlanForm planForm;
	@Resource
	public PlayerService playerService;
	public List<PlayerDto> playerList;
	public TSchedule tSchedule;

	@Execute(validator=false)
	public String create(){
		mScheduleList=mScheduleService.findAllOrderById();
		playerList=playerService.findPlayerHasNoPlan();
		return "create.jsp";
	}

	@Execute(validator=false)
	public String createComplete(){
		mScheduleList=mScheduleService.findAllOrderById();
		for(int i=0;i<mScheduleList.size();i++){
			if(!planForm.getPlans().get(i).equals("")){
				tSchedule = new TSchedule();
				tSchedule.mstId=mScheduleList.get(i).id;
				tSchedule.plans=Integer.parseInt(planForm.getPlans().get(i));
				tSchedule.playerId=Integer.parseInt(planForm.getId());
				List<TSchedule> list = tScheduleService.findByPlayerId(Integer.parseInt(planForm.getId()));
				if(list.size()==0){
					tScheduleService.insert(tSchedule);
				}
			}
		}
		return "/schedule/index&redirect=true";
	}
}
