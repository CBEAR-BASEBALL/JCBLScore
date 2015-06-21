package cx.myhome.ckoshien.service;

import cx.myhome.ckoshien.dto.GameResultDto;
import cx.myhome.ckoshien.entity.Result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import static cx.myhome.ckoshien.entity.ResultNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;
import static org.seasar.extension.jdbc.parameter.Parameter.date;

/**
 * {@link Result}のサービスクラスです。
 *
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2015/06/19 14:06:18")
public class ResultService extends AbstractService<Result> {

    public Result result;
	public List<GameResultDto> resultList;

	/**
     * 識別子でエンティティを検索します。
     *
     * @param id
     *            識別子
     * @return エンティティ
     */
    public Result findById(Integer id) {
        return select().id(id).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     *
     * @return エンティティのリスト
     */
    public List<Result> findAllOrderById() {
        return select().orderBy(asc(id())).getResultList();
    }

    /**
     *
     * */
    public Result findById(Integer teamId,Integer gameId) {
    	result=select().where("teamId=? and gameId=?",teamId,gameId).orderBy(asc(id())).getSingleResult();
        return result;
    }

    public List<GameResultDto> findGameResult(Integer leagueId){
    	Map<String, Object> param = new HashMap<String, Object>();
		param.put("leagueId", leagueId);
    	resultList=
    		jdbcManager
    		.selectBySqlFile(GameResultDto.class, "cx.myhome.ckoshien.sql.GameResult.sql", param)
    		.getResultList();
    	return resultList;
    }
}