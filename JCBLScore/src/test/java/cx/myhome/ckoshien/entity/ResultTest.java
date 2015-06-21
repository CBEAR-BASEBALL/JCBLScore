package cx.myhome.ckoshien.entity;

import javax.annotation.Generated;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.unit.S2TestCase;

import static cx.myhome.ckoshien.entity.ResultNames.*;

/**
 * {@link Result}のテストクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityTestModelFactoryImpl"}, date = "2015/06/19 14:06:20")
public class ResultTest extends S2TestCase {

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
        jdbcManager.from(Result.class).id(1).getSingleResult();
    }

    /**
     * teamとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_team() throws Exception {
        jdbcManager.from(Result.class).leftOuterJoin(team()).id(1).getSingleResult();
    }

    /**
     * team2との外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_team2() throws Exception {
        jdbcManager.from(Result.class).leftOuterJoin(team2()).id(1).getSingleResult();
    }

    /**
     * gameとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_game() throws Exception {
        jdbcManager.from(Result.class).leftOuterJoin(game()).id(1).getSingleResult();
    }

    /**
     * leagueとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_league() throws Exception {
        jdbcManager.from(Result.class).leftOuterJoin(league()).id(1).getSingleResult();
    }
}