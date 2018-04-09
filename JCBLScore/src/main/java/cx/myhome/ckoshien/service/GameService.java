package cx.myhome.ckoshien.service;

import cx.myhome.ckoshien.dto.GameListDto;
import cx.myhome.ckoshien.dto.MinMaxLeagueDto;
import cx.myhome.ckoshien.entity.Game;
import cx.myhome.ckoshien.entity.League;

import java.util.Date;
import java.util.List;
import javax.annotation.Generated;

import static cx.myhome.ckoshien.entity.GameNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

/**
 * {@link Game}のサービスクラスです。
 *
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2014/12/09 16:58:09")
public class GameService extends AbstractService<Game> {

    /**
     * 識別子でエンティティを検索します。
     *
     * @param gameId
     *            識別子
     * @return エンティティ
     */
    public Game findById(Integer gameId) {
        return select().id(gameId).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     *
     * @return エンティティのリスト
     */
    public List<Game> findAllOrderById() {
        return select().orderBy(asc(gameId())).getResultList();
    }

    public List<Game> findByDateOrderByDate(Date gameDate) {
        return select().where("gameDate=?",gameDate).innerJoin(team()).innerJoin(team2()).orderBy(asc(gameNumber())).getResultList();
    }

    public List<Game> findByLeagueId(Integer leagueId){
    	return select().where("leagueId=?", leagueId).orderBy(desc(gameDate())).getResultList();
    }

    public List<Game> findByPeriod(Date beginDate,Date endDate){
    	return select().innerJoin(league()).where("begin_date>=? and end_date<=?", beginDate,endDate).orderBy(desc(gameDate())).getResultList();
    }

    public Game findByDateAndNumber(Date gameDate,Integer number){
    	return select().where("game_date=? and game_number=?",gameDate,number).getSingleResult();
    }

    public List<GameListDto> findAllGroupByGameDate() {
    	return jdbcManager
    	.selectBySqlFile(GameListDto.class, "cx.myhome.ckoshien.sql.GameDate.sql")
    	.getResultList();
    }

    public List<MinMaxLeagueDto> findMinMaxLeagueId() {
    	return jdbcManager
    	.selectBySqlFile(MinMaxLeagueDto.class, "cx.myhome.ckoshien.sql.MinMaxLeagueId.sql")
    	.getResultList();
    }
}