package cx.myhome.ckoshien.service;

import cx.myhome.ckoshien.entity.Migrations;
import java.util.List;
import javax.annotation.Generated;

import static cx.myhome.ckoshien.entity.MigrationsNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

/**
 * {@link Migrations}のサービスクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2018/04/08 16:30:13")
public class MigrationsService extends AbstractService<Migrations> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param id
     *            識別子
     * @return エンティティ
     */
    public Migrations findById(Integer id) {
        return select().id(id).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<Migrations> findAllOrderById() {
        return select().orderBy(asc(id())).getResultList();
    }
}