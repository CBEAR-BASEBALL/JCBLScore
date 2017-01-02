package cx.myhome.ckoshien.task;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import org.seasar.chronos.core.annotation.task.Task;
import org.seasar.chronos.core.annotation.trigger.CronTrigger;
import org.seasar.framework.beans.util.BeanUtil;

import cx.myhome.ckoshien.action.api.v1.WeatherAction;
import cx.myhome.ckoshien.action.api.v1.WeatherDto;
import cx.myhome.ckoshien.entity.Weather;
import cx.myhome.ckoshien.service.WeatherService;

@Task
@CronTrigger(expression = "0 15 */6 * * ?")//6時間ごと
public class GetWeatherTask {
	@Resource
	public WeatherService weatherService;
	public List<Weather> weatherList;
	public HashMap<String,WeatherDto> response;

	private static Logger logger = Logger.getLogger("rootLogger");
	// タスク処理
	public void doExecute() {
		logger.info("タスク開始");
		weatherList=weatherService.findAllOrderByRegTime();
		response=new HashMap<String,WeatherDto>();
		//テーブル全データ削除
		long t3=System.currentTimeMillis();
		for(int i=0;i<weatherList.size();i++){
			weatherService.delete(weatherList.get(i));
		}
		long t4=System.currentTimeMillis();
		WeatherAction weatherAction =new WeatherAction();
		response=weatherAction.get();
		long t5=System.currentTimeMillis();
		String date="";
		WeatherDto weatherDto= new WeatherDto();
		for(Map.Entry<String, WeatherDto> e : response.entrySet()) {
			date=e.getKey();
			weatherDto=e.getValue();
			Weather weatherBean= new Weather();
			BeanUtil.copyProperties(weatherDto, weatherBean);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			java.util.Date formatDate = null;
			String todayStr= new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()).replaceAll("-", "/");
			int year=Calendar.getInstance().get(Calendar.YEAR);
			java.util.Date today=null;
			try {
				formatDate = sdf.parse(year+"/"+date);
				today=sdf.parse(todayStr);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			if(today.compareTo(formatDate)>0){
				weatherBean.date=Date.valueOf(String.valueOf(year+1)+"-"+date.replaceAll("/", "-"));
			}else{
				weatherBean.date=Date.valueOf(String.valueOf(year)+"-"+date.replaceAll("/", "-"));
			}
			weatherService.insert(weatherBean);
		}
		long t6=System.currentTimeMillis();
		logger.info("天気テーブル全削除:"+(t4-t3));
		logger.info("天気データ取得:"+(t5-t4));
		logger.info("天気データinsert:"+(t6-t5));
		logger.info("タスク終了");
	}

	 // 破棄処理
	public void destroy() {
    	logger.info(this.getClass().getSimpleName() + ":destroy");
    }
}
