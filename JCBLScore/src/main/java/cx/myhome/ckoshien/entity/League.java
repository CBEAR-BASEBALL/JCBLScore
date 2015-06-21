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
import javax.persistence.OneToMany;

/**
 * Leagueエンティティクラス
 * 
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"}, date = "2015/06/19 22:14:13")
public class League implements Serializable {

    private static final long serialVersionUID = 1L;

    /** idプロパティ */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(precision = 10, nullable = false, unique = true)
    public Integer id;

    /** titleプロパティ */
    @Column(length = 100, nullable = false, unique = false)
    public String title;

    /** beginDateプロパティ */
    @Column(nullable = false, unique = false)
    public Date beginDate;

    /** endDateプロパティ */
    @Column(nullable = false, unique = false)
    public Date endDate;

    /** totalFlgプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Integer totalFlg;

    /** resultList関連プロパティ */
    @OneToMany(mappedBy = "league")
    public List<Result> resultList;
}