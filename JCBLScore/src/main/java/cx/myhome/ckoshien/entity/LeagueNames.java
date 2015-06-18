package cx.myhome.ckoshien.entity;

import java.sql.Date;
import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link League}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2015/06/18 22:37:12")
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
    }
}