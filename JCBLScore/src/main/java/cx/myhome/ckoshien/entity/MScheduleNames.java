package cx.myhome.ckoshien.entity;

import cx.myhome.ckoshien.entity.TScheduleNames._TScheduleNames;
import java.sql.Date;
import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link MSchedule}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2016/08/24 0:03:17")
public class MScheduleNames {

    /**
     * idのプロパティ名を返します。
     * 
     * @return idのプロパティ名
     */
    public static PropertyName<Integer> id() {
        return new PropertyName<Integer>("id");
    }

    /**
     * dateのプロパティ名を返します。
     * 
     * @return dateのプロパティ名
     */
    public static PropertyName<Date> date() {
        return new PropertyName<Date>("date");
    }

    /**
     * TScheduleListのプロパティ名を返します。
     * 
     * @return TScheduleListのプロパティ名
     */
    public static _TScheduleNames TScheduleList() {
        return new _TScheduleNames("TScheduleList");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _MScheduleNames extends PropertyName<MSchedule> {

        /**
         * インスタンスを構築します。
         */
        public _MScheduleNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _MScheduleNames(final String name) {
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
        public _MScheduleNames(final PropertyName<?> parent, final String name) {
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
         * dateのプロパティ名を返します。
         *
         * @return dateのプロパティ名
         */
        public PropertyName<Date> date() {
            return new PropertyName<Date>(this, "date");
        }

        /**
         * TScheduleListのプロパティ名を返します。
         * 
         * @return TScheduleListのプロパティ名
         */
        public _TScheduleNames TScheduleList() {
            return new _TScheduleNames(this, "TScheduleList");
        }
    }
}