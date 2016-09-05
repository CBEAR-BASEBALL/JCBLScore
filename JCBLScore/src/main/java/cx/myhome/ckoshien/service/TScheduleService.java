package cx.myhome.ckoshien.service;

import cx.myhome.ckoshien.entity.TSchedule;
import java.util.List;
import javax.annotation.Generated;

import static cx.myhome.ckoshien.entity.TScheduleNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

/**
 * {@link TSchedule}のサービスクラスです。
 *
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2016/08/24 0:01:46")
public class TScheduleService extends AbstractService<TSchedule> {

    /**
     * 識別子でエンティティを検索します。
     *
     * @param id
     *            識別子
     * @return エンティティ
     */
    public TSchedule findById(Integer id) {
        return select().id(id).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     *
     * @return エンティティのリスト
     */
    public List<TSchedule> findAllOrderById() {
        return select().orderBy(asc(id())).getResultList();
    }

    public List<TSchedule> findOldData(Integer id){
    	return select().where("mst_id=?", id).getResultList();
    }
    public List<TSchedule> findByPlayerId(Integer playerId){
    	return select().where("player_id=?", playerId).getResultList();
    }
    public List<TSchedule> findByPlayerIdAndMstId(Integer playerId,Integer mstId){
    	return select().where("player_id=? and mstId=?", playerId,mstId).getResultList();
    }
}