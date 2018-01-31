package cx.myhome.ckoshien.dto;

import java.sql.Date;

public class PlanDto {
	private Date date;
	private Integer playerId;
	private String name;
	private String scPassword;
	private Integer teamId;
	private String plans;
	private String team_name;
	private Integer mstId;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getPlayerId() {
		return playerId;
	}
	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getScPassword() {
		return scPassword;
	}
	public void setScPassword(String scPassword) {
		this.scPassword = scPassword;
	}
	public Integer getTeamId() {
		return teamId;
	}
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	public String getPlans() {
		return plans;
	}
	public void setPlans(String plans) {
		this.plans = plans;
	}
	public String getTeam_name() {
		return team_name;
	}
	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}
	public Integer getMstId() {
		return mstId;
	}
	public void setMstId(Integer mstId) {
		this.mstId = mstId;
	}

}
