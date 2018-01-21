package cx.myhome.ckoshien.action;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.util.TokenProcessor;
import org.seasar.framework.util.ResourceUtil;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.util.RequestUtil;

import cx.myhome.ckoshien.dto.PlayerDto;
import cx.myhome.ckoshien.entity.MSchedule;
import cx.myhome.ckoshien.entity.Player;
import cx.myhome.ckoshien.entity.TSchedule;
import cx.myhome.ckoshien.form.PlanForm;
import cx.myhome.ckoshien.rest.PushbulletClient;
import cx.myhome.ckoshien.rest.SlackLogger;
import cx.myhome.ckoshien.service.MScheduleService;
import cx.myhome.ckoshien.service.PlayerService;
import cx.myhome.ckoshien.service.TScheduleService;
import cx.myhome.ckoshien.util.MemoryUtil;

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
	//public TSchedule tSchedule;
	@Resource
	protected HttpServletRequest request;
	@Resource
	protected HttpServletResponse response;
	static Logger logger = Logger.getLogger("rootLogger");
	private SlackLogger slackLogger=new SlackLogger();
	//private PushbulletClient bullet=new PushbulletClient(ResourceUtil.getProperties("config.properties").getProperty("PUSHBULLET_TOKEN"));;

	@Execute(validator=false)
	public String create(){
		mScheduleList=mScheduleService.findAllOrderByDate();
		playerList=playerService.findPlayerHasNoPlan();
		//トークンを設定する
		TokenProcessor.getInstance().saveToken(RequestUtil.getRequest());
		return "create.jsp";
	}

	@Execute(validator=true,input="create",stopOnValidationError=false)
	public String createComplete(){
		if (!TokenProcessor.getInstance().isTokenValid(RequestUtil.getRequest(), true)) {
			//不正なトークンの場合。エラー画面を表示
			return "twice.jsp";
		}
		Player player = playerService.findById(Integer.parseInt(planForm.getIdHidden()));
		mScheduleList=mScheduleService.findAllOrderByDate();
		List<TSchedule> list = tScheduleService.findByPlayerId(Integer.parseInt(planForm.getIdHidden()));
		if(list.size()==0){
			for(int i=0;i<mScheduleList.size();i++){
				if(!planForm.getPlans().get(i).equals("")){
					TSchedule tSchedule = new TSchedule();
					tSchedule.mstId=mScheduleList.get(i).id;
					tSchedule.plans=Integer.parseInt(planForm.getPlans().get(i));
					tSchedule.playerId=Integer.parseInt(planForm.getIdHidden());
					tScheduleService.insert(tSchedule);

				}
			}
		}
		logger.info(player.name+"さんが予定を入力しました。");
		try{
			InetAddress ia=InetAddress.getByName(request.getRemoteAddr());
			logger.info(ia.getHostName()+":"+request.getRemotePort());
		}catch(UnknownHostException e1){
			e1.printStackTrace();
		}
		logger.info(request.getHeader("user-agent"));
		//slackLogger.info(player.name+"さんが予定を入力しました。");
		//bullet.sendPush("note", "JCBLスコア管理システム", player.name+"さんが予定を入力しました。",null, null, null, null, null, null, null, "jcbl", null, null);
		MemoryUtil.viewMemoryInfo();
		return "/schedule/index&redirect=true";
	}

	@Execute(urlPattern="update/{id}",validator=false)
	public String update(){
		mScheduleList=mScheduleService.findAllOrderByDate();
		List<TSchedule> list=tScheduleService.findByPlayerId(Integer.parseInt(planForm.getId()));
		List<String> plans=new ArrayList<String>(mScheduleList.size());
		for(int i=0;i<mScheduleList.size();i++){
			plans.add(null);
		}
		for(int j=0;j<list.size();j++){
			for(int i=0; i<mScheduleList.size();i++){
				if(list.get(j).mstId.equals(mScheduleList.get(i).id)){
					plans.set(i, list.get(j).plans.toString());
				}
			}
		}
		planForm.setPlans(plans);
		//トークンを設定する
		TokenProcessor.getInstance().saveToken(RequestUtil.getRequest());
		return "update.jsp";

	}

	@Execute(validator=false)
	public String updateComplete(){
		if (!TokenProcessor.getInstance().isTokenValid(RequestUtil.getRequest(), true)) {
			//不正なトークンの場合。エラー画面を表示
			return "twice.jsp";
		}
		Player player = playerService.findById(Integer.parseInt(planForm.getId()));
		mScheduleList=mScheduleService.findAllOrderByDate();
		List<TSchedule> list = tScheduleService.findByPlayerId(Integer.parseInt(planForm.getId()));
		for(int i=0;i<list.size();i++){
			tScheduleService.delete(list.get(i));
		}
		for(int i=0;i<mScheduleList.size();i++){
			if(!planForm.getPlans().get(i).equals("")){
				TSchedule tSchedule = new TSchedule();
				tSchedule.mstId=mScheduleList.get(i).id;
				tSchedule.plans=Integer.parseInt(planForm.getPlans().get(i));
				tSchedule.playerId=Integer.parseInt(planForm.getId());
				tScheduleService.insert(tSchedule);
			}
		}
		logger.info(player.name+"さんが予定を変更しました。");
		try{
			InetAddress ia=InetAddress.getByName(request.getRemoteAddr());
			logger.info(ia.getHostName()+":"+request.getRemotePort());
		}catch(UnknownHostException e1){
			e1.printStackTrace();
		}
		logger.info(request.getHeader("user-agent"));
		//slackLogger.info(player.name+"さんが予定を変更しました");
		//bullet.sendPush("note", "JCBLスコア管理システム", player.name+"さんが予定を変更しました",null, null, null, null, null, null, null, "jcbl", null, null);
		MemoryUtil.viewMemoryInfo();
		return "/schedule/index&redirect=true";

	}
}
