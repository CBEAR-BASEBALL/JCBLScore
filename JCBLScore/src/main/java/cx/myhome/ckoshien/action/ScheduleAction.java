package cx.myhome.ckoshien.action;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;

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

public class ScheduleAction {
	@Resource
	public MScheduleService mScheduleService;
	@Resource
	public TScheduleService tScheduleService;
	@Resource
	public PlayerService playerService;
	@Resource
	public WeatherService weatherService;
	public List<MSchedule> mScheduleList;
	//public WeatherAction weatherAction;
	//public HashMap<String,WeatherDto> response;
	//public WeatherDto weatherDto;
	public List<ScheduleDto> scheduleList;
	//public ScheduleDto scheduleDto;
	public List<PlanDto> planList;
	public List<PlayerDto> playerList;
	//public String htmlStr;
	public List<Weather> weatherList;
	@Resource
	@ActionForm
	public ScheduleForm scheduleForm;
	//private MSchedule mSchedule;
	public Timestamp timestamp;
	static Logger logger = Logger.getLogger("rootLogger");

	@Execute(validator = false)
	public String index(){
		mScheduleList=mScheduleService.findAllOrderById();
		//HashMap<String,WeatherDto> response=new HashMap<String,WeatherDto>();
		weatherList=weatherService.findAllOrderByRegTime();
		scheduleList= mScheduleService.findScheduleList();
		planList=mScheduleService.findAllPlan();
		playerList=playerService.findPlayerHasPlan();
		if(scheduleList.size()!=0){
			timestamp=scheduleList.get(0).getRegtime();
		}
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
