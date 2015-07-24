package cx.myhome.ckoshien.service;

import cx.myhome.ckoshien.dto.PitchingResultDto;
import cx.myhome.ckoshien.dto.TeamPitchingResultDto;
import cx.myhome.ckoshien.entity.Pitching;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import static cx.myhome.ckoshien.entity.PitchingNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;
import static org.seasar.extension.jdbc.parameter.Parameter.date;

/**
 * {@link Pitching}のサービスクラスです。
 *
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2014/12/09 16:58:09")
public class PitchingService extends AbstractService<Pitching> {

	public List<Pitching> list;
	public List<PitchingResultDto> pitchingResultDtos;
	public List<TeamPitchingResultDto> teamPitchingResultDtos;
	public PitchingResultDto pitchingResultDto;

    /**
     * 識別子でエンティティを検索します。
     *
     * @param id
     *            識別子
     * @return エンティティ
     */
    public Pitching findById(Integer id) {
        return select().id(id).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     *
     * @return エンティティのリスト
     */
    public List<Pitching> findAllOrderById() {
        return select().orderBy(asc(id())).getResultList();
    }

    public List<Pitching> findByGameId(Integer gameId,Integer myTeamId) {
    	list=select().innerJoin(player()).where("gameId=? AND myTeamId=?",gameId,myTeamId).orderBy(asc(id())).getResultList();
        return list;
    }
    public List<Pitching> findByGameIdAll(Integer gameId) {
    	list=select().where("gameId=?",gameId).orderBy(asc(id())).getResultList();
        return list;
    }

    public List<PitchingResultDto> findByPeriod(Date date,Date date2,String order){
		pitchingResultDtos=new ArrayList<PitchingResultDto>();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("beginDate", date(date));
		param.put("endDate", date(date2));
		param.put("order", order);
		pitchingResultDtos=jdbcManager.selectBySqlFile(PitchingResultDto.class, "cx.myhome.ckoshien.sql.PitchingResult.sql", param).getResultList();
		return pitchingResultDtos;
	}

    public List<TeamPitchingResultDto> findTPRByPeriod(Date date,Date date2,Integer teamId){
    	Map<String, Object> param = new HashMap<String, Object>();
		param.put("beginDate", date(date));
		param.put("endDate", date(date2));
		param.put("teamId", teamId);
		teamPitchingResultDtos=jdbcManager.selectBySqlFile(TeamPitchingResultDto.class, "cx.myhome.ckoshien.sql.TeamPitchingResult.sql",param).getResultList();
		return teamPitchingResultDtos;
	}

    public List<TeamPitchingResultDto> findPPRById(Integer playerId){
    	Map<String, Object> param = new HashMap<String, Object>();
		param.put("playerId", playerId);
		teamPitchingResultDtos=jdbcManager.selectBySqlFile(TeamPitchingResultDto.class, "cx.myhome.ckoshien.sql.PersonalPitchingResult.sql",param).getResultList();
		return teamPitchingResultDtos;
	}

    public PitchingResultDto findByIdAndDate(Integer playerId,Date beginDate,Date todayDate,Integer gameNumber){
    	Map<String, Object> param = new HashMap<String, Object>();
		param.put("playerId", playerId);
		param.put("beginDate", beginDate);
		param.put("todayDate", todayDate);
		param.put("gameNumber", gameNumber);
		pitchingResultDto=jdbcManager.selectBySqlFile(PitchingResultDto.class, "cx.myhome.ckoshien.sql.GamePitchingResult.sql",param).getSingleResult();
		return pitchingResultDto;
	}
}