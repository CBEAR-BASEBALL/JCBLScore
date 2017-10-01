package cx.myhome.ckoshien.entity;

import javax.annotation.Generated;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.unit.S2TestCase;

import static cx.myhome.ckoshien.entity.TScheduleNames.*;

/**
 * {@link TSchedule}のテストクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityTestModelFactoryImpl"}, date = "2016/08/24 0:01:48")
public class TScheduleTest extends S2TestCase {

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
        jdbcManager.from(TSchedule.class).id(1).getSingleResult();
    }

    /**
     * playerとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_player() throws Exception {
        jdbcManager.from(TSchedule.class).leftOuterJoin(player()).id(1).getSingleResult();
    }

    /**
     * mstとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_mst() throws Exception {
        jdbcManager.from(TSchedule.class).leftOuterJoin(mst()).id(1).getSingleResult();
    }
}