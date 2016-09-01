package cx.myhome.ckoshien.action.api.v1;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.arnx.jsonic.JSON;

import org.apache.log4j.Logger;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.seasar.framework.beans.util.BeanUtil;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.util.ResponseUtil;

import cx.myhome.ckoshien.entity.Weather;
import cx.myhome.ckoshien.service.WeatherService;

public class WeatherAction {
	public HashMap<String,WeatherDto> response;
	static Logger logger = Logger.getLogger("rootLogger");
	@Resource
	public WeatherService weatherService;
	private List<Weather> weatherList;

	@Execute(validator = false)
	public String index(){
		long t1 = System.currentTimeMillis();
		response=new HashMap<String,WeatherDto>();
		weatherList=weatherService.findAllOrderByRegTime();
		boolean timeFlg=false;
		if(weatherList.size()>0){
			//現在時刻-3Hの取得
			Calendar cal= Calendar.getInstance();
			cal.add(Calendar.HOUR, -3);
			Timestamp regtime=weatherList.get(0).regtime;
			System.out.println(cal.getTime());
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
			response=get();
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
		String json=JSON.encode(response);
		//System.out.println(json);
		long t2 = System.currentTimeMillis();
		System.out.println(t2-t1);
		ResponseUtil.write(json,"application/json");
		return null;

	}

	public HashMap<String, WeatherDto> get(){
		Document document;
		response=new HashMap<String,WeatherDto>();
		for(int i=0;i<6;i++){
			try{
				document = Jsoup.connect("http://www.accuweather.com/ja/jp/tokorozawa-shi/225818/daily-weather-forecast/225818?day="+(i*5+1)).get();
				Element element;
				if(i==0){
					element=document.getElementById("feed-tabs").child(1);
				}else{
					element=document.getElementById("feed-tabs").child(2);
				}
				for(int j=0;j<5;j++){
					WeatherDto weatherDto=new WeatherDto();
					Element row=element.child(j).child(1);//j日目
					String date=row.child(1).html().replaceAll(" ", "/");
					String img=row.child(2).attributes().get("class").replaceAll("icon i-", "").replaceAll(" ", "");
					String weather=row.child(3).child(0).text();
					String highTemp=row.child(3).child(1).text().replaceAll("°", "").replaceAll("最低", "");
					String lowTemp=row.child(3).child(2).text().replaceAll("°", "").replaceAll("最低", "").replaceAll(" ", "");
					weatherDto.setImg(img);
					weatherDto.setWeather(weather);
					weatherDto.setHighTemp(highTemp);
					weatherDto.setLowTemp(lowTemp);
					weatherDto.setRegTime(new Timestamp(System.currentTimeMillis()));
					response.put(date, weatherDto);
				}
			}catch(HttpStatusException hse){
				logger.error("[API]"+hse.getStatusCode());
				hse.printStackTrace();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		logger.info("長期予報が更新されました。");
		return response;
	}
}
