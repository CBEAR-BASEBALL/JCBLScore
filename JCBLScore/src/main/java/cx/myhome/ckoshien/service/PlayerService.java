package cx.myhome.ckoshien.service;

import cx.myhome.ckoshien.dto.PlayerDto;
import cx.myhome.ckoshien.entity.Player;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

/**
 * {@link Player}のサービスクラスです。
 *
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2014/12/09 16:58:09")
public class PlayerService extends AbstractService<Player> {

    public List<PlayerDto> playerList;

	/**
     * 識別子でエンティティを検索します。
     *
     * @param id
     *            識別子
     * @return エンティティ
     */
    public Player findById(Integer id) {
        return select().id(id).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     *
     * @return エンティティのリスト
     */
//    public List<Player> findAllOrderById() {
//        return select().innerJoin(team()).orderBy(asc(teamId())).getResultList();
//    }
    public List<PlayerDto> findAllOrderById(){
		playerList=new ArrayList<PlayerDto>();
		playerList=jdbcManager.selectBySqlFile(PlayerDto.class, "cx.myhome.ckoshien.sql.TeamDate.sql").getResultList();
		return playerList;
	}

    public List<PlayerDto> findPlayerHasPlan(){
		playerList=new ArrayList<PlayerDto>();
		playerList=jdbcManager.selectBySqlFile(PlayerDto.class, "cx.myhome.ckoshien.sql.PlayerHasPlan.sql").getResultList();
		return playerList;
	}

    public List<PlayerDto> findPlayerHasNoPlan(){
		playerList=new ArrayList<PlayerDto>();
		playerList=jdbcManager.selectBySqlFile(PlayerDto.class, "cx.myhome.ckoshien.sql.PlayerHasNoPlan.sql").getResultList();
		return playerList;
	}


	public Player findByLoginId(String loginId) {
		return select().where("loginId=?", loginId).getSingleResult();
	}

	public Player findByNameAndTeamId(String name,Integer teamId) {
		return select().where("name=? and teamId=?", name,teamId).getSingleResult();
	}

	public List<Player> findByLikeName(String name) {
		return select().where("name like ?", "%"+name+"%").getResultList();
	}
}