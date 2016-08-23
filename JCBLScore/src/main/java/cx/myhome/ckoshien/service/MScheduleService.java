package cx.myhome.ckoshien.service;

import cx.myhome.ckoshien.entity.MSchedule;
import java.util.List;
import javax.annotation.Generated;

import static cx.myhome.ckoshien.entity.MScheduleNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

/**
 * {@link MSchedule}のサービスクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2016/08/24 0:01:46")
public class MScheduleService extends AbstractService<MSchedule> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param id
     *            識別子
     * @return エンティティ
     */
    public MSchedule findById(Integer id) {
        return select().id(id).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<MSchedule> findAllOrderById() {
        return select().orderBy(asc(id())).getResultList();
    }
}