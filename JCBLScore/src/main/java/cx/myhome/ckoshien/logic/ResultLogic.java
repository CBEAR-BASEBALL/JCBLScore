package cx.myhome.ckoshien.logic;

import java.util.ArrayList;
import java.util.List;

import cx.myhome.ckoshien.dto.BattingResultDto;

public class ResultLogic {
	public List<BattingResultDto> averageTop10;
	public BattingResultDto battingResultDto;
	public List<BattingResultDto> homerunTop10;
	public List<BattingResultDto> rbiTop10;

	public List<BattingResultDto> returnAverageTop10(List<BattingResultDto> battingResultList){
		int j=0;
		int k=0;
		averageTop10=new ArrayList<BattingResultDto>();
		for(int i=0;i<battingResultList.size();i++){
			battingResultDto=new BattingResultDto();
			if(battingResultList.get(i).tpa>=42){
				j++;
				if (i>=1&&!(battingResultList.get(i-1).average==battingResultList.get(i).average)){
					battingResultDto.rank=j;
					k=j;
				}
				convert2BattingResultDto(battingResultList,i);
				if (battingResultDto.rank==null||battingResultDto.rank<=10){
					if (k>=11){
						break;
					}
					averageTop10.add(battingResultDto);
				}
			}
		}
		return averageTop10;
	}

	public List<BattingResultDto> returnHomerunTop10(List<BattingResultDto> battingResultList){
		int j=0;
		int k=0;
		homerunTop10=new ArrayList<BattingResultDto>();
		for(int i=0;i<battingResultList.size();i++){
			battingResultDto=new BattingResultDto();
			j++;
			if (i==0){
				battingResultDto.rank=1;
			}
			if (i>=1 && (!battingResultList.get(i-1).homerun.equals(battingResultList.get(i).homerun))){
				battingResultDto.rank=j;
				k=j;
			}
			convert2BattingResultDto(battingResultList,i);
			if (battingResultDto.rank==null||battingResultDto.rank<=10){
				if (k>=11){
					break;
				}
				homerunTop10.add(battingResultDto);

			}
		}
		return homerunTop10;
	}

	public List<BattingResultDto> returnRbiTop10(List<BattingResultDto> battingResultList){
		int j=0;
		int k=0;
		rbiTop10=new ArrayList<BattingResultDto>();
		for(int i=0;i<battingResultList.size();i++){
			battingResultDto=new BattingResultDto();
			j++;
			if (i==0){
				battingResultDto.rank=1;
			}
			if (i>=1 && (!battingResultList.get(i-1).rbi.equals(battingResultList.get(i).rbi))){
				battingResultDto.rank=j;
				k=j;
			}
			convert2BattingResultDto(battingResultList,i);
			if (battingResultDto.rank==null||battingResultDto.rank<=10){
				if (k>=11){
					break;
				}
				rbiTop10.add(battingResultDto);

			}
		}
		return rbiTop10;
	}

	public void convert2BattingResultDto(List<BattingResultDto> battingResultList,int i){
		battingResultDto.atBats=battingResultList.get(i).atBats;
		battingResultDto.average=battingResultList.get(i).average;
		battingResultDto.fourBall=battingResultList.get(i).fourBall;
		battingResultDto.hit=battingResultList.get(i).hit;
		battingResultDto.homerun=battingResultList.get(i).homerun;
		battingResultDto.name=battingResultList.get(i).name;
		battingResultDto.rbi=battingResultList.get(i).rbi;
		battingResultDto.strikeOut=battingResultList.get(i).strikeOut;
		battingResultDto.tpa=battingResultList.get(i).tpa;
		battingResultDto.twobase=battingResultList.get(i).twobase;
	}
}
