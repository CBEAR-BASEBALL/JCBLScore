package cx.myhome.ckoshien.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.annotation.UrlType;

import cx.myhome.ckoshien.dto.PlayerDto;
import cx.myhome.ckoshien.entity.MSchedule;
import cx.myhome.ckoshien.entity.TSchedule;
import cx.myhome.ckoshien.form.PlanForm;
import cx.myhome.ckoshien.rest.SlackLogger;
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
	@Resource
	protected HttpServletRequest request;
	@Resource
	protected HttpServletResponse response;
	static Logger logger = Logger.getLogger("rootLogger");
	private SlackLogger slackLogger=new SlackLogger();

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
		logger.info("IP:"+request.getRemoteAddr());
		logger.info("ポート:"+request.getRemotePort());
		logger.info(request.getRemoteHost());
		logger.info(request.getHeader("user-agent"));
		//slackLogger.info("playerId="+planForm.getId()+"のスケジュールが更新されました。");
		return "/schedule/index&redirect=true";
	}

	@Execute(urlPattern="update/{id}",validator=false)
	public String update(){
		mScheduleList=mScheduleService.findAllOrderById();
		List<TSchedule> list=tScheduleService.findByPlayerId(Integer.parseInt(planForm.getId()));
		List<String> plans=new ArrayList<String>(mScheduleList.size());
		for(int i=0;i<mScheduleList.size();i++){
			plans.add(null);
		}
		for(int j=0;j<list.size();j++){
			for(int i=0; i<mScheduleList.size();i++){
				if(list.get(j).mstId.equals(mScheduleList.get(i).id)){
					plans.set(mScheduleList.get(i).id-1, list.get(j).plans.toString());
				}
			}
		}
		planForm.setPlans(plans);
		return "update.jsp";

	}

	@Execute(validator=false)
	public String updateComplete(){
		mScheduleList=mScheduleService.findAllOrderById();
		List<TSchedule> list = tScheduleService.findByPlayerId(Integer.parseInt(planForm.getId()));
		for(int i=0;i<list.size();i++){
			tScheduleService.delete(list.get(i));
		}
		for(int i=0;i<mScheduleList.size();i++){
			if(!planForm.getPlans().get(i).equals("")){
				tSchedule = new TSchedule();
				tSchedule.mstId=mScheduleList.get(i).id;
				tSchedule.plans=Integer.parseInt(planForm.getPlans().get(i));
				tSchedule.playerId=Integer.parseInt(planForm.getId());
				tScheduleService.insert(tSchedule);
			}
		}
		logger.info("IP:"+request.getRemoteAddr());
		logger.info("ポート:"+request.getRemotePort());
		logger.info(request.getRemoteHost());
		logger.info(request.getHeader("user-agent"));
		slackLogger.info("playerId="+planForm.getId()+"のスケジュールが更新されました。");
		return "/schedule/index&redirect=true";

	}
}
