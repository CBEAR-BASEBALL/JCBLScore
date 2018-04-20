package cx.myhome.ckoshien.logic;

import java.util.ArrayList;
import java.util.List;

import org.seasar.framework.beans.util.BeanUtil;

import cx.myhome.ckoshien.dto.BattingResultDto;
import cx.myhome.ckoshien.dto.PitchingResultDto;

public class ResultLogic {
	public List<BattingResultDto> averageTop10;
	public BattingResultDto battingResultDto;
	public List<BattingResultDto> homerunTop10;
	public List<BattingResultDto> rbiTop10;
	public List<BattingResultDto> hitTop10;
	public PitchingResultDto pitchingResultDto;
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

	public List<BattingResultDto> returnAverageTop10(List<BattingResultDto> battingResultList,int regAtBats){
		int j=0;
		int k=0;
		averageTop10=new ArrayList<BattingResultDto>();
		List<BattingResultDto> list=new ArrayList<BattingResultDto>();
		//規定打席数でリスト詰め替え
		for(int i=0;i<battingResultList.size();i++){
			if(battingResultList.get(i).tpa>=regAtBats){
				list.add(battingResultList.get(i));
			}
		}
		battingResultList=list;
		for(int i=0;i<battingResultList.size();i++){
			battingResultDto=new BattingResultDto();
			BattingResultDto battingResult = battingResultList.get(i);
			BeanUtil.copyProperties(battingResult, battingResultDto);
			if(battingResultList.get(i).tpa>=regAtBats){
				j++;
				if (i==0){
					battingResultDto.rank=1;
				}
				if (i>=1&&!(battingResultList.get(i-1).average.equals(battingResultList.get(i).average))){
					battingResultDto.rank=j;
					k=j;
				}
				//convert2BattingResultDto(battingResultList,i);

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
		//homerunTop10=new ArrayList<BattingResultDto>(battingResultList);
		for(int i=0;i<battingResultList.size();i++){
			for(int j=0;j<battingResultList.size();j++){
				if(battingResultList.get(i).homerun>battingResultList.get(j).homerun){
					BattingResultDto brd=battingResultList.get(i);
					battingResultList.set(i, battingResultList.get(j));
					battingResultList.set(j,brd);
				}
			}
		}
		int j=0;
		int k=0;
		homerunTop10=new ArrayList<BattingResultDto>();
		for(int i=0;i<battingResultList.size();i++){
			battingResultDto=new BattingResultDto();
			BattingResultDto battingResult = battingResultList.get(i);
			BeanUtil.copyProperties(battingResult, battingResultDto);
			if(battingResultList.get(i).homerun>0){
				j++;
				if (i==0){
					battingResultDto.rank=1;
				}
				if (i>=1 && (!battingResultList.get(i-1).homerun.equals(battingResultList.get(i).homerun))){
					battingResultDto.rank=j;
					k=j;
				}
				//convert2BattingResultDto(battingResultList,i);
				if (battingResultDto.rank==null||battingResultDto.rank<=10){
					if (k>=11){
						break;
					}
					homerunTop10.add(battingResultDto);
				}
			}

		}
		return homerunTop10;
	}

	public List<BattingResultDto> returnRbiTop10(List<BattingResultDto> battingResultList){
		//rbiTop10=new ArrayList<BattingResultDto>(battingResultList);
		for(int i=0;i<battingResultList.size();i++){
			for(int j=0;j<battingResultList.size();j++){
				if(battingResultList.get(i).rbi>battingResultList.get(j).rbi){
					BattingResultDto brd=battingResultList.get(i);
					battingResultList.set(i, battingResultList.get(j));
					battingResultList.set(j,brd);
				}
			}
		}
		int j=0;
		int k=0;
		rbiTop10=new ArrayList<BattingResultDto>();
		for(int i=0;i<battingResultList.size();i++){
			battingResultDto=new BattingResultDto();
			BattingResultDto battingResult = battingResultList.get(i);
			BeanUtil.copyProperties(battingResult, battingResultDto);
			if(battingResultList.get(i).rbi>0){
				j++;
				if (i==0){
					battingResultDto.rank=1;
				}
				if (i>=1 && (!battingResultList.get(i-1).rbi.equals(battingResultList.get(i).rbi))){
					battingResultDto.rank=j;
					k=j;
				}
				//convert2BattingResultDto(battingResultList,i);
				if (battingResultDto.rank==null||battingResultDto.rank<=10){
					if (k>=11){
						break;
					}
					rbiTop10.add(battingResultDto);
				}

			}
					}
		return rbiTop10;
	}

	public List<BattingResultDto> returnHitTop10(List<BattingResultDto> battingResultList){
		//hitTop10=new ArrayList<BattingResultDto>(battingResultList);
		for(int i=0;i<battingResultList.size();i++){
			for(int j=0;j<battingResultList.size();j++){
				if(battingResultList.get(i).hit>battingResultList.get(j).hit){
					BattingResultDto brd=battingResultList.get(i);
					battingResultList.set(i, battingResultList.get(j));
					battingResultList.set(j,brd);
				}
			}
		}
		int j=0;
		int k=0;
		hitTop10=new ArrayList<BattingResultDto>();
		for(int i=0;i<battingResultList.size();i++){
			battingResultDto=new BattingResultDto();
			BattingResultDto battingResult = battingResultList.get(i);
			BeanUtil.copyProperties(battingResult, battingResultDto);
			if(battingResultList.get(i).hit>0){
				j++;
				if (i==0){
					battingResultDto.rank=1;
				}
				if (i>=1 && (!battingResultList.get(i-1).hit.equals(battingResultList.get(i).hit))){
					battingResultDto.rank=j;
					k=j;
				}
				//convert2BattingResultDto(battingResultList,i);
				if (battingResultDto.rank==null||battingResultDto.rank<=10){
					if (k>=11){
						break;
					}
					hitTop10.add(battingResultDto);
				}
			}
		}
		return hitTop10;
	}

	public List<BattingResultDto> returnObpTop10(List<BattingResultDto> battingResultList,int regAtBats){
		//obpTop10=new ArrayList<BattingResultDto>(battingResultList);
		for(int i=0;i<battingResultList.size();i++){
			for(int j=0;j<battingResultList.size();j++){
				if(battingResultList.get(i).obp>battingResultList.get(j).obp){
					BattingResultDto brd=battingResultList.get(i);
					battingResultList.set(i, battingResultList.get(j));
					battingResultList.set(j,brd);
				}
			}
		}
		int j=0;
		int k=0;
		obpTop10=new ArrayList<BattingResultDto>();
		List<BattingResultDto> list=new ArrayList<BattingResultDto>();
		//規定打席数でリスト詰め替え
		for(int i=0;i<battingResultList.size();i++){
			if(battingResultList.get(i).tpa>=regAtBats){
				list.add(battingResultList.get(i));
			}
		}
		battingResultList=list;
		for(int i=0;i<battingResultList.size();i++){
			battingResultDto=new BattingResultDto();
			BattingResultDto battingResult = battingResultList.get(i);
			BeanUtil.copyProperties(battingResult, battingResultDto);
			if(battingResultList.get(i).tpa>=regAtBats){
				j++;
				if (i==0){
					battingResultDto.rank=1;
				}
				if (i>=1 && (battingResultList.get(i-1).obp!=battingResultList.get(i).obp)){
					battingResultDto.rank=j;
					k=j;
				}
				if (i>=1 && (battingResultList.get(i-1).obp==battingResultList.get(i).obp)){
					battingResultDto.rank=k;
				}
				//convert2BattingResultDto(battingResultList,i);
				if (battingResultDto.rank==null||battingResultDto.rank<=10){
					if (k>=11){
						break;
					}
					obpTop10.add(battingResultDto);
				}
			}
		}
		return obpTop10;
	}

	public List<BattingResultDto> returnTwobaseTop10(List<BattingResultDto> battingResultList){
		//twobaseTop10=new ArrayList<BattingResultDto>(battingResultList);
		for(int i=0;i<battingResultList.size();i++){
			for(int j=0;j<battingResultList.size();j++){
				if(battingResultList.get(i).twobase>battingResultList.get(j).twobase){
					BattingResultDto brd=battingResultList.get(i);
					battingResultList.set(i, battingResultList.get(j));
					battingResultList.set(j,brd);
				}
			}
		}
		int j=0;
		int k=0;
		twobaseTop10=new ArrayList<BattingResultDto>();
		for(int i=0;i<battingResultList.size();i++){
			battingResultDto=new BattingResultDto();
			BattingResultDto battingResult = battingResultList.get(i);
			BeanUtil.copyProperties(battingResult, battingResultDto);
			if(battingResultList.get(i).twobase>0){
				j++;
				if (i==0){
					battingResultDto.rank=1;
					k=1;
				}
				if (i>=1 && (!battingResultList.get(i-1).twobase.equals(battingResultList.get(i).twobase))){
					battingResultDto.rank=j;
					k=j;
				}
				if (i>=1 && (battingResultList.get(i-1).twobase.equals(battingResultList.get(i).twobase))){
					battingResultDto.rank=k;
				}
				//convert2BattingResultDto(battingResultList,i);
				if (battingResultDto.rank==null||battingResultDto.rank<=10){
					if (k>=11){
						break;
					}
					twobaseTop10.add(battingResultDto);
				}
			}
		}
		return twobaseTop10;
	}
	public List<BattingResultDto> returnSlgTop10(List<BattingResultDto> battingResultList,int regAtBats){
		slgTop10=new ArrayList<BattingResultDto>(battingResultList);
		for(int i=0;i<battingResultList.size();i++){
			for(int j=0;j<battingResultList.size();j++){
				if(battingResultList.get(i).slg>battingResultList.get(j).slg){
					BattingResultDto brd=battingResultList.get(i);
					battingResultList.set(i, battingResultList.get(j));
					battingResultList.set(j,brd);
				}
			}
		}
		int j=0;
		int k=0;
		slgTop10=new ArrayList<BattingResultDto>();
		List<BattingResultDto> list=new ArrayList<BattingResultDto>();
		//規定打席数でリスト詰め替え
		for(int i=0;i<battingResultList.size();i++){
			if(battingResultList.get(i).tpa>=regAtBats){
				list.add(battingResultList.get(i));
			}
		}
		battingResultList=list;
		for(int i=0;i<battingResultList.size();i++){
			battingResultDto=new BattingResultDto();
			BattingResultDto battingResult = battingResultList.get(i);
			BeanUtil.copyProperties(battingResult, battingResultDto);
			if(battingResultList.get(i).tpa>=regAtBats){
				j++;
				if (i==0){
					battingResultDto.rank=1;
				}
				if (i>=1 && (battingResultList.get(i-1).slg!=battingResultList.get(i).slg)){
					battingResultDto.rank=j;
					k=j;
				}
				if (i>=1 && (battingResultList.get(i-1).slg==battingResultList.get(i).slg)){
					battingResultDto.rank=k;
				}
				//convert2BattingResultDto(battingResultList,i);
				if (battingResultDto.rank==null||battingResultDto.rank<=10){
					if (k>=11){
						break;
					}
					slgTop10.add(battingResultDto);
				}
			}
		}
		return slgTop10;
	}

	public List<BattingResultDto> returnFourBallTop10(List<BattingResultDto> battingResultList){
		//fourBallTop10=new ArrayList<BattingResultDto>(battingResultList);
		for(int i=0;i<battingResultList.size();i++){
			for(int j=0;j<battingResultList.size();j++){
				if(battingResultList.get(i).fourBall>battingResultList.get(j).fourBall){
					BattingResultDto brd=battingResultList.get(i);
					battingResultList.set(i, battingResultList.get(j));
					battingResultList.set(j,brd);
				}
			}
		}
		int j=0;
		int k=0;
		fourBallTop10=new ArrayList<BattingResultDto>();
		for(int i=0;i<battingResultList.size();i++){
			battingResultDto=new BattingResultDto();
			BattingResultDto battingResult = battingResultList.get(i);
			BeanUtil.copyProperties(battingResult, battingResultDto);
			if(battingResultList.get(i).fourBall>0){
				j++;
				if (i==0){
					battingResultDto.rank=1;
					k=1;
				}
				if (i>=1 && (!battingResultList.get(i-1).fourBall.equals(battingResultList.get(i).fourBall))){
					battingResultDto.rank=j;
					k=j;
				}
				if (i>=1 && (battingResultList.get(i-1).fourBall.equals(battingResultList.get(i).fourBall))){
					battingResultDto.rank=k;
				}
				//convert2BattingResultDto(battingResultList,i);
				if (battingResultDto.rank==null||battingResultDto.rank<=10){
					if (k>=11){
						break;
					}
					fourBallTop10.add(battingResultDto);
				}
			}
		}
		return fourBallTop10;
	}

	public List<BattingResultDto> returnOpsTop10(List<BattingResultDto> battingResultList,int regAtBats){
		//opsTop10=new ArrayList<BattingResultDto>(battingResultList);
		for(int i=0;i<battingResultList.size();i++){
			for(int j=0;j<battingResultList.size();j++){
				if(battingResultList.get(i).ops>battingResultList.get(j).ops){
					BattingResultDto brd=battingResultList.get(i);
					battingResultList.set(i, battingResultList.get(j));
					battingResultList.set(j,brd);
				}
			}
		}
		int j=0;
		int k=0;
		opsTop10=new ArrayList<BattingResultDto>();
		List<BattingResultDto> list=new ArrayList<BattingResultDto>();
		//規定打席数でリスト詰め替え
		for(int i=0;i<battingResultList.size();i++){
			if(battingResultList.get(i).tpa>=regAtBats){
				list.add(battingResultList.get(i));
			}
		}
		battingResultList=list;
		for(int i=0;i<battingResultList.size();i++){
			battingResultDto=new BattingResultDto();
			BattingResultDto battingResult = battingResultList.get(i);
			BeanUtil.copyProperties(battingResult, battingResultDto);
			if(battingResultList.get(i).tpa>=regAtBats){
				j++;
				if (i==0){
					battingResultDto.rank=1;
				}
				if (i>=1 && (battingResultList.get(i-1).ops!=battingResultList.get(i).ops)){
					battingResultDto.rank=j;
					k=j;
				}
				if (i>=1 && (battingResultList.get(i-1).ops==battingResultList.get(i).ops)){
					battingResultDto.rank=k;
				}
				//convert2BattingResultDto(battingResultList,i);
				if (battingResultDto.rank==null||battingResultDto.rank<=10){
					if (k>=11){
						break;
					}
					opsTop10.add(battingResultDto);
				}
			}
		}
		return opsTop10;
	}

	public List<BattingResultDto> returnNsoTop10(List<BattingResultDto> battingResultList,int regAtBats){
		nsoTop10=new ArrayList<BattingResultDto>(battingResultList);
		for(int i=0;i<battingResultList.size();i++){
			for(int j=0;j<battingResultList.size();j++){
				if(battingResultList.get(i).notStrikeOut>battingResultList.get(j).notStrikeOut){
					BattingResultDto brd=battingResultList.get(i);
					battingResultList.set(i, battingResultList.get(j));
					battingResultList.set(j,brd);
				}
			}
		}
		int j=0;
		int k=0;
		nsoTop10=new ArrayList<BattingResultDto>();
		List<BattingResultDto> list=new ArrayList<BattingResultDto>();
		//規定打席数でリスト詰め替え
		for(int i=0;i<battingResultList.size();i++){
			if(battingResultList.get(i).tpa>=regAtBats){
				list.add(battingResultList.get(i));
			}
		}
		battingResultList=list;
		for(int i=0;i<battingResultList.size();i++){
			battingResultDto=new BattingResultDto();
			BattingResultDto battingResult = battingResultList.get(i);
			BeanUtil.copyProperties(battingResult, battingResultDto);
			if(battingResultList.get(i).tpa>=regAtBats &&battingResultList.get(i).notStrikeOut>0){
				j++;
				if (i==0){
					battingResultDto.rank=1;
				}
				if (i>=1 && (!battingResultList.get(i-1).notStrikeOut.equals(battingResultList.get(i).notStrikeOut))){
					battingResultDto.rank=j;
					k=j;
				}
				if (i>=1 && (battingResultList.get(i-1).notStrikeOut.equals(battingResultList.get(i).notStrikeOut))){
					battingResultDto.rank=k;
					//バグ修正。rank=0となる場合は1位タイなので1を代入。
					if(k==0){
						battingResultDto.rank=1;
					}
				}
				//convert2BattingResultDto(battingResultList,i);

				if (battingResultDto.rank==null||battingResultDto.rank<=10){
					if (k>=11){
						break;
					}
					nsoTop10.add(battingResultDto);
				}
			}
		}
		return nsoTop10;
	}
	public List<BattingResultDto> returnAvgHRTop10(List<BattingResultDto> battingResultList,int regAtBats){
		//avgHRTop10=new ArrayList<BattingResultDto>(battingResultList);
		for(int i=0;i<battingResultList.size();i++){
			for(int j=0;j<battingResultList.size();j++){
				if(battingResultList.get(i).avgHomerun<battingResultList.get(j).avgHomerun){
					BattingResultDto brd=battingResultList.get(i);
					battingResultList.set(i, battingResultList.get(j));
					battingResultList.set(j,brd);
				}
			}
		}
		int j=0;
		int k=0;
		avgHRTop10=new ArrayList<BattingResultDto>();
		List<BattingResultDto> list=new ArrayList<BattingResultDto>();
		//規定打席数でリスト詰め替え
		for(int i=0;i<battingResultList.size();i++){
			if(battingResultList.get(i).tpa>=regAtBats){
				list.add(battingResultList.get(i));
			}
		}
		battingResultList=list;
		for(int i=0;i<battingResultList.size();i++){
			battingResultDto=new BattingResultDto();
			BattingResultDto battingResult = battingResultList.get(i);
			BeanUtil.copyProperties(battingResult, battingResultDto);
			if(battingResultList.get(i).tpa>=regAtBats &&battingResultList.get(i).avgHomerun>0){
				j++;
				if (i==0){
					battingResultDto.rank=1;
				}
				if (i>=1 && (!battingResultList.get(i-1).avgHomerun.equals(battingResultList.get(i).avgHomerun))){
					battingResultDto.rank=j;
					k=j;
				}
				if (i>=1 && (battingResultList.get(i-1).avgHomerun.equals(battingResultList.get(i).avgHomerun))){
					battingResultDto.rank=k;
					//バグ修正。rank=0となる場合は1位タイなので1を代入。
					if(k==0){
						battingResultDto.rank=1;
					}
				}
				//convert2BattingResultDto(battingResultList,i);

				if (battingResultDto.rank==null||battingResultDto.rank<=10){
					if (k>=11){
						break;
					}
					avgHRTop10.add(battingResultDto);
				}
			}
		}
		return avgHRTop10;
	}
	public List<BattingResultDto> returnAvgRBITop10(List<BattingResultDto> battingResultList,int regAtBats){
		//avgRBITop10=new ArrayList<BattingResultDto>(battingResultList);
		for(int i=0;i<battingResultList.size();i++){
			for(int j=0;j<battingResultList.size();j++){
				if(battingResultList.get(i).avgRbi>battingResultList.get(j).avgRbi){
					BattingResultDto brd=battingResultList.get(i);
					battingResultList.set(i, battingResultList.get(j));
					battingResultList.set(j,brd);
				}
			}
		}
		int j=0;
		int k=0;
		avgRBITop10=new ArrayList<BattingResultDto>();
		List<BattingResultDto> list=new ArrayList<BattingResultDto>();
		//規定打席数でリスト詰め替え
		for(int i=0;i<battingResultList.size();i++){
			if(battingResultList.get(i).tpa>=regAtBats){
				list.add(battingResultList.get(i));
			}
		}
		battingResultList=list;
		for(int i=0;i<battingResultList.size();i++){
			battingResultDto=new BattingResultDto();
			BattingResultDto battingResult = battingResultList.get(i);
			BeanUtil.copyProperties(battingResult, battingResultDto);
			if(battingResultList.get(i).tpa>=regAtBats &&battingResultList.get(i).avgRbi>0){
				j++;
				if (i==0){
					battingResultDto.rank=1;
				}
				if (i>=1 && (!battingResultList.get(i-1).avgRbi.equals(battingResultList.get(i).avgRbi))){
					battingResultDto.rank=j;
					k=j;
				}
				if (i>=1 && (battingResultList.get(i-1).avgRbi.equals(battingResultList.get(i).avgRbi))){
					battingResultDto.rank=k;
					//バグ修正。rank=0となる場合は1位タイなので1を代入。
					if(k==0){
						battingResultDto.rank=1;
					}
				}

				if (battingResultDto.rank==null||battingResultDto.rank<=10){
					if (k>=11){
						break;
					}
					avgRBITop10.add(battingResultDto);
				}
			}
		}
		return avgRBITop10;
	}

	public List<PitchingResultDto> returnEraTop10(List<PitchingResultDto> pitchingResultList,int regAtPitch){
		int j=0;
		int k=0;
		eraTop10=new ArrayList<PitchingResultDto>();
		for(int i=0;i<pitchingResultList.size();i++){
			pitchingResultDto=new PitchingResultDto();
			PitchingResultDto pitchingResult = pitchingResultList.get(i);
			BeanUtil.copyProperties(pitchingResult, pitchingResultDto);
			if(pitchingResultList.get(i).inning>=regAtPitch){
				j++;
				if (i==0){
					pitchingResultDto.rank=1;
				}
				if (i>=1&&!(pitchingResultList.get(i-1).era.equals(pitchingResultList.get(i).era))){
					pitchingResultDto.rank=j;
					k=j;
				}
				//convert2PitchingResultDto(pitchingResultList,i);
				if (pitchingResultDto.rank==null||pitchingResultDto.rank<=10){
					if (k>=11){
						break;
					}
					eraTop10.add(pitchingResultDto);
				}
			}
		}
		return eraTop10;
	}

	public List<PitchingResultDto> returnWinTop10(List<PitchingResultDto> pitchingResultList){
		for(int i=0;i<pitchingResultList.size();i++){
			for(int j=0;j<pitchingResultList.size();j++){
				if(pitchingResultList.get(i).win>pitchingResultList.get(j).win){
					PitchingResultDto prd=pitchingResultList.get(i);
					pitchingResultList.set(i, pitchingResultList.get(j));
					pitchingResultList.set(j,prd);
				}
			}
		}
		int j=0;
		int k=0;
		winTop10=new ArrayList<PitchingResultDto>();
		for(int i=0;i<pitchingResultList.size();i++){
			pitchingResultDto=new PitchingResultDto();
			PitchingResultDto pitchingResult = pitchingResultList.get(i);
			BeanUtil.copyProperties(pitchingResult, pitchingResultDto);
			if (pitchingResultList.get(i).win>0){
				j++;
				if (i==0){
					pitchingResultDto.rank=1;
				}
				if (i>=1&&!(pitchingResultList.get(i-1).win.equals(pitchingResultList.get(i).win))){
					//System.out.println(pitchingResultList.get(i-1).win+","+pitchingResultList.get(i).win);
					pitchingResultDto.rank=j;
					k=j;
				}
				//convert2PitchingResultDto(pitchingResultList,i);
				if (pitchingResultDto.rank==null||pitchingResultDto.rank<=10){
					if (k>=11){
						break;
					}
					winTop10.add(pitchingResultDto);
				}
			}

		}
		return winTop10;
	}
	public List<PitchingResultDto> returnSaveTop10(List<PitchingResultDto> pitchingResultList){
		for(int i=0;i<pitchingResultList.size();i++){
			for(int j=0;j<pitchingResultList.size();j++){
				if(pitchingResultList.get(i).save>pitchingResultList.get(j).save){
					PitchingResultDto prd=pitchingResultList.get(i);
					pitchingResultList.set(i, pitchingResultList.get(j));
					pitchingResultList.set(j,prd);
				}
			}
		}
		int j=0;
		int k=0;
		saveTop10=new ArrayList<PitchingResultDto>();
		for(int i=0;i<pitchingResultList.size();i++){
			pitchingResultDto=new PitchingResultDto();
			PitchingResultDto pitchingResult = pitchingResultList.get(i);
			BeanUtil.copyProperties(pitchingResult, pitchingResultDto);
			if (pitchingResultList.get(i).save>0){
				j++;
				if (i==0){
					pitchingResultDto.rank=1;
				}
				if (i>=1&&!(pitchingResultList.get(i-1).save.equals(pitchingResultList.get(i).save))){
					//System.out.println(pitchingResultList.get(i-1).win+","+pitchingResultList.get(i).win);
					pitchingResultDto.rank=j;
					k=j;
				}
				//convert2PitchingResultDto(pitchingResultList,i);
				if (pitchingResultDto.rank==null||pitchingResultDto.rank<=10){
					if (k>=11){
						break;
					}
					saveTop10.add(pitchingResultDto);
				}
			}

		}
		return saveTop10;
	}

	public List<PitchingResultDto> returnStrikeOutTop10(List<PitchingResultDto> pitchingResultList){
		//strikeOutTop10=new ArrayList<PitchingResultDto>(pitchingResultList);
		for(int i=0;i<pitchingResultList.size();i++){
			for(int j=0;j<pitchingResultList.size();j++){
				if(pitchingResultList.get(i).strikeOut>pitchingResultList.get(j).strikeOut){
					PitchingResultDto prd=pitchingResultList.get(i);
					pitchingResultList.set(i, pitchingResultList.get(j));
					pitchingResultList.set(j,prd);
				}
			}
		}
		int j=0;
		int k=0;
		strikeOutTop10=new ArrayList<PitchingResultDto>();
		for(int i=0;i<pitchingResultList.size();i++){
			pitchingResultDto=new PitchingResultDto();
			PitchingResultDto pitchingResult = pitchingResultList.get(i);
			BeanUtil.copyProperties(pitchingResult, pitchingResultDto);
			if (pitchingResultList.get(i).strikeOut>0){
				j++;
				if (i==0){
					pitchingResultDto.rank=1;
				}
				if (i>=1&&!(pitchingResultList.get(i-1).strikeOut.equals(pitchingResultList.get(i).strikeOut))){
					//System.out.println(pitchingResultList.get(i-1).win+","+pitchingResultList.get(i).win);
					pitchingResultDto.rank=j;
					k=j;
				}
				//convert2PitchingResultDto(pitchingResultList,i);
				if (pitchingResultDto.rank==null||pitchingResultDto.rank<=10){
					if (k>=11){
						break;
					}
					strikeOutTop10.add(pitchingResultDto);
				}
			}

		}
		return strikeOutTop10;
	}

	public List<BattingResultDto> returnAverageTop20(List<BattingResultDto> battingResultList,int regAtBats){
		int j=0;
		int k=0;
		ArrayList<BattingResultDto> averageTop20 = new ArrayList<BattingResultDto>();
		List<BattingResultDto> list=new ArrayList<BattingResultDto>();
		//規定打席数でリスト詰め替え
		for(int i=0;i<battingResultList.size();i++){
			if(battingResultList.get(i).tpa>=regAtBats){
				list.add(battingResultList.get(i));
			}
		}
		battingResultList=list;
		for(int i=0;i<battingResultList.size();i++){
			battingResultDto=new BattingResultDto();
			BattingResultDto battingResult = battingResultList.get(i);
			BeanUtil.copyProperties(battingResult, battingResultDto);
			if(battingResultList.get(i).tpa>=regAtBats){
				j++;
				if (i==0){
					battingResultDto.rank=1;
				}
				if (i>=1&&!(battingResultList.get(i-1).average.equals(battingResultList.get(i).average))){
					battingResultDto.rank=j;
					k=j;
				}
				//convert2BattingResultDto(battingResultList,i);

				if (battingResultDto.rank==null||battingResultDto.rank<=20){
					if (k>=21){
						break;
					}
					averageTop20.add(battingResultDto);
				}
			}
		}
		return averageTop20;
	}
}
