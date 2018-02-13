package cx.myhome.ckoshien.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.seasar.struts.annotation.Execute;

import cx.myhome.ckoshien.dto.MemberCountDto;
import cx.myhome.ckoshien.entity.League;
import cx.myhome.ckoshien.entity.Team;
import cx.myhome.ckoshien.service.BattingSumService;
import cx.myhome.ckoshien.service.LeagueService;
import cx.myhome.ckoshien.service.TeamService;
import cx.myhome.ckoshien.util.MemoryUtil;

public class StatisticsAction {
	private static Logger logger = Logger.getLogger("rootLogger");
	@Resource
	protected BattingSumService battingSumService;
	public List<Integer> leagueList;
	public List<MemberCountDto> countDtos;
	public List<MemberCountDto> yearDtos;
	@Resource
	protected TeamService teamService;
	public List<Team> teamList;
	@Resource
	protected LeagueService leagueService;
	public List<String> seasonList;
	public List<String> yearList;

	@Execute(validator = false)
	public String index(){
		logger.info("/statistics/");
		countDtos = battingSumService.countMemberBySeason();
		yearDtos=battingSumService.countMemberByYear();
		teamList=teamService.findAllOrderById();
		leagueList=new ArrayList<Integer>();
		seasonList=new ArrayList<String>();
		for(int i=0;i<countDtos.size();i++){
			if(!leagueList.contains(countDtos.get(i).leagueId)){
				leagueList.add(countDtos.get(i).leagueId);
				seasonList.add(countDtos.get(i).shortName);
			}
		}
		HashMap<HashMap<Integer,Integer>,Integer> dbMap= new HashMap<HashMap<Integer,Integer>,Integer>();
		HashMap<Integer,Integer> elementMap;
		for(int i=0;i<countDtos.size();i++){
			elementMap = new HashMap<Integer,Integer>();
			elementMap.put(countDtos.get(i).teamId, countDtos.get(i).leagueId);
			dbMap.put(elementMap, countDtos.get(i).memberCount);
		}
		for(int i=0;i<teamList.size();i++){
			for(int j=0;j<leagueList.size();j++){
				elementMap = new HashMap<Integer,Integer>();
				elementMap.put(teamList.get(i).teamId, leagueList.get(j));
				if(!dbMap.containsKey(elementMap)){
					dbMap.put(elementMap, null);
				}
			}
		}
		countDtos= new ArrayList<MemberCountDto>();
		for(int i=0;i<teamList.size();i++){
			for(int j=0;j<leagueList.size();j++){
				MemberCountDto mcd=new MemberCountDto();
				mcd.leagueId=leagueList.get(j);
				mcd.teamId=teamList.get(i).teamId;
				elementMap = new HashMap<Integer,Integer>();
				elementMap.put(teamList.get(i).teamId, leagueList.get(j));
				mcd.memberCount=dbMap.get(elementMap);
				countDtos.add(mcd);
			}
		}
		//年ごとの処理ここから
		yearList=new ArrayList<String>();
		for(int i=0;i<yearDtos.size();i++){
			if(!yearList.contains(yearDtos.get(i).shortName)){
				//leagueList.add(yearDtos.get(i).leagueId);
				yearList.add(yearDtos.get(i).shortName);
			}
		}
		HashMap<HashMap<Integer, String>, Integer> dbMap2 = new HashMap<HashMap<Integer,String>,Integer>();
		HashMap<Integer, String> elementMap2 = new HashMap<Integer, String>();
		for(int i=0;i<yearDtos.size();i++){
			elementMap2 = new HashMap<Integer,String>();
			elementMap2.put(yearDtos.get(i).teamId, yearDtos.get(i).shortName);
			dbMap2.put(elementMap2, yearDtos.get(i).memberCount);
		}
		for(int i=0;i<teamList.size();i++){
			for(int j=0;j<yearList.size();j++){
				elementMap2 = new HashMap<Integer,String>();
				elementMap2.put(teamList.get(i).teamId, yearList.get(j));
				if(!dbMap2.containsKey(elementMap2)){
					//dbMap2.put(elementMap2, 0);
					dbMap2.put(elementMap2, null);
				}
			}
		}
		yearDtos= new ArrayList<MemberCountDto>();
		for(int i=0;i<teamList.size();i++){
			for(int j=0;j<yearList.size();j++){
				MemberCountDto mcd=new MemberCountDto();
				mcd.shortName=yearList.get(j);
				mcd.teamId=teamList.get(i).teamId;
				elementMap2 = new HashMap<Integer,String>();
				elementMap2.put(teamList.get(i).teamId, yearList.get(j));
				mcd.memberCount=dbMap2.get(elementMap2);
				yearDtos.add(mcd);
			}
		}
		MemoryUtil.viewMemoryInfo();
		return "index.jsp";
	}

}
