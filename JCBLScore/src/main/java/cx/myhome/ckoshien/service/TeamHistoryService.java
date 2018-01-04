package cx.myhome.ckoshien.service;

import cx.myhome.ckoshien.dto.TeamHistoryDto;
import cx.myhome.ckoshien.entity.TeamHistory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import static cx.myhome.ckoshien.entity.TeamHistoryNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

/**
 * {@link TeamHistory}のサービスクラスです。
 *
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2016/12/03 21:13:29")
public class TeamHistoryService extends AbstractService<TeamHistory> {

    /**
     * 識別子でエンティティを検索します。
     *
     * @param id
     *            識別子
     * @return エンティティ
     */
    public TeamHistory findById(Integer id) {
        return select().id(id).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     *
     * @return エンティティのリスト
     */
    public List<TeamHistory> findByIDOrderByStartLeagueId(Integer id) {
        return select().orderBy(asc(startLeagueId())).where("playerId=?",id).getResultList();
    }

    public List<TeamHistoryDto> findTeamHistoryWithSeason(Integer playerId){
    	Map<String, Object> param = new HashMap<String, Object>();
		param.put("playerId",playerId);
    	return jdbcManager.selectBySqlFile(TeamHistoryDto.class,"cx.myhome.ckoshien.sql.FindTeamHistoryWithSeasonName.sql",param).getResultList();
    }
}