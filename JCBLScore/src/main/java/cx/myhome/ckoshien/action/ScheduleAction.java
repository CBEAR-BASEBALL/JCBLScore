package cx.myhome.ckoshien.action;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.seasar.framework.beans.util.BeanUtil;
import org.seasar.framework.beans.util.Beans;
import org.seasar.framework.container.annotation.tiger.Aspect;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import cx.myhome.ckoshien.action.api.v1.WeatherAction;
import cx.myhome.ckoshien.action.api.v1.WeatherDto;
import cx.myhome.ckoshien.dto.PlanDto;
import cx.myhome.ckoshien.dto.PlayerDto;
import cx.myhome.ckoshien.dto.ScheduleDto;
import cx.myhome.ckoshien.entity.MSchedule;
import cx.myhome.ckoshien.entity.Player;
import cx.myhome.ckoshien.entity.TSchedule;
import cx.myhome.ckoshien.entity.Weather;
import cx.myhome.ckoshien.form.PlayerForm;
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

	@Execute(validator = false)
	public String index(){
		//前日までのスケジュール削除
		oldMScheduleList=mScheduleService.findOldData();
		for(int i=0;i<oldMScheduleList.size();i++){
			oldTScheduleList=tScheduleService.findOldData(oldMScheduleList.get(i).id);
			for(int j=0;j<oldTScheduleList.size();j++){
				tScheduleService.delete(oldTScheduleList.get(j));
			}
			mScheduleService.delete(oldMScheduleList.get(i));
		}
		mScheduleList=mScheduleService.findAllOrderById();
		response=new HashMap<String,WeatherDto>();
		weatherList=weatherService.findAllOrderByRegTime();
		boolean timeFlg=false;
		if(weatherList.size()>0){
			//現在時刻-3Hの取得
			Calendar cal= Calendar.getInstance();
			cal.add(Calendar.HOUR, -3);
			Timestamp regtime=weatherList.get(0).regtime;
			//System.out.println(cal.getTime());
			if(regtime.before(cal.getTime())){
			//3時間経過している場合
				timeFlg=true;
			}else{
				//3時間経過していない場合DBから取得
				timeFlg=false;
			}
		}else{
			//DBにない場合
			timeFlg=true;
		}
		if(timeFlg){
			//テーブル全データ削除
			for(int i=0;i<weatherList.size();i++){
				weatherService.delete(weatherList.get(i));
			}
			WeatherAction weatherAction =new WeatherAction();
			response=weatherAction.get();
			String date="";
			WeatherDto weatherDto= new WeatherDto();
			for(Map.Entry<String, WeatherDto> e : response.entrySet()) {
				date=e.getKey();
				weatherDto=e.getValue();
				Weather weatherBean= new Weather();
				BeanUtil.copyProperties(weatherDto, weatherBean);
				int year=Calendar.getInstance().get(Calendar.YEAR);
				weatherBean.date=Date.valueOf(String.valueOf(year)+"-"+date.replaceAll("/", "-"));
				//weatherBean.regtime=new Timestamp(System.currentTimeMillis());
				weatherService.insert(weatherBean);
			}
		}else{
			for(int i=0;i<weatherList.size();i++){
				WeatherDto weatherDto= new WeatherDto();
				Weather weatherBean=weatherList.get(i);
				BeanUtil.copyProperties(weatherBean,weatherDto);
				SimpleDateFormat sdf= new SimpleDateFormat("M/d");
				response.put(sdf.format(weatherBean.date), weatherDto);
			}

		}
		SimpleDateFormat sdf= new SimpleDateFormat("M/d");
		scheduleList= new ArrayList<ScheduleDto>();
		for(int i=0;i<mScheduleList.size();i++){
			scheduleDto= new ScheduleDto();
			System.out.println(sdf.format(mScheduleList.get(i).date).toString());
			weatherDto=response.get(sdf.format(mScheduleList.get(i).date).toString());
			scheduleDto.setWeatherDto(weatherDto);
			scheduleDto.id=mScheduleList.get(i).id;
			scheduleDto.date=mScheduleList.get(i).date;
			scheduleList.add(scheduleDto);
		}
		planList=mScheduleService.findAllPlan();
		playerList=playerService.findPlayerHasPlan();
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
