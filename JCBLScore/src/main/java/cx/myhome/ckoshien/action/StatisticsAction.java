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
	@Resource
	protected TeamService teamService;
	public List<Team> teamList;
	@Resource
	protected LeagueService leagueService;
	public List<String> seasonList;

	@Execute(validator = false)
	public String index(){
		logger.info("/statistics/");
		countDtos = battingSumService.countMemberBySeason();
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
					dbMap.put(elementMap, 0);
				}
			}
		}
		countDtos= new ArrayList<MemberCountDto>();
		for(int i=0;i<teamList.size();i++){
			for(int j=0;j<leagueList.size();j++){
				MemberCountDto mcd=new MemberCountDto();
				mcd.leagueId=leagueList.get(j);
				mcd.teamId=teamList.get(i).teamId;
				//mcd.teamName=teamList.get(i).teamName;
				//mcd.shortName=teamList.get(i).shortName;
				elementMap = new HashMap<Integer,Integer>();
				elementMap.put(teamList.get(i).teamId, leagueList.get(j));
				mcd.memberCount=dbMap.get(elementMap);
				countDtos.add(mcd);
			}
		}
		MemoryUtil.viewMemoryInfo();
		return "index.jsp";
	}

}
