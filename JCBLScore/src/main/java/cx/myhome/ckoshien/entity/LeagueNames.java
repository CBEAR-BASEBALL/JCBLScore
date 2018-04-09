package cx.myhome.ckoshien.entity;

import cx.myhome.ckoshien.entity.GameNames._GameNames;
import cx.myhome.ckoshien.entity.ResultNames._ResultNames;
import java.sql.Date;
import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link League}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2018/04/08 16:30:11")
public class LeagueNames {

    /**
     * idのプロパティ名を返します。
     * 
     * @return idのプロパティ名
     */
    public static PropertyName<Integer> id() {
        return new PropertyName<Integer>("id");
    }

    /**
     * titleのプロパティ名を返します。
     * 
     * @return titleのプロパティ名
     */
    public static PropertyName<String> title() {
        return new PropertyName<String>("title");
    }

    /**
     * beginDateのプロパティ名を返します。
     * 
     * @return beginDateのプロパティ名
     */
    public static PropertyName<Date> beginDate() {
        return new PropertyName<Date>("beginDate");
    }

    /**
     * endDateのプロパティ名を返します。
     * 
     * @return endDateのプロパティ名
     */
    public static PropertyName<Date> endDate() {
        return new PropertyName<Date>("endDate");
    }

    /**
     * totalFlgのプロパティ名を返します。
     * 
     * @return totalFlgのプロパティ名
     */
    public static PropertyName<Integer> totalFlg() {
        return new PropertyName<Integer>("totalFlg");
    }

    /**
     * shortTitleのプロパティ名を返します。
     * 
     * @return shortTitleのプロパティ名
     */
    public static PropertyName<String> shortTitle() {
        return new PropertyName<String>("shortTitle");
    }

    /**
     * gameListのプロパティ名を返します。
     * 
     * @return gameListのプロパティ名
     */
    public static _GameNames gameList() {
        return new _GameNames("gameList");
    }

    /**
     * resultListのプロパティ名を返します。
     * 
     * @return resultListのプロパティ名
     */
    public static _ResultNames resultList() {
        return new _ResultNames("resultList");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _LeagueNames extends PropertyName<League> {

        /**
         * インスタンスを構築します。
         */
        public _LeagueNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _LeagueNames(final String name) {
            super(name);
        }

        /**
         * インスタンスを構築します。
         * 
         * @param parent
         *            親
         * @param name
         *            名前
         */
        public _LeagueNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * idのプロパティ名を返します。
         *
         * @return idのプロパティ名
         */
        public PropertyName<Integer> id() {
            return new PropertyName<Integer>(this, "id");
        }

        /**
         * titleのプロパティ名を返します。
         *
         * @return titleのプロパティ名
         */
        public PropertyName<String> title() {
            return new PropertyName<String>(this, "title");
        }

        /**
         * beginDateのプロパティ名を返します。
         *
         * @return beginDateのプロパティ名
         */
        public PropertyName<Date> beginDate() {
            return new PropertyName<Date>(this, "beginDate");
        }

        /**
         * endDateのプロパティ名を返します。
         *
         * @return endDateのプロパティ名
         */
        public PropertyName<Date> endDate() {
            return new PropertyName<Date>(this, "endDate");
        }

        /**
         * totalFlgのプロパティ名を返します。
         *
         * @return totalFlgのプロパティ名
         */
        public PropertyName<Integer> totalFlg() {
            return new PropertyName<Integer>(this, "totalFlg");
        }

        /**
         * shortTitleのプロパティ名を返します。
         *
         * @return shortTitleのプロパティ名
         */
        public PropertyName<String> shortTitle() {
            return new PropertyName<String>(this, "shortTitle");
        }

        /**
         * gameListのプロパティ名を返します。
         * 
         * @return gameListのプロパティ名
         */
        public _GameNames gameList() {
            return new _GameNames(this, "gameList");
        }

        /**
         * resultListのプロパティ名を返します。
         * 
         * @return resultListのプロパティ名
         */
        public _ResultNames resultList() {
            return new _ResultNames(this, "resultList");
        }
    }
}