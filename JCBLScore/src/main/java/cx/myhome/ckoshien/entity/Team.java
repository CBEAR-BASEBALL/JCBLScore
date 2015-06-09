package cx.myhome.ckoshien.entity;

import java.io.Serializable;
import java.util.List;
import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Teamエンティティクラス
 * 
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"}, date = "2015/06/09 15:33:30")
public class Team implements Serializable {

    private static final long serialVersionUID = 1L;

    /** teamIdプロパティ */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(precision = 10, nullable = false, unique = true)
    public Integer teamId;

    /** teamNameプロパティ */
    @Column(length = 200, nullable = false, unique = false)
    public String teamName;

    /** battingDetailList関連プロパティ */
    @OneToMany(mappedBy = "myTeam")
    public List<BattingDetail> battingDetailList;

    /** battingDetailList2関連プロパティ */
    @OneToMany(mappedBy = "team")
    public List<BattingDetail> battingDetailList2;

    /** battingSumList関連プロパティ */
    @OneToMany(mappedBy = "myTeam")
    public List<BattingSum> battingSumList;

    /** battingSumList2関連プロパティ */
    @OneToMany(mappedBy = "team")
    public List<BattingSum> battingSumList2;

    /** gameList関連プロパティ */
    @OneToMany(mappedBy = "team")
    public List<Game> gameList;

    /** gameList2関連プロパティ */
    @OneToMany(mappedBy = "team2")
    public List<Game> gameList2;

    /** playerList関連プロパティ */
    @OneToMany(mappedBy = "team")
    public List<Player> playerList;
}