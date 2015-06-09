package cx.myhome.ckoshien.entity;

import java.io.Serializable;
import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Pitchingエンティティクラス
 * 
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"}, date = "2015/06/09 15:33:30")
public class Pitching implements Serializable {

    private static final long serialVersionUID = 1L;

    /** idプロパティ */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(precision = 10, nullable = false, unique = true)
    public Integer id;

    /** playerIdプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer playerId;

    /** gameIdプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer gameId;

    /** inningプロパティ */
    @Column(precision = 3, scale = 3, nullable = false, unique = false)
    public Double inning;

    /** paプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer pa;

    /** hitプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer hit;

    /** homerunプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer homerun;

    /** fourBallプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer fourBall;

    /** strikeOutプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer strikeOut;

    /** runsプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer runs;

    /** completeプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer complete;

    /** shutoutプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer shutout;

    /** resultプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer result;

    /** player関連プロパティ */
    @ManyToOne
    public Player player;

    /** game関連プロパティ */
    @ManyToOne
    @JoinColumn(name = "GAME_ID", referencedColumnName = "GAME_ID")
    public Game game;
}