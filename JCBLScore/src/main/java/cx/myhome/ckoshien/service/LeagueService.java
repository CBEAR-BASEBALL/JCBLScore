package cx.myhome.ckoshien.service;

import cx.myhome.ckoshien.entity.League;
import java.util.List;
import javax.annotation.Generated;

import static cx.myhome.ckoshien.entity.LeagueNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

/**
 * {@link League}のサービスクラスです。
 *
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2015/06/18 16:43:26")
public class LeagueService extends AbstractService<League> {

    /**
     * 識別子でエンティティを検索します。
     *
     * @param id
     *            識別子
     * @return エンティティ
     */
    public League findById(Integer id) {
        return select().id(id).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     *
     * @return エンティティのリスト
     */
    public List<League> findAllOrderById() {
        return select().orderBy(desc(id())).getResultList();
    }
}