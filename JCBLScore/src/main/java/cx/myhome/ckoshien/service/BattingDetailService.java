package cx.myhome.ckoshien.service;

import cx.myhome.ckoshien.entity.BattingDetail;
import java.util.List;
import javax.annotation.Generated;

import static cx.myhome.ckoshien.entity.BattingDetailNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

/**
 * {@link BattingDetail}のサービスクラスです。
 *
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2014/12/09 16:58:09")
public class BattingDetailService extends AbstractService<BattingDetail> {

    /**
     * 識別子でエンティティを検索します。
     *
     * @param id
     *            識別子
     * @return エンティティ
     */
    public BattingDetail findById(Integer id) {
        return select().id(id).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     *
     * @return エンティティのリスト
     */
    public List<BattingDetail> findAllOrderById() {
        return select().orderBy(asc(id())).getResultList();
    }
    public List<BattingDetail> findGameDetail(int teamId,int gameId) {
        return select().where("teamId=? and gameId=?",teamId, gameId).getResultList();
    }

}