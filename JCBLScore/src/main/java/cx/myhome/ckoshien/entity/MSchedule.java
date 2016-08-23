package cx.myhome.ckoshien.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * MScheduleエンティティクラス
 * 
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"}, date = "2016/08/24 0:03:15")
public class MSchedule implements Serializable {

    private static final long serialVersionUID = 1L;

    /** idプロパティ */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(precision = 10, nullable = false, unique = true)
    public Integer id;

    /** dateプロパティ */
    @Column(nullable = false, unique = false)
    public Date date;

    /** TScheduleList関連プロパティ */
    @OneToMany(mappedBy = "mst")
    public List<TSchedule> TScheduleList;
}