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
 * BattingDetailエンティティクラス
 * 
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"}, date = "2015/06/19 22:14:13")
public class BattingDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /** idプロパティ */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(precision = 10, nullable = false, unique = true)
    public Integer id;

    /** playerIdプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer playerId;

    /** resultプロパティ */
    @Column(precision = 10, nullable = true, unique = false)
    public Integer result;

    /** gameIdプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer gameId;

    /** myteamIdプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer myteamId;

    /** phFlagプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer phFlag;

    /** missFlagプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer missFlag;

    /** pitcherIdプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer pitcherId;

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

    /** pitcher関連プロパティ */
    @ManyToOne
    public Player pitcher;

    /** team関連プロパティ */
    @ManyToOne
    @JoinColumn(name = "TEAM_ID", referencedColumnName = "TEAM_ID")
    public Team team;
}