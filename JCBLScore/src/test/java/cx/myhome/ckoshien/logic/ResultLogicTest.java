package cx.myhome.ckoshien.logic;

import static org.junit.Assert.assertThat;

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
		assertEquals(5, homerunTop10.get(5).rank.intValue());
	}

	public void testReturnAverageTop10(){
		List<BattingResultDto> battingResultList=new ArrayList<BattingResultDto>();
		BattingResultDto battingResultDto = new BattingResultDto();
		battingResultDto.average=0.600;
		battingResultDto.tpa=42;
		battingResultList.add(battingResultDto);
		battingResultDto = new BattingResultDto();
		battingResultDto.average=0.500;
		battingResultDto.tpa=42;
		battingResultList.add(battingResultDto);
		battingResultDto = new BattingResultDto();
		battingResultDto.average=0.400;
		battingResultDto.tpa=42;
		battingResultList.add(battingResultDto);
		battingResultDto = new BattingResultDto();
		battingResultDto.average=0.400;
		battingResultDto.tpa=42;
		battingResultList.add(battingResultDto);
		battingResultDto = new BattingResultDto();
		battingResultDto.average=0.300;
		battingResultDto.tpa=42;
		battingResultList.add(battingResultDto);
		battingResultDto = new BattingResultDto();
		battingResultDto.average=0.200;
		battingResultDto.tpa=42;
		battingResultList.add(battingResultDto);
		battingResultDto = new BattingResultDto();
		battingResultDto.average=0.550;
		battingResultDto.tpa=42;
		battingResultList.add(battingResultDto);
		battingResultDto = new BattingResultDto();
		battingResultDto.average=0.350;
		battingResultDto.tpa=42;
		battingResultList.add(battingResultDto);
		ResultLogic logic=new ResultLogic();
		List<BattingResultDto> resultList = logic.returnAverageTop10(battingResultList, 42);
		assertEquals(0.600,resultList.get(0).average);
		assertEquals(1,resultList.get(0).rank.intValue());
		assertEquals(0.550,resultList.get(1).average);
		assertEquals(2,resultList.get(1).rank.intValue());
		assertEquals(0.500,resultList.get(2).average);
		assertEquals(2,resultList.get(2).rank.intValue());
		assertEquals(0.400,resultList.get(3).average);
		assertEquals(3,resultList.get(3).rank.intValue());
		assertEquals(0.400,resultList.get(4).average);
	}

}
