package cx.myhome.ckoshien.entity;

import javax.annotation.Generated;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.unit.S2TestCase;

import static cx.myhome.ckoshien.entity.TeamHistoryNames.*;

/**
 * {@link TeamHistory}のテストクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityTestModelFactoryImpl"}, date = "2016/12/03 21:13:30")
public class TeamHistoryTest extends S2TestCase {

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
        jdbcManager.from(TeamHistory.class).id(1).getSingleResult();
    }

    /**
     * playerとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_player() throws Exception {
        jdbcManager.from(TeamHistory.class).leftOuterJoin(player()).id(1).getSingleResult();
    }

    /**
     * teamとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_team() throws Exception {
        jdbcManager.from(TeamHistory.class).leftOuterJoin(team()).id(1).getSingleResult();
    }

    /**
     * startLeagueとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_startLeague() throws Exception {
        jdbcManager.from(TeamHistory.class).leftOuterJoin(startLeague()).id(1).getSingleResult();
    }

    /**
     * endLeagueとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_endLeague() throws Exception {
        jdbcManager.from(TeamHistory.class).leftOuterJoin(endLeague()).id(1).getSingleResult();
    }
}