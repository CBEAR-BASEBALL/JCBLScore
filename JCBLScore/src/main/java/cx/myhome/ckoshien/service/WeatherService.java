package cx.myhome.ckoshien.service;

import cx.myhome.ckoshien.entity.Weather;
import java.sql.Date;
import java.util.List;
import javax.annotation.Generated;

import static cx.myhome.ckoshien.entity.WeatherNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

/**
 * {@link Weather}のサービスクラスです。
 *
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2016/08/31 22:16:36")
public class WeatherService extends AbstractService<Weather> {

    /**
     * 識別子でエンティティを検索します。
     *
     * @param date
     *            識別子
     * @return エンティティ
     */
    public Weather findById(Date date) {
        return select().id(date).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     *
     * @return エンティティのリスト
     */
    public List<Weather> findAllOrderByRegTime() {
        return select().orderBy(asc(regtime())).getResultList();
    }

    public List<Weather> findOldData() {
        return select().where("date<?",new Date(System.currentTimeMillis())).getResultList();
    }
}