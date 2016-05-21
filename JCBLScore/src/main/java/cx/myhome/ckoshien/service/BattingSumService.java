package cx.myhome.ckoshien.service;

import cx.myhome.ckoshien.SqlFiles;
import cx.myhome.ckoshien.dto.BattingResultDto;
import cx.myhome.ckoshien.dto.TeamBattingResultDto;
import cx.myhome.ckoshien.entity.BattingSum;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.seasar.extension.jdbc.parameter.Parameter.*;

import javax.annotation.Generated;

import static cx.myhome.ckoshien.entity.BattingSumNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

/**
 * {@link BattingSum}のサービスクラスです。
 *
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2014/12/09 16:58:09")
public class BattingSumService extends AbstractService<BattingSum> {

	public List<BattingSum> list;
	public List<BattingResultDto> battingResultDtos;
	public List<TeamBattingResultDto> teamBattingResultDtos;
    /**
     * 識別子でエンティティを検索します。
     *
     * @param id
     *            識別子
     * @return エンティティ
     */
    public BattingSum findById(Integer id) {
        return select().id(id).getSingleResult();
    }
    public BattingSum findByPlayerIdAndGameId(Integer playerId,Integer gameId) {
        return select().where("playerId=? and gameId=?",playerId,gameId).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     *
     * @return エンティティのリスト
     */
    public List<BattingSum> findAllOrderById() {
        return select().orderBy(asc(id())).getResultList();
    }

	public List<BattingSum> findByGameId(Integer gameId,Integer myTeamId) {
		list=select().innerJoin(player()).where("gameId=? AND myTeamId=?",gameId,myTeamId).orderBy(asc(id())).getResultList();
		return list;
	}
	public List<BattingSum> findByGameIdAll(Integer gameId) {
		list=select().where("gameId=?",gameId).orderBy(asc(id())).getResultList();
		return list;
	}
	public List<BattingResultDto> findByPeriod(Date date,Date date2,String order){
		battingResultDtos=new ArrayList<BattingResultDto>();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("beginDate", date(date));
		param.put("endDate", date(date2));
		param.put("order", order);
		battingResultDtos=jdbcManager.selectBySqlFile(BattingResultDto.class, "cx.myhome.ckoshien.sql.BattingResult.sql",param).getResultList();
		return battingResultDtos;
	}

	public List<BattingResultDto> findHomerun(Date beginDate,Date todayDate,Integer gameId,Integer gameNumber){
		battingResultDtos=new ArrayList<BattingResultDto>();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("beginDate", beginDate);
		param.put("todayDate", todayDate);
		param.put("gameId", gameId);
		param.put("gameNumber", gameNumber);
		battingResultDtos=jdbcManager.selectBySqlFile(BattingResultDto.class, "cx.myhome.ckoshien.sql.Homerun.sql",param).getResultList();
		return battingResultDtos;
	}

	public List<TeamBattingResultDto> findTBRByPeriod(Date date,Date date2,Integer teamId){
		//battingResultDtos=new ArrayList<BattingResultDto>();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("beginDate", date(date));
		param.put("endDate", date(date2));
		param.put("teamId", teamId);
		teamBattingResultDtos=jdbcManager.selectBySqlFile(TeamBattingResultDto.class, "cx.myhome.ckoshien.sql.TeamBattingResult.sql",param).getResultList();
		return teamBattingResultDtos;
	}

	public List<TeamBattingResultDto> findPBRById(Integer playerId){
		teamBattingResultDtos=new ArrayList<TeamBattingResultDto>();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("playerId", playerId);
		teamBattingResultDtos=jdbcManager.selectBySqlFile(TeamBattingResultDto.class, "cx.myhome.ckoshien.sql.PersonalBattingResult.sql",param).getResultList();
		return teamBattingResultDtos;
	}

	public List<TeamBattingResultDto> findPBRDById(Integer playerId){
		teamBattingResultDtos=new ArrayList<TeamBattingResultDto>();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("playerId", playerId);
		teamBattingResultDtos=jdbcManager.selectBySqlFile(TeamBattingResultDto.class, "cx.myhome.ckoshien.sql.PersonalBattingResultDetail.sql",param).getResultList();
		return teamBattingResultDtos;
	}

	public List<TeamBattingResultDto> findPBRGOById(Integer playerId){
		teamBattingResultDtos=new ArrayList<TeamBattingResultDto>();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("playerId", playerId);
		teamBattingResultDtos=jdbcManager.selectBySqlFile(TeamBattingResultDto.class, "cx.myhome.ckoshien.sql.PersonalBattingResultGroupByOpponent.sql",param).getResultList();
		return teamBattingResultDtos;
	}
}