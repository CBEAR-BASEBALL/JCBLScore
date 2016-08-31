package cx.myhome.ckoshien.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Weatherエンティティクラス
 * 
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"}, date = "2016/08/31 22:47:51")
public class Weather implements Serializable {

    private static final long serialVersionUID = 1L;

    /** dateプロパティ */
    @Id
    @Column(nullable = false, unique = true)
    public Date date;

    /** imgプロパティ */
    @Column(length = 50, nullable = false, unique = false)
    public String img;

    /** weatherプロパティ */
    @Column(length = 100, nullable = false, unique = false)
    public String weather;

    /** hightempプロパティ */
    @Column(length = 2, nullable = true, unique = false)
    public String hightemp;

    /** lowtempプロパティ */
    @Column(length = 2, nullable = true, unique = false)
    public String lowtemp;

    /** regtimeプロパティ */
    @Column(nullable = false, unique = false)
    public Timestamp regtime;
}