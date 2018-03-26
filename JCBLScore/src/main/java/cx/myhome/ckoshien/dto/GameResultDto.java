package cx.myhome.ckoshien.dto;

import java.util.HashMap;

public class GameResultDto {
	public Integer teamId;
	public Integer rank;
	public String teamName;
	public String shortName;
	public Integer gameCount;
	public Integer win;
	public Integer lose;
	public Integer draw;
	public Double percentage;
	public Integer points;
	public Integer leagueId;
	public Integer opponent;
	public Double avg;
	public Double era;
	public HashMap<Integer, String> opponentMap;
	public Double gameBehind;
}
