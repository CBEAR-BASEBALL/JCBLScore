package cx.myhome.ckoshien.action;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.seasar.framework.container.annotation.tiger.Aspect;
import org.seasar.struts.annotation.Execute;

import cx.myhome.ckoshien.dto.BattingResultDto;
import cx.myhome.ckoshien.dto.MinMaxLeagueDto;
import cx.myhome.ckoshien.dto.PitchingResultDto;
import cx.myhome.ckoshien.dto.TitleHolderDto;
import cx.myhome.ckoshien.dto.api.ResultApiDto;
import cx.myhome.ckoshien.entity.TitleHolder;
import cx.myhome.ckoshien.rest.RestClient;
import cx.myhome.ckoshien.service.GameService;
import cx.myhome.ckoshien.service.TitleHolderService;

public class TitleHolderAction {
	private static final int EVENT_TYPE_AVG=1;
	private static final int EVENT_TYPE_HR=2;
	private static final int EVENT_TYPE_RBI=3;
	private static final int EVENT_TYPE_HIT=4;
	private static final int EVENT_TYPE_ERA=5;
	private static final int EVENT_TYPE_WIN=6;
	private static final int EVENT_TYPE_SAVE=7;
	private static final int EVENT_TYPE_STRIKE=8;

	@Resource
	private HttpServletRequest request;
	@Resource
	protected GameService gameService;
	@Resource
	protected TitleHolderService titleHolderService;
	@Resource
	protected HttpServletResponse response;
	public List<TitleHolderDto> titleHolderList;

