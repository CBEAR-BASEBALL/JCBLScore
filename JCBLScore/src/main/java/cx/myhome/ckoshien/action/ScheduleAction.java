package cx.myhome.ckoshien.action;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.seasar.framework.container.annotation.tiger.Aspect;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import cx.myhome.ckoshien.action.api.v1.WeatherAction;
import cx.myhome.ckoshien.action.api.v1.WeatherDto;
import cx.myhome.ckoshien.dto.PlanDto;
import cx.myhome.ckoshien.dto.PlayerDto;
import cx.myhome.ckoshien.dto.ScheduleDto;
import cx.myhome.ckoshien.entity.MSchedule;
import cx.myhome.ckoshien.entity.TSchedule;
import cx.myhome.ckoshien.entity.Weather;
import cx.myhome.ckoshien.form.ScheduleForm;
import cx.myhome.ckoshien.service.MScheduleService;
import cx.myhome.ckoshien.service.PlayerService;
import cx.myhome.ckoshien.service.TScheduleService;
import cx.myhome.ckoshien.service.WeatherService;
import cx.myhome.ckoshien.util.MemoryUtil;

public class ScheduleAction {
	@Resource
	protected MScheduleService mScheduleService;
	@Resource
	protected TScheduleService tScheduleService;
	@Resource
	protected PlayerService playerService;
	@Resource
	protected WeatherService weatherService;
	public List<MSchedule> mScheduleList;
	@Resource
	protected HttpServletRequest request;
	@Resource
	protected HttpServletResponse response;

	public List<ScheduleDto> scheduleList;
	public List<PlanDto> planList;
	public List<PlayerDto> playerList;
	public List<Weather> weatherList;
	@Resource
	@ActionForm
	private ScheduleForm scheduleForm;
	public Timestamp timestamp;
	private static Logger logger = Logger.getLogger("rootLogger");

	@Execute(validator = false)
	public String index(){
		try {
    		InetAddress ia=InetAddress.getByName(request.getRemoteAddr());
    		System.out.println(ia.getHostName());
//    		if(!ia.getHostName().substring(ia.getHostName().length()-3).equals(".jp")
//    				&& !request.getRemoteAddr().equals("0:0:0:0:0:0:0:1")
//    				&& !request.getRemoteAddr().startsWith("192.168")
//    				&& !ia.getHostName().substring(ia.getHostName().length()-4).equals(".net")){
//    			//logger.info("ホスト名で遮断:"+ia.getHostName()+":"+request.getRemotePort());
//    			//response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//    			try {
//    				response.sendError(404, "許可されていないドメインです");
//    			} catch (Exception e) {
//    				logger.error(e);
//    			}
//        		return null;
//    		}
    		logger.info(ia.getHostName()+":"+request.getRemotePort());
    	} catch (UnknownHostException e1) {
    		e1.printStackTrace();
    	}
		mScheduleList=mScheduleService.findAllOrderById();
		weatherList=weatherService.findAllOrderByRegTime();
		scheduleList= mScheduleService.findScheduleList();
		planList=mScheduleService.findAllPlan();
		playerList=playerService.findPlayerHasPlan();
		if(scheduleList.size()!=0){
			timestamp=scheduleList.get(0).getRegtime();
		}
		mScheduleService=null;
		weatherService=null;
		playerService=null;
		request=null;
		response=null;
		logger.info("/schedule");
		MemoryUtil.viewMemoryInfo();
		return "index.jsp";
	}

	@Aspect(value="loginConfInterceptor")
	@Execute(validator=false)
	public String create(){
		return "create.jsp";
	}

	@Aspect(value="loginConfInterceptor")
	@Execute(validator = true,input="create",stopOnValidationError=true)
	public String createComplete(){
		String[] scheduleArray=scheduleForm.getDate().split(",");
		//SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		for(int i=0;i<scheduleArray.length;i++){
			MSchedule mSchedule= new MSchedule();
			if(mScheduleService.findByDate(Date.valueOf(scheduleArray[i])).size()==0){
				mSchedule.date=Date.valueOf(scheduleArray[i]);
				mScheduleService.insert(mSchedule);
			}
		}
        return "index&redirect=true";
	}
}
