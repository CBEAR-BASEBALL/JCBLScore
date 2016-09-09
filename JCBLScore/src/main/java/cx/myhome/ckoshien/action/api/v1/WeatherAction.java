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
import org.jsoup.select.Elements;
import org.seasar.framework.beans.util.BeanUtil;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.util.ResponseUtil;

import cx.myhome.ckoshien.entity.Weather;
import cx.myhome.ckoshien.rest.SlackLogger;
import cx.myhome.ckoshien.service.WeatherService;

public class WeatherAction {
	public HashMap<String,WeatherDto> response;
	static Logger logger = Logger.getLogger("rootLogger");
	@Resource
	public WeatherService weatherService;
	private List<Weather> weatherList;
	private SlackLogger slackLogger=new SlackLogger();

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
		boolean errFlg=false;
		for(int i=0;i<6;i++){
			try{
				document = Jsoup.connect("http://www.accuweather.com/ja/jp/tokorozawa-shi/225818/daily-weather-forecast/225818?day="+(i*5+1)).get();
				Element element;
				Elements dateList=null;
				Element row=null;
				dateList=document.select("#feed-tabs > ul ");//日付のリスト
				row=dateList.get(1);
				for(int j=0;j<5;j++){
					WeatherDto weatherDto=new WeatherDto();
					String date=row.child(j).child(0).child(1).text().replaceAll("月", "/").replaceAll("日", "");//日付
					String img=row.child(j).child(0).child(2).attributes().get("class").replaceAll("icon i-", "").replaceAll(" ", "").replaceAll("l", "m");//画像
					if(img.indexOf("-")==1){
						//一桁の場合0埋め
						img="0"+img;
					}
					String highTemp=row.child(j).child(0).child(3).child(0).child(0).text().replaceAll("°", "");//最高気温
					String lowTemp=row.child(j).child(0).child(3).child(0).child(1).text().replaceAll("°", "").replaceAll("C", "").replaceAll("/", "");//最低
					String weather=row.child(j).child(0).child(3).child(1).text();//天気
					weatherDto.setImg(img);
					weatherDto.setWeather(weather);
					weatherDto.setHighTemp(highTemp);
					weatherDto.setLowTemp(lowTemp);
					weatherDto.setRegTime(new Timestamp(System.currentTimeMillis()));
					System.out.println(date+" "+img+" "+weather+" "+highTemp+"/"+lowTemp);
					response.put(date, weatherDto);
				}

			}catch(HttpStatusException hse){
				logger.error("[API]"+hse.getStatusCode());
				hse.printStackTrace();
				errFlg=true;
			}catch(Exception e){
				logger.error("長期予報の更新に失敗しました。");
				//slackLogger.info("長期予報の更新に失敗しました。");
				e.printStackTrace();
				errFlg=true;
				break;
			}
		}
		if(!errFlg){
			logger.info("長期予報が更新されました。");
			slackLogger.info("長期予報が更新されました。");
		}
		return response;
	}
}
