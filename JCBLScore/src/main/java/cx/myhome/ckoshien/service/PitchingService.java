package cx.myhome.ckoshien.service;

import cx.myhome.ckoshien.entity.Pitching;
import java.util.List;
import javax.annotation.Generated;

import static cx.myhome.ckoshien.entity.PitchingNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

/**
 * {@link Pitching}のサービスクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2014/12/09 16:58:09")
public class PitchingService extends AbstractService<Pitching> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param id
     *            識別子
     * @return エンティティ
     */
    public Pitching findById(Integer id) {
        return select().id(id).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<Pitching> findAllOrderById() {
        return select().orderBy(asc(id())).getResultList();
    }
}