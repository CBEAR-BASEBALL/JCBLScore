package cx.myhome.ckoshien.service;

import cx.myhome.ckoshien.entity.Game;
import java.util.List;
import javax.annotation.Generated;

import static cx.myhome.ckoshien.entity.GameNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

/**
 * {@link Game}のサービスクラスです。
 *
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2014/12/09 16:58:09")
public class GameService extends AbstractService<Game> {

    /**
     * 識別子でエンティティを検索します。
     *
     * @param gameId
     *            識別子
     * @return エンティティ
     */
    public Game findById(Integer gameId) {
    	System.out.println(gameId);
        return select().id(gameId).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     *
     * @return エンティティのリスト
     */
    public List<Game> findAllOrderById() {
        return select().orderBy(asc(gameId())).getResultList();
    }

    public List<Game> findAllOrderByDate() {
        return select().innerJoin(team()).innerJoin(team2()).orderBy(asc(gameDate())).getResultList();
    }
}