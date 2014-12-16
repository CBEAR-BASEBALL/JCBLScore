package cx.myhome.ckoshien.service;

import cx.myhome.ckoshien.entity.Team;
import java.util.List;
import javax.annotation.Generated;

import static cx.myhome.ckoshien.entity.TeamNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

/**
 * {@link Team}のサービスクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2014/12/09 16:58:09")
public class TeamService extends AbstractService<Team> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param teamId
     *            識別子
     * @return エンティティ
     */
    public Team findById(Integer teamId) {
        return select().id(teamId).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<Team> findAllOrderById() {
        return select().orderBy(asc(teamId())).getResultList();
    }
}