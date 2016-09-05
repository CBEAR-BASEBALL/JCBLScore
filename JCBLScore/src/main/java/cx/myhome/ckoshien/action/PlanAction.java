package cx.myhome.ckoshien.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.annotation.UrlType;

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
	private List<TSchedule> tScheduleList;

	@Execute(validator=false)
	public String create(){
		mScheduleList=mScheduleService.findAllOrderById();
		playerList=playerService.findPlayerHasNoPlan();
		return "create.jsp";
	}

	@Execute(validator=true,input="create",stopOnValidationError=false)
	public String createComplete(){
		mScheduleList=mScheduleService.findAllOrderById();
		List<TSchedule> list = tScheduleService.findByPlayerId(Integer.parseInt(planForm.getId()));
		if(list.size()==0){
			for(int i=0;i<mScheduleList.size();i++){
				if(!planForm.getPlans().get(i).equals("")){
					tSchedule = new TSchedule();
					tSchedule.mstId=mScheduleList.get(i).id;
					tSchedule.plans=Integer.parseInt(planForm.getPlans().get(i));
					tSchedule.playerId=Integer.parseInt(planForm.getId());
					tScheduleService.insert(tSchedule);

				}
			}
		}
		return "/schedule/index&redirect=true";
	}

	@Execute(urlPattern="update/{id}",validator=false)
	public String update(){
		mScheduleList=mScheduleService.findAllOrderById();
		List<TSchedule> list=tScheduleService.findByPlayerId(Integer.parseInt(planForm.getId()));
		List<String> plans=new ArrayList<String>();
		for(int i=0; i<mScheduleList.size();i++){
			if(i<list.size()){
				plans.add(String.valueOf(list.get(i).plans));
			}else{
				plans.add(null);
			}
		}
		planForm.setPlans(plans);
		return "update.jsp";

	}

	@Execute(validator=false)
	public String updateComplete(){
		mScheduleList=mScheduleService.findAllOrderById();
		tScheduleList=tScheduleService.findByPlayerId(Integer.parseInt(planForm.getId()));
		for(int i=0;i<mScheduleList.size();i++){
			if(planForm.getPlans().get(i).equals("")){
				tScheduleList=tScheduleService.findByPlayerIdAndMstId(Integer.parseInt(planForm.getId()), mScheduleList.get(i).id);
				for(int j=0;j<tScheduleList.size();j++){
					tScheduleService.delete(tScheduleList.get(j));
				}
			}else{
				if(i<tScheduleList.size()){
					tSchedule = tScheduleList.get(i);
					tSchedule.mstId=mScheduleList.get(i).id;
					tSchedule.plans=Integer.parseInt(planForm.getPlans().get(i));
					tSchedule.playerId=Integer.parseInt(planForm.getId());
					tScheduleService.update(tSchedule);
				}else{
					//新規スケジュール
					tSchedule = new TSchedule();
					tSchedule.mstId=mScheduleList.get(i).id;
					tSchedule.plans=Integer.parseInt(planForm.getPlans().get(i));
					tSchedule.playerId=Integer.parseInt(planForm.getId());
					tScheduleService.insert(tSchedule);
				}
			}
		}
		return "/schedule/index&redirect=true";

	}
}
