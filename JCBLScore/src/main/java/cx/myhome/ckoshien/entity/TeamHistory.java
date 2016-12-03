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
 * TeamHistoryエンティティクラス
 * 
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"}, date = "2016/12/03 21:13:22")
public class TeamHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    /** idプロパティ */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(precision = 10, nullable = false, unique = true)
    public Integer id;

    /** playerIdプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer playerId;

    /** teamIdプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer teamId;

    /** startLeagueIdプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer startLeagueId;

    /** endLeagueIdプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer endLeagueId;

    /** player関連プロパティ */
    @ManyToOne
    public Player player;

    /** team関連プロパティ */
    @ManyToOne
    @JoinColumn(name = "TEAM_ID", referencedColumnName = "TEAM_ID")
    public Team team;

    /** startLeague関連プロパティ */
    @ManyToOne
    @JoinColumn(name = "START_LEAGUE_ID", referencedColumnName = "ID")
    public League startLeague;

    /** endLeague関連プロパティ */
    @ManyToOne
    @JoinColumn(name = "END_LEAGUE_ID", referencedColumnName = "ID")
    public League endLeague;
}