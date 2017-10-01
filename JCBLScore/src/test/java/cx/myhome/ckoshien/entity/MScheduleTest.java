package cx.myhome.ckoshien.entity;

import javax.annotation.Generated;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.unit.S2TestCase;

import static cx.myhome.ckoshien.entity.MScheduleNames.*;

/**
 * {@link MSchedule}のテストクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityTestModelFactoryImpl"}, date = "2016/08/24 0:01:48")
public class MScheduleTest extends S2TestCase {

    private JdbcManager jdbcManager;

    /**
     * 事前処理をします。
     * 
     * @throws Exception
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        include("s2jdbc.dicon");
    }

    /**
     * 識別子による取得をテストします。
     * 
     * @throws Exception
     */
    public void testFindById() throws Exception {
        jdbcManager.from(MSchedule.class).id(1).getSingleResult();
    }

    /**
     * TScheduleListとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_TScheduleList() throws Exception {
        jdbcManager.from(MSchedule.class).leftOuterJoin(TScheduleList()).id(1).getSingleResult();
    }
}