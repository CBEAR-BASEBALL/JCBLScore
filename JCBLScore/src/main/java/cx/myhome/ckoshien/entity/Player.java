package cx.myhome.ckoshien.entity;

import java.io.Serializable;
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
 * Playerエンティティクラス
 * 
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"}, date = "2015/06/16 18:24:02")
public class Player implements Serializable {

    private static final long serialVersionUID = 1L;

    /** idプロパティ */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(precision = 10, nullable = false, unique = true)
    public Integer id;

    /** nameプロパティ */
    @Column(length = 50, nullable = false, unique = false)
    public String name;

    /** teamIdプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer teamId;

    /** battingDetailList関連プロパティ */
    @OneToMany(mappedBy = "player")
    public List<BattingDetail> battingDetailList;

    /** battingDetailList2関連プロパティ */
    @OneToMany(mappedBy = "pitcher")
    public List<BattingDetail> battingDetailList2;

    /** battingSumList関連プロパティ */
    @OneToMany(mappedBy = "player")
    public List<BattingSum> battingSumList;

    /** pitchingList関連プロパティ */
    @OneToMany(mappedBy = "player")
    public List<Pitching> pitchingList;

    /** team関連プロパティ */
    @ManyToOne
    @JoinColumn(name = "TEAM_ID", referencedColumnName = "TEAM_ID")
    public Team team;
}