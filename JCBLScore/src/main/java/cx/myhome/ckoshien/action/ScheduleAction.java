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
import org.seasar.struts.annotation.Execute;

import cx.myhome.ckoshien.action.api.v1.WeatherAction;
import cx.myhome.ckoshien.action.api.v1.WeatherDto;
import cx.myhome.ckoshien.dto.PlanDto;
import cx.myhome.ckoshien.dto.PlayerDto;
import cx.myhome.ckoshien.dto.ScheduleDto;
import cx.myhome.ckoshien.entity.MSchedule;
import cx.myhome.ckoshien.entity.Weather;
import cx.myhome.ckoshien.service.MScheduleService;
import cx.myhome.ckoshien.service.PlayerService;
import cx.myhome.ckoshien.service.TScheduleService;
import cx.myhome.ckoshien.service.WeatherService;

public class ScheduleAction {
	@Resource
	public MScheduleService mScheduleService;
	@Resource
	public PlayerService playerService;
	@Resource
	public WeatherService weatherService;
	public List<MSchedule> mScheduleList;
	public WeatherAction weatherAction;
	public HashMap<String,WeatherDto> response;
	private WeatherDto weatherDto;
	public List<ScheduleDto> scheduleList;
	private ScheduleDto scheduleDto;
	public List<PlanDto> planList;
	private List<PlayerDto> playerList;
	public String htmlStr;
	private List<Weather> weatherList;


	@Execute(validator = false)
	public String index(){
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
		playerList=playerService.findAllOrderById();
		HashMap<HashMap<Integer,Integer>,Integer> plans= new HashMap<HashMap<Integer,Integer>,Integer>();
		//HashMap<<playerId,mstId>,plans>>
		HashMap<Integer,Integer> planMap=new HashMap<Integer,Integer>();
		for(int i=0;i<planList.size();i++){
			for(int j=0;j<playerList.size();j++){
				if(planList.get(i).getPlayerId().equals(playerList.get(j).id)){
					planMap=new HashMap<Integer,Integer>();
					planMap.put(playerList.get(j).id,planList.get(i).getMstId());
					plans.put(planMap, planList.get(i).getPlans());
				}
			}

		}


		StringBuilder sb=new StringBuilder();
		boolean flg=false;
		for(int i=0;i<playerList.size();i++){
			flg=false;
			for(int j=0;j<planList.size();j++){
				if(playerList.get(i).id.equals(planList.get(j).getPlayerId())){
					flg = true;
					sb.append("<tr>\n");
					sb.append("<td class=\"left-box\"><img width=\"15\" height=\"15\" src=\"../img/"+playerList.get(i).teamId+".jpg\">"+playerList.get(i).name+"</td>\n");
					//sb.append("<td>"+playerList.get(i).name+"</td>\n");
					break;
				}
			}
			if(flg){
				for(int k=0;k<mScheduleList.size();k++){
					HashMap<Integer,Integer> keyMap= new HashMap<Integer,Integer>();
					keyMap.put(playerList.get(i).id, mScheduleList.get(k).id);
					if(plans.get(keyMap)!=null){
						if(plans.get(keyMap).equals(1)){
							sb.append("<td id=\"selection01\">△</td>\n");
						}else if(plans.get(keyMap).equals(2)){
							sb.append("<td id=\"selection02\">○</td>\n");
						}else if(plans.get(keyMap).equals(3)){
							sb.append("<td id=\"selection03\">◎</td>\n");
						}else {
							sb.append("<td>×</td>\n");
						}

					}else{
						sb.append("<td></td>\n");
					}
				}
				sb.append("</tr>\n");
			}

		}
		htmlStr=new String(sb);
		return "index.jsp";
	}
}
