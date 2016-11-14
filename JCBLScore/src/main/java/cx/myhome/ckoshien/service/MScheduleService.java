package cx.myhome.ckoshien.service;

import cx.myhome.ckoshien.dto.PlanDto;
import cx.myhome.ckoshien.dto.PlayerDto;
import cx.myhome.ckoshien.dto.ScheduleDto;
import cx.myhome.ckoshien.entity.MSchedule;

import java.sql.Date;
import java.util.List;
import javax.annotation.Generated;

import static cx.myhome.ckoshien.entity.MScheduleNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

/**
 * {@link MSchedule}のサービスクラスです。
 *
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2016/08/24 0:01:46")
public class MScheduleService extends AbstractService<MSchedule> {

    private List<PlanDto> resultList;
	private List<ScheduleDto> scheduleList;

	/**
     * 識別子でエンティティを検索します。
     *
     * @param id
     *            識別子
     * @return エンティティ
     */
    public MSchedule findById(Integer id) {
        return select().id(id).getSingleResult();
    }
    public List<MSchedule> findByDate(Date date) {
        return select().where("date=?",date).getResultList();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     *
     * @return エンティティのリスト
     */
    public List<MSchedule> findAllOrderById() {
        return select().orderBy(asc(id())).getResultList();
    }

    public List<MSchedule> findAllOrderByDate() {
        return select().orderBy(asc(date())).getResultList();
    }

    public List<MSchedule> findOldData() {
        return select().where("date<?",new Date(System.currentTimeMillis())).getResultList();
    }

    public List<PlanDto> findAllPlan(){
    	resultList=
    		jdbcManager
    		.selectBySqlFile(PlanDto.class, "cx.myhome.ckoshien.sql.Schedule.sql")
    		.getResultList();
    	return resultList;
    }

    public List<ScheduleDto> findScheduleList(){
    	scheduleList=
    		jdbcManager
    		.selectBySqlFile(ScheduleDto.class, "cx.myhome.ckoshien.sql.PlanCount.sql")
    		.getResultList();
    	return scheduleList;
    }

}