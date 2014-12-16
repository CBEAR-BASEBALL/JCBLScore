package cx.myhome.ckoshien.entity;

import javax.annotation.Generated;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.unit.S2TestCase;

import static cx.myhome.ckoshien.entity.PlayerNames.*;

/**
 * {@link Player}のテストクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityTestModelFactoryImpl"}, date = "2014/12/09 16:58:11")
public class PlayerTest extends S2TestCase {

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
        jdbcManager.from(Player.class).id(1).getSingleResult();
    }

    /**
     * battingDetailListとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_battingDetailList() throws Exception {
        jdbcManager.from(Player.class).leftOuterJoin(battingDetailList()).id(1).getSingleResult();
    }

    /**
     * battingDetailList2との外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_battingDetailList2() throws Exception {
        jdbcManager.from(Player.class).leftOuterJoin(battingDetailList2()).id(1).getSingleResult();
    }

    /**
     * battingSumListとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_battingSumList() throws Exception {
        jdbcManager.from(Player.class).leftOuterJoin(battingSumList()).id(1).getSingleResult();
    }

    /**
     * pitchingListとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_pitchingList() throws Exception {
        jdbcManager.from(Player.class).leftOuterJoin(pitchingList()).id(1).getSingleResult();
    }

    /**
     * teamとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_team() throws Exception {
        jdbcManager.from(Player.class).leftOuterJoin(team()).id(1).getSingleResult();
    }
}