package cx.myhome.ckoshien.entity;

import javax.annotation.Generated;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.unit.S2TestCase;

import static cx.myhome.ckoshien.entity.TeamNames.*;

/**
 * {@link Team}のテストクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityTestModelFactoryImpl"}, date = "2014/12/09 16:58:11")
public class TeamTest extends S2TestCase {

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
        jdbcManager.from(Team.class).id(1).getSingleResult();
    }

    /**
     * battingDetailListとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_battingDetailList() throws Exception {
        jdbcManager.from(Team.class).leftOuterJoin(battingDetailList()).id(1).getSingleResult();
    }

    /**
     * battingDetailList2との外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_battingDetailList2() throws Exception {
        jdbcManager.from(Team.class).leftOuterJoin(battingDetailList2()).id(1).getSingleResult();
    }

    /**
     * gameListとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_gameList() throws Exception {
        jdbcManager.from(Team.class).leftOuterJoin(gameList()).id(1).getSingleResult();
    }

    /**
     * gameList2との外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_gameList2() throws Exception {
        jdbcManager.from(Team.class).leftOuterJoin(gameList2()).id(1).getSingleResult();
    }

    /**
     * playerListとの外部結合をテストします。
     * 
     * @throws Exception
     */
    public void testLeftOuterJoin_playerList() throws Exception {
        jdbcManager.from(Team.class).leftOuterJoin(playerList()).id(1).getSingleResult();
    }
}