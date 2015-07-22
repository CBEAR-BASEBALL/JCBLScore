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
 * Resultエンティティクラス
 * 
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"}, date = "2015/07/22 16:23:17")
public class Result implements Serializable {

    private static final long serialVersionUID = 1L;

    /** idプロパティ */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(precision = 10, nullable = false, unique = true)
    public Integer id;

    /** teamIdプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer teamId;

    /** opponentプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer opponent;

    /** winプロパティ */
    @Column(precision = 10, nullable = true, unique = false)
    public Integer win;

    /** loseプロパティ */
    @Column(precision = 10, nullable = true, unique = false)
    public Integer lose;

    /** drawプロパティ */
    @Column(precision = 10, nullable = true, unique = false)
    public Integer draw;

    /** gameIdプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer gameId;

    /** leagueIdプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer leagueId;

    /** team関連プロパティ */
    @ManyToOne
    @JoinColumn(name = "TEAM_ID", referencedColumnName = "TEAM_ID")
    public Team team;

    /** team2関連プロパティ */
    @ManyToOne
    @JoinColumn(name = "OPPONENT", referencedColumnName = "TEAM_ID")
    public Team team2;

    /** game関連プロパティ */
    @ManyToOne
    @JoinColumn(name = "GAME_ID", referencedColumnName = "GAME_ID")
    public Game game;

    /** league関連プロパティ */
    @ManyToOne
    public League league;
}