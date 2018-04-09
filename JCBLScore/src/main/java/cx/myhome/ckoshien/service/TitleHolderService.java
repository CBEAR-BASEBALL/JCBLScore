package cx.myhome.ckoshien.service;

import cx.myhome.ckoshien.dto.MinMaxLeagueDto;
import cx.myhome.ckoshien.dto.TitleHolderDto;
import cx.myhome.ckoshien.entity.TitleHolder;
import java.util.List;
import javax.annotation.Generated;

import static cx.myhome.ckoshien.entity.TitleHolderNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

/**
 * {@link TitleHolder}のサービスクラスです。
 *
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2018/04/08 16:30:13")
public class TitleHolderService extends AbstractService<TitleHolder> {

    /**
     * 識別子でエンティティを検索します。
     *
     * @param playerId
     *            識別子
     * @param leagueId
     *            識別子
     * @param eventType
     *            識別子
     * @return エンティティ
     */
    public TitleHolder findById(Integer playerId, Integer leagueId, Integer eventType) {
        return select().id(playerId, leagueId, eventType).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     *
     * @return エンティティのリスト
     */
    public List<TitleHolder> findAllOrderById() {
        return select().orderBy(asc(playerId()), asc(leagueId()), asc(eventType())).getResultList();
    }

    public List<TitleHolderDto> findAllOrderByLeagueId() {
    	return jdbcManager
    	.selectBySqlFile(TitleHolderDto.class, "cx.myhome.ckoshien.sql.TitleHolderList.sql")
    	.getResultList();
    }
}