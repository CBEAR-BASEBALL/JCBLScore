package cx.myhome.ckoshien.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


public class BattingResultDto implements Serializable {
	private static final long serialVersionUID = 1L;

	public Integer playerId;
	public Integer rank;
	public String teamId;
	public String name;
	public String teamName;
	public Double average;

	public Double slg;

	public Double ops;

	public Double obp;

	public Double notStrikeOut;
	public Double avgHomerun;
	public Double avgRbi;
	public Double rc27;

	public Integer tpa;

	public Integer gameCount;

	public Integer atBats;

	public Integer hit;

	public Integer rbi;

	public Integer fourBall;

	public Integer strikeOut;

	public Integer twobase;

	public Integer homerun;
	public Integer homerunCount;
	public Integer year;

}
