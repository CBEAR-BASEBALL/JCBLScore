package cx.myhome.ckoshien.dto;

import cx.myhome.ckoshien.entity.TitleHolder;

public class TitleHolderDto extends TitleHolder{
	private String eventTypeStr;
	private Double average;
	private String avgPlayer;
	private Integer homerun;
	private String hrPlayer;
	private Integer rbi;
	private String rbiPlayer;
	private Integer hit;
	private String hitPlayer;
	private Double era;
	private String eraPlayer;
	private Integer win;
	private String winPlayer;
	private Integer save;
	private String savePlayer;
	private Integer strikeout;
	private String strikePlayer;
	private Integer totalFlg;
	private String name;
	private String shortTitle;
	private Integer year;

	public String getEventTypeStr() {
		return eventTypeStr;
	}
	public void setEventTypeStr(String eventTypeStr) {
		this.eventTypeStr = eventTypeStr;
	}
	public Double getAverage() {
		return average;
	}
	public void setAverage(Double average) {
		this.average = average;
	}
	public Integer getHomerun() {
		return homerun;
	}
	public void setHomerun(Integer homerun) {
		this.homerun = homerun;
	}
	public Integer getRbi() {
		return rbi;
	}
	public void setRbi(Integer rbi) {
		this.rbi = rbi;
	}
	public Integer getHit() {
		return hit;
	}
	public void setHit(Integer hit) {
		this.hit = hit;
	}
	public Double getEra() {
		return era;
	}
	public void setEra(Double era) {
		this.era = era;
	}
	public Integer getWin() {
		return win;
	}
	public void setWin(Integer win) {
		this.win = win;
	}
	public Integer getSave() {
		return save;
	}
	public void setSave(Integer save) {
		this.save = save;
	}
	public Integer getStrikeout() {
		return strikeout;
	}
	public void setStrikeout(Integer strikeout) {
		this.strikeout = strikeout;
	}
	public Integer getTotalFlg() {
		return totalFlg;
	}
	public void setTotalFlg(Integer totalFlg) {
		this.totalFlg = totalFlg;
	}
	public String getAvgPlayer() {
		return avgPlayer;
	}
	public void setAvgPlayer(String avgPlayer) {
		this.avgPlayer = avgPlayer;
	}
	public String getHrPlayer() {
		return hrPlayer;
	}
	public void setHrPlayer(String hrPlayer) {
		this.hrPlayer = hrPlayer;
	}
	public String getRbiPlayer() {
		return rbiPlayer;
	}
	public void setRbiPlayer(String rbiPlayer) {
		this.rbiPlayer = rbiPlayer;
	}
	public String getHitPlayer() {
		return hitPlayer;
	}
	public void setHitPlayer(String hitPlayer) {
		this.hitPlayer = hitPlayer;
	}
	public String getEraPlayer() {
		return eraPlayer;
	}
	public void setEraPlayer(String eraPlayer) {
		this.eraPlayer = eraPlayer;
	}
	public String getWinPlayer() {
		return winPlayer;
	}
	public void setWinPlayer(String winPlayer) {
		this.winPlayer = winPlayer;
	}
	public String getSavePlayer() {
		return savePlayer;
	}
	public void setSavePlayer(String savePlayer) {
		this.savePlayer = savePlayer;
	}
	public String getStrikePlayer() {
		return strikePlayer;
	}
	public void setStrikePlayer(String strikePlayer) {
		this.strikePlayer = strikePlayer;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShortTitle() {
		return shortTitle;
	}
	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
}
