package cx.myhome.ckoshien.dto.api;

import java.util.List;

import cx.myhome.ckoshien.dto.BattingResultDto;
import cx.myhome.ckoshien.dto.GameResultDto;
import cx.myhome.ckoshien.dto.PitchingResultDto;
import cx.myhome.ckoshien.entity.Game;
import cx.myhome.ckoshien.entity.League;

public class ResultApiDto {
	public List<League> leagueList;
	public League league;
	public List<BattingResultDto> battingResultList;
	public List<PitchingResultDto> pitchingResultList;
	public List<GameResultDto> resultList;
	public int length;
	//public List<GameResultDto> opponentList;
	//public List<GameResultDto> resultList2;
	public List<BattingResultDto> averageTop10;
	public List<BattingResultDto> homerunTop10;
	public List<BattingResultDto> rbiTop10;
	public List<BattingResultDto> hitTop10;
	public List<PitchingResultDto> eraTop10;
	public List<PitchingResultDto> winTop10;
	public List<PitchingResultDto> saveTop10;
	public List<PitchingResultDto> strikeOutTop10;
	public List<BattingResultDto> obpTop10;
	public List<BattingResultDto> twobaseTop10;
	public List<BattingResultDto> slgTop10;
	public List<BattingResultDto> fourBallTop10;
	public List<BattingResultDto> opsTop10;
	public List<BattingResultDto> nsoTop10;
	public List<BattingResultDto> avgHRTop10;
	public List<BattingResultDto> avgRBITop10;
	public Game game;
	public int regAtBats;
	public int regAtPitch;
	public int listSize;
	public List<Game> gameList;
	public Integer totalLeagueId;
	public List<NonTitleDto> nonTitle;

}
