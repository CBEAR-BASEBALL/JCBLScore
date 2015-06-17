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
 * BattingSumエンティティクラス
 * 
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"}, date = "2015/06/16 18:24:02")
public class BattingSum implements Serializable {

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

    /** tpaプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer tpa;

    /** atBatsプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer atBats;

    /** hitプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer hit;

    /** rbiプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer rbi;

    /** fourBallプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer fourBall;

    /** strikeOutプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer strikeOut;

    /** twobaseプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer twobase;

    /** homerunプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer homerun;

    /** myteamIdプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer myteamId;

    /** teamIdプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer teamId;

    /** player関連プロパティ */
    @ManyToOne
    public Player player;

    /** game関連プロパティ */
    @ManyToOne
    @JoinColumn(name = "GAME_ID", referencedColumnName = "GAME_ID")
    public Game game;

    /** myTeam関連プロパティ */
    @ManyToOne
    @JoinColumn(name = "MYTEAM_ID", referencedColumnName = "TEAM_ID")
    public Team myTeam;

    /** team関連プロパティ */
    @ManyToOne
    @JoinColumn(name = "TEAM_ID", referencedColumnName = "TEAM_ID")
    public Team team;
}