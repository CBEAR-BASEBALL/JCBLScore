package cx.myhome.ckoshien.dto;

import java.util.List;

import cx.myhome.ckoshien.entity.BattingSum;
import cx.myhome.ckoshien.entity.Game;

public class GameListDto extends Game{

	private static final long serialVersionUID = 1L;
	public String title;
	public PitchingResultDto win;
	public PitchingResultDto lose;
	public PitchingResultDto save;
	public List<BattingResultDto> homerun;

}
