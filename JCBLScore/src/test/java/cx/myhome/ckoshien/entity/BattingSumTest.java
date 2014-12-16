package cx.myhome.ckoshien.entity;

import javax.annotation.Generated;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.unit.S2TestCase;

import static cx.myhome.ckoshien.entity.BattingSumNames.*;

/**
 * {@link BattingSum}のテストクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityTestModelFactoryImpl"}, date = "2014/12/09 16:58:11")
public class BattingSumTest extends S2TestCase {

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
        jdbcManager.from(BattingSum.class).id(1).getSingleResult();
    }

    /**
     * playerとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_player() throws Exception {
        jdbcManager.from(BattingSum.class).leftOuterJoin(player()).id(1).getSingleResult();
    }

    /**
     * gameとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_game() throws Exception {
        jdbcManager.from(BattingSum.class).leftOuterJoin(game()).id(1).getSingleResult();
    }
}