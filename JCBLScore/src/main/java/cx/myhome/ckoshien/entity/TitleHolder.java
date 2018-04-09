package cx.myhome.ckoshien.entity;

import java.io.Serializable;
import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * TitleHolderエンティティクラス
 * 
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"}, date = "2018/04/08 16:30:09")
public class TitleHolder implements Serializable {

    private static final long serialVersionUID = 1L;

    /** playerIdプロパティ */
    @Id
    @Column(precision = 10, nullable = false, unique = false)
    public Integer playerId;

    /** leagueIdプロパティ */
    @Id
    @Column(precision = 10, nullable = false, unique = false)
    public Integer leagueId;

    /** eventTypeプロパティ */
    @Id
    @Column(precision = 10, nullable = false, unique = false)
    public Integer eventType;

    /** valueプロパティ */
    @Column(precision = 22, nullable = false, unique = false)
    public Double value;

    /** lockFlgプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer lockFlg;

    /** player関連プロパティ */
    @ManyToOne
    public Player player;

    /** league関連プロパティ */
    @ManyToOne
    public League league;
}