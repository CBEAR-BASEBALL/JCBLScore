package cx.myhome.ckoshien.service;

import cx.myhome.ckoshien.entity.Player;
import java.util.List;
import javax.annotation.Generated;

import static cx.myhome.ckoshien.entity.PlayerNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

/**
 * {@link Player}のサービスクラスです。
 *
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2014/12/09 16:58:09")
public class PlayerService extends AbstractService<Player> {

    /**
     * 識別子でエンティティを検索します。
     *
     * @param id
     *            識別子
     * @return エンティティ
     */
    public Player findById(Integer id) {
        return select().id(id).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     *
     * @return エンティティのリスト
     */
    public List<Player> findAllOrderById() {
        return select().orderBy(asc(teamId())).getResultList();
    }

	public Player findByLoginId(String loginId) {
		return select().where("loginId=?", loginId).getSingleResult();
	}
}