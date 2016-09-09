package cx.myhome.ckoshien.dto;

import java.sql.Date;
import java.sql.Timestamp;

public class ScheduleDto {
	private Integer id;
	private String weather;
	private Integer count;
	private String hightemp;
	private String lowtemp;
	private String img;
	private Date date;
	private Timestamp regtime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getHightemp() {
		return hightemp;
	}
	public void setHightemp(String hightemp) {
		this.hightemp = hightemp;
	}
	public String getLowtemp() {
		return lowtemp;
	}
	public void setLowtemp(String lowtemp) {
		this.lowtemp = lowtemp;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Timestamp getRegtime() {
		return regtime;
	}
	public void setRegtime(Timestamp regtime) {
		this.regtime = regtime;
	}


}