	static Logger logger = Logger.getLogger("rootLogger");
	@Execute(validator = false)
	public String index() {
		long t0=System.currentTimeMillis();
		//アクセス制限
		try {
    		InetAddress ia=InetAddress.getByName(request.getRemoteAddr());
//    		System.out.println(ia.getHostName());
    		if(!ia.getHostName().substring(ia.getHostName().length()-3).equals(".jp")
    				&& !request.getRemoteAddr().equals("0:0:0:0:0:0:0:1")
    				&& !request.getRemoteAddr().startsWith("192.168")
    				&& !ia.getHostName().substring(ia.getHostName().length()-4).equals(".net")
    				&& !ia.getHostName().equals("127.0.0.1")
    				&& !ia.getHostName().equals(request.getRemoteAddr())
    			){
    			logger.info("遮断:"+ia.getHostName()+":"+request.getRemotePort());
//    			//response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    			try {
					response.sendError(404, "jp、netドメインのみ許可しています。"+ia.getHostName());
				} catch (IOException e) {
					logger.error(e);
				}
        		return null;
    		}
			logger.info(ia.getHostName()+":"+request.getRemotePort());
		} catch (Exception e1) {
			logger.warn(e1);
		}
		long t1=System.currentTimeMillis();
		logger.info("lookup:"+(t1-t0));
		List<TitleHolderDto> tmpList = titleHolderService.findAllOrderByLeagueId();
//		titleHolderList = titleHolderService.findAllOrderByLeagueId();
		titleHolderList=new ArrayList<TitleHolderDto>();
		TitleHolderDto tHolderDto = new TitleHolderDto();
		for(int i=0;i<tmpList.size();i++){
			if(tmpList.get(i).eventType==EVENT_TYPE_AVG){
				tHolderDto.setAverage(tmpList.get(i).value);
				if(tHolderDto.getAvgPlayer()!=null){
					tHolderDto.setAvgPlayer(tHolderDto.getAvgPlayer()+"<br>/"+tmpList.get(i).getName());
				}else{
					tHolderDto.setAvgPlayer(tmpList.get(i).getName());
				}
			}else if(tmpList.get(i).eventType==EVENT_TYPE_HR){
				tHolderDto.setHomerun(tmpList.get(i).value.intValue());
				if(tHolderDto.getHrPlayer()!=null){
					tHolderDto.setHrPlayer(tHolderDto.getHrPlayer()+"<br>/"+tmpList.get(i).getName());
				}else{
					tHolderDto.setHrPlayer(tmpList.get(i).getName());
				}
			}else if(tmpList.get(i).eventType==EVENT_TYPE_RBI){
				tHolderDto.setRbi(tmpList.get(i).value.intValue());
				if(tHolderDto.getRbiPlayer()!=null){
					tHolderDto.setRbiPlayer(tHolderDto.getRbiPlayer()+"<br>/"+tmpList.get(i).getName());
				}else{
					tHolderDto.setRbiPlayer(tmpList.get(i).getName());
				}
			}else if(tmpList.get(i).eventType==EVENT_TYPE_HIT){
				tHolderDto.setHit(tmpList.get(i).value.intValue());
				if(tHolderDto.getHitPlayer()!=null){
					tHolderDto.setHitPlayer(tHolderDto.getHitPlayer()+"<br>/"+tmpList.get(i).getName());
				}else{
					tHolderDto.setHitPlayer(tmpList.get(i).getName());
				}
			}else if(tmpList.get(i).eventType==EVENT_TYPE_ERA){
				tHolderDto.setEra(tmpList.get(i).value);
				if(tHolderDto.getEraPlayer()!=null){
					tHolderDto.setEraPlayer(tHolderDto.getEraPlayer()+"<br>/"+tmpList.get(i).getName());
				}else{
					tHolderDto.setEraPlayer(tmpList.get(i).getName());
				}
			}else if(tmpList.get(i).eventType==EVENT_TYPE_WIN){
				tHolderDto.setWin(tmpList.get(i).value.intValue());
				if(tHolderDto.getWinPlayer()!=null){
					tHolderDto.setWinPlayer(tHolderDto.getWinPlayer()+"<br>/"+tmpList.get(i).getName());
				}else{
					tHolderDto.setWinPlayer(tmpList.get(i).getName());
				}
			}else if(tmpList.get(i).eventType==EVENT_TYPE_SAVE){
				tHolderDto.setSave(tmpList.get(i).value.intValue());
				if(tHolderDto.getSavePlayer()!=null){
					tHolderDto.setSavePlayer(tHolderDto.getSavePlayer()+"<br>/"+tmpList.get(i).getName());
				}else{
					tHolderDto.setSavePlayer(tmpList.get(i).getName());
				}
			}else if(tmpList.get(i).eventType==EVENT_TYPE_STRIKE){
				tHolderDto.setStrikeout(tmpList.get(i).value.intValue());
				if(tHolderDto.getStrikePlayer()!=null){
					tHolderDto.setStrikePlayer(tHolderDto.getStrikePlayer()+"<br>/"+tmpList.get(i).getName());
				}else{
					tHolderDto.setStrikePlayer(tmpList.get(i).getName());
				}
			}
			if(i==tmpList.size()-1 || tmpList.get(i).leagueId!=tmpList.get(i+1).leagueId){
				System.out.println(i);
				titleHolderList.add(tHolderDto);
				tHolderDto = new TitleHolderDto();
			}
			tHolderDto.setShortTitle(tmpList.get(i).getShortTitle());
			tHolderDto.setTotalFlg(tmpList.get(i).getTotalFlg());
		}
        return "index.jsp";
	}

	@Aspect(value="loginConfInterceptor")
	@Execute(validator = false)
	public String exec() {
		List<MinMaxLeagueDto> minMax = gameService.findMinMaxLeagueId();
		Integer minLeagueId = minMax.get(0).getMin();
		Integer maxLeagueId = minMax.get(0).getMax();
		RestClient client = new RestClient();
		List<TitleHolder> titleHolderList=new ArrayList<TitleHolder>();
		for(int i=minLeagueId;i<=maxLeagueId;i++){
			String uri="http://127.0.0.1:8080/JCBLScore/api/v1/result/season/"+i;
			Object entity=null;
			HashMap<String,String> header=new HashMap<String,String>();
			ResultApiDto json=client.sendRequest(uri, "GET", entity, ResultApiDto.class,header);

			titleHolderList=bToTHList(i, json.averageTop10, titleHolderList,EVENT_TYPE_AVG);
			titleHolderList=bToTHList(i, json.homerunTop10, titleHolderList,EVENT_TYPE_HR);
			titleHolderList=bToTHList(i, json.rbiTop10, titleHolderList,EVENT_TYPE_RBI);
			titleHolderList=bToTHList(i, json.hitTop10, titleHolderList,EVENT_TYPE_HIT);
			titleHolderList=pToTHList(i, json.eraTop10, titleHolderList,EVENT_TYPE_ERA);
			titleHolderList=pToTHList(i, json.winTop10, titleHolderList,EVENT_TYPE_WIN);
			titleHolderList=pToTHList(i, json.saveTop10, titleHolderList,EVENT_TYPE_SAVE);
			titleHolderList=pToTHList(i, json.strikeOutTop10, titleHolderList,EVENT_TYPE_STRIKE);
		}
		List<TitleHolder> oldList = titleHolderService.findAllOrderById();
		for(int j=0;j<oldList.size();j++){
			//全削除
			titleHolderService.delete(oldList.get(j));
		}
		for(int i=0;i<titleHolderList.size();i++){
			titleHolderService.insert(titleHolderList.get(i));
		}
		System.out.println(titleHolderList);
		//logger.info(json);
		return null;
	}

	public List<TitleHolder> bToTHList(Integer i,List<BattingResultDto> list,List<TitleHolder> titleHolderList,Integer eventType){
		for(int j=0;j<list.size();j++){
			if(list.get(j).rank==null || list.get(j).rank==1){
				TitleHolder titleHolder=new TitleHolder();
				titleHolder.eventType=eventType;
				if(eventType==EVENT_TYPE_AVG){
					titleHolder.value=list.get(j).average;
				}else if(eventType==EVENT_TYPE_HR){
					titleHolder.value=list.get(j).homerun.doubleValue();
				}else if(eventType==EVENT_TYPE_RBI){
					titleHolder.value=list.get(j).rbi.doubleValue();
				}else if(eventType==EVENT_TYPE_HIT){
					titleHolder.value=list.get(j).hit.doubleValue();
				}
				titleHolder.leagueId=i;
				titleHolder.playerId=list.get(j).playerId;

				titleHolder.lockFlg=0;
				titleHolderList.add(titleHolder);
			}
			if(list.get(j).rank!=null && list.get(j).rank>1){
				break;
			}
		}
		return titleHolderList;
	}
	public List<TitleHolder> pToTHList(Integer i,List<PitchingResultDto> list,List<TitleHolder> titleHolderList,Integer eventType){
		for(int j=0;j<list.size();j++){
			if(list.get(j).rank==null || list.get(j).rank==1){
				TitleHolder titleHolder=new TitleHolder();
				titleHolder.eventType=eventType;
				if(eventType==EVENT_TYPE_ERA){
					titleHolder.value=list.get(j).era;
				}else if(eventType==EVENT_TYPE_WIN){
					titleHolder.value=list.get(j).win.doubleValue();
				}else if(eventType==EVENT_TYPE_STRIKE){
					titleHolder.value=list.get(j).strikeOut.doubleValue();
				}else if(eventType==EVENT_TYPE_SAVE){
					titleHolder.value=list.get(j).save.doubleValue();
				}
				titleHolder.leagueId=i;
				titleHolder.playerId=list.get(j).playerId;

				titleHolder.lockFlg=0;
				titleHolderList.add(titleHolder);
			}
			if(list.get(j).rank!=null && list.get(j).rank>1){
				break;
			}
		}
		return titleHolderList;
	}

}
