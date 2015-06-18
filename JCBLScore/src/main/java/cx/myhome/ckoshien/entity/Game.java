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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Gameエンティティクラス
 * 
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"}, date = "2015/06/18 16:43:19")
public class Game implements Serializable {

    private static final long serialVersionUID = 1L;

    /** gameIdプロパティ */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(precision = 10, nullable = false, unique = true)
    public Integer gameId;

    /** gameDateプロパティ */
    @Column(nullable = false, unique = false)
    public Date gameDate;

    /** gameNumberプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer gameNumber;

    /** firstTeamプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer firstTeam;

    /** lastTeamプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer lastTeam;

    /** firstRunプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer firstRun;

    /** lastRunプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer lastRun;

    /** top1stプロパティ */
    @Column(name = "TOP_1st", precision = 10, nullable = true, unique = false)
    public Integer top1st;

    /** bottom1stプロパティ */
    @Column(name = "BOTTOM_1st", precision = 10, nullable = true, unique = false)
    public Integer bottom1st;

    /** top2ndプロパティ */
    @Column(name = "TOP_2nd", precision = 10, nullable = true, unique = false)
    public Integer top2nd;

    /** bottom2ndプロパティ */
    @Column(name = "BOTTOM_2nd", precision = 10, nullable = true, unique = false)
    public Integer bottom2nd;

    /** top3rdプロパティ */
    @Column(name = "TOP_3rd", precision = 10, nullable = true, unique = false)
    public Integer top3rd;

    /** bottom3rdプロパティ */
    @Column(name = "BOTTOM_3rd", precision = 10, nullable = true, unique = false)
    public Integer bottom3rd;

    /** top4thプロパティ */
    @Column(name = "TOP_4th", precision = 10, nullable = true, unique = false)
    public Integer top4th;

    /** bottom4thプロパティ */
    @Column(name = "BOTTOM_4th", precision = 10, nullable = true, unique = false)
    public Integer bottom4th;

    /** top5thプロパティ */
    @Column(name = "TOP_5th", precision = 10, nullable = true, unique = false)
    public Integer top5th;

    /** bottom5thプロパティ */
    @Column(name = "BOTTOM_5th", precision = 10, nullable = true, unique = false)
    public Integer bottom5th;

    /** leagueIdプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer leagueId;

    /** battingDetailList関連プロパティ */
    @OneToMany(mappedBy = "game")
    public List<BattingDetail> battingDetailList;

    /** battingSumList関連プロパティ */
    @OneToMany(mappedBy = "game")
    public List<BattingSum> battingSumList;

    /** team関連プロパティ */
    @ManyToOne
    @JoinColumn(name = "FIRST_TEAM", referencedColumnName = "TEAM_ID")
    public Team team;

    /** team2関連プロパティ */
    @ManyToOne
    @JoinColumn(name = "LAST_TEAM", referencedColumnName = "TEAM_ID")
    public Team team2;

    /** pitchingList関連プロパティ */
    @OneToMany(mappedBy = "game")
    public List<Pitching> pitchingList;
}