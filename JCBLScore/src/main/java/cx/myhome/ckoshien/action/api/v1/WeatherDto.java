package cx.myhome.ckoshien.action.api.v1;

public class WeatherDto {
	private String img;
	private String weather;
	private String highTemp;
	private String lowTemp;
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	public String getHighTemp() {
		return highTemp;
	}
	public void setHighTemp(String highTemp) {
		this.highTemp = highTemp;
	}
	public String getLowTemp() {
		return lowTemp;
	}
	public void setLowTemp(String lowTemp) {
		this.lowTemp = lowTemp;
	}
}
