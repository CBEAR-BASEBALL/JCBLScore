package cx.myhome.ckoshien.service;

import cx.myhome.ckoshien.entity.TeamHistory;
import java.util.List;
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
    public List<TeamHistory> findAllOrderById() {
        return select().orderBy(asc(id())).getResultList();
    }
}