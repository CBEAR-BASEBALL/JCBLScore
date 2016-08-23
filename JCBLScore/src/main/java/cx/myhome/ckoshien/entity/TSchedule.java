package cx.myhome.ckoshien.entity;

import java.io.Serializable;
import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * TScheduleエンティティクラス
 * 
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"}, date = "2016/08/24 0:03:15")
public class TSchedule implements Serializable {

    private static final long serialVersionUID = 1L;

    /** idプロパティ */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(precision = 10, nullable = false, unique = true)
    public Integer id;

    /** playerIdプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer playerId;

    /** mstIdプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer mstId;

    /** plansプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer plans;

    /** player関連プロパティ */
    @ManyToOne
    public Player player;

    /** mst関連プロパティ */
    @ManyToOne
    public MSchedule mst;
}