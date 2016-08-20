package cx.myhome.ckoshien.action.api.v1;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;

import net.arnx.jsonic.JSON;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.util.ResponseUtil;

public class WeatherAction {
	public static HashMap<String,WeatherDto> response;

	@Execute(validator = false)
	public String index() throws IOException{
		long t1 = System.currentTimeMillis();
		response=new HashMap<>();
		Document document;
		for(int i=0;i<6;i++){
			try{
				document = Jsoup.connect("http://www.accuweather.com/ja/jp/tokorozawa-shi/225818/daily-weather-forecast/225818?day="+(i*5+1)).get();
			}catch(HttpStatusException hse){
				hse.printStackTrace();
				throw hse;
			}
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
				String lowTemp=row.child(3).child(2).text().replaceAll("°", "").replaceAll("最低", "");
				weatherDto.setImg(img);
				weatherDto.setWeather(weather);
				weatherDto.setHighTemp(highTemp);
				weatherDto.setLowTemp(lowTemp);
				response.put(date, weatherDto);
				//System.out.println(date+" "+img+" "+weather+" "+highTemp+"/"+lowTemp);
			}

		}
		String json=JSON.encode(response);
		//System.out.println(json);
		long t2 = System.currentTimeMillis();
		System.out.println(t2-t1);
		ResponseUtil.write(json,"application/json");
		return null;

	}
}
