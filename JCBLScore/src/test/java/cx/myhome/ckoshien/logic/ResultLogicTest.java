package cx.myhome.ckoshien.logic;

import java.util.ArrayList;
import java.util.List;

import org.seasar.extension.unit.S2TestCase;

import cx.myhome.ckoshien.dto.BattingResultDto;

public class ResultLogicTest extends S2TestCase{
	public void testReturnHomerunTop10(){
		List<BattingResultDto> battingResultList=new ArrayList<BattingResultDto>();
		BattingResultDto battingResultDto = new BattingResultDto();
		battingResultDto.homerun=5;
		battingResultList.add(battingResultDto);
		battingResultDto = new BattingResultDto();
		battingResultDto.homerun=7;
		battingResultList.add(battingResultDto);
		battingResultDto = new BattingResultDto();
		battingResultDto.homerun=4;
		battingResultList.add(battingResultDto);
		battingResultDto = new BattingResultDto();
		battingResultDto.homerun=2;
		battingResultList.add(battingResultDto);
		battingResultDto = new BattingResultDto();
		battingResultDto.homerun=2;
		battingResultList.add(battingResultDto);
		battingResultDto = new BattingResultDto();
		battingResultDto.homerun=3;
		battingResultList.add(battingResultDto);
		battingResultDto = new BattingResultDto();
		battingResultDto.homerun=2;
		battingResultList.add(battingResultDto);
		ResultLogic logic=new ResultLogic();
		List<BattingResultDto> homerunTop10 = logic.returnHomerunTop10(battingResultList);
		assertEquals(7, homerunTop10.get(0).homerun.intValue());
		assertEquals(5, homerunTop10.get(1).homerun.intValue());
		assertEquals(4, homerunTop10.get(2).homerun.intValue());
		assertEquals(3, homerunTop10.get(3).homerun.intValue());
		assertEquals(2, homerunTop10.get(4).homerun.intValue());
		//5位タイ
		assertEquals(null, homerunTop10.get(5).rank);
	}

}
