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
	public WeatherAction weatherAction;
	public HashMap<String,WeatherDto> response;
	public WeatherDto weatherDto;
	public List<ScheduleDto> scheduleList;
	public ScheduleDto scheduleDto;
	public List<PlanDto> planList;
	public List<PlayerDto> playerList;
	public String htmlStr;
	public List<Weather> weatherList;
	@Resource
	@ActionForm
	public ScheduleForm scheduleForm;
	private MSchedule mSchedule;
	private List<MSchedule> oldMScheduleList;
	private List<TSchedule> oldTScheduleList;
	public Timestamp timestamp;
	static Logger logger = Logger.getLogger("rootLogger");

	@Execute(validator = false)
	public String index(){
		//前日までのスケジュール削除
//		long t1 = System.currentTimeMillis();
//		oldMScheduleList=mScheduleService.findOldData();
//		for(int i=0;i<oldMScheduleList.size();i++){
//			oldTScheduleList=tScheduleService.findOldData(oldMScheduleList.get(i).id);
//			for(int j=0;j<oldTScheduleList.size();j++){
//				tScheduleService.delete(oldTScheduleList.get(j));
//			}
//			mScheduleService.delete(oldMScheduleList.get(i));
//		}
//		long t2 = System.currentTimeMillis();
//		if(t2-t1>1000){
//			logger.info("スケジュール削除処理:"+(t2-t1)+"mS");
//		}

		mScheduleList=mScheduleService.findAllOrderById();
		response=new HashMap<String,WeatherDto>();
		weatherList=weatherService.findAllOrderByRegTime();
//		boolean timeFlg=false;
//		if(weatherList.size()>0){
//			//現在時刻-3Hの取得
//			Calendar cal= Calendar.getInstance();
//			cal.add(Calendar.HOUR, -6);
//			Timestamp regtime=weatherList.get(0).regtime;
//			//System.out.println(cal.getTime());
//			if(regtime.before(cal.getTime())){
//			//6時間経過している場合
//				timeFlg=true;
//			}else{
//				//6時間経過していない場合DBから取得
//				timeFlg=false;
//			}
//		}else{
//			//DBにない場合
//			timeFlg=true;
//		}
//		if(timeFlg){
//			//テーブル全データ削除
//			long t3=System.currentTimeMillis();
//			for(int i=0;i<weatherList.size();i++){
//				weatherService.delete(weatherList.get(i));
//			}
//			long t4=System.currentTimeMillis();
//			WeatherAction weatherAction =new WeatherAction();
//			response=weatherAction.get();
//			long t5=System.currentTimeMillis();
//			String date="";
//			WeatherDto weatherDto= new WeatherDto();
//			for(Map.Entry<String, WeatherDto> e : response.entrySet()) {
//				date=e.getKey();
//				weatherDto=e.getValue();
//				Weather weatherBean= new Weather();
//				BeanUtil.copyProperties(weatherDto, weatherBean);
//				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//				java.util.Date formatDate = null;
//				String todayStr= new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()).replaceAll("-", "/");
//				int year=Calendar.getInstance().get(Calendar.YEAR);
//				java.util.Date today=null;
//				try {
//					formatDate = sdf.parse(year+"/"+date);
//					today=sdf.parse(todayStr);
//				} catch (ParseException e1) {
//					e1.printStackTrace();
//				}
//				if(today.compareTo(formatDate)>0){
//					weatherBean.date=Date.valueOf(String.valueOf(year+1)+"-"+date.replaceAll("/", "-"));
//				}else{
//					weatherBean.date=Date.valueOf(String.valueOf(year)+"-"+date.replaceAll("/", "-"));
//				}
//				weatherService.insert(weatherBean);
//			}
//			long t6=System.currentTimeMillis();
//			logger.info("天気テーブル全削除:"+(t4-t3));
//			logger.info("天気データ取得:"+(t5-t4));
//			logger.info("天気データinsert:"+(t6-t5));
//		}
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
			mSchedule= new MSchedule();
			if(mScheduleService.findByDate(Date.valueOf(scheduleArray[i])).size()==0){
				mSchedule.date=Date.valueOf(scheduleArray[i]);
				mScheduleService.insert(mSchedule);
			}
		}
        return "index&redirect=true";
	}
}
