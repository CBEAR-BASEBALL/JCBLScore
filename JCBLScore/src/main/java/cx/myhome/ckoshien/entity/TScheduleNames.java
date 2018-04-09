package cx.myhome.ckoshien.entity;

import cx.myhome.ckoshien.entity.MScheduleNames._MScheduleNames;
import cx.myhome.ckoshien.entity.PlayerNames._PlayerNames;
import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link TSchedule}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2018/04/08 16:30:11")
public class TScheduleNames {

    /**
     * idのプロパティ名を返します。
     * 
     * @return idのプロパティ名
     */
    public static PropertyName<Integer> id() {
        return new PropertyName<Integer>("id");
    }

    /**
     * playerIdのプロパティ名を返します。
     * 
     * @return playerIdのプロパティ名
     */
    public static PropertyName<Integer> playerId() {
        return new PropertyName<Integer>("playerId");
    }

    /**
     * mstIdのプロパティ名を返します。
     * 
     * @return mstIdのプロパティ名
     */
    public static PropertyName<Integer> mstId() {
        return new PropertyName<Integer>("mstId");
    }

    /**
     * plansのプロパティ名を返します。
     * 
     * @return plansのプロパティ名
     */
    public static PropertyName<Integer> plans() {
        return new PropertyName<Integer>("plans");
    }

    /**
     * playerのプロパティ名を返します。
     * 
     * @return playerのプロパティ名
     */
    public static _PlayerNames player() {
        return new _PlayerNames("player");
    }

    /**
     * mstのプロパティ名を返します。
     * 
     * @return mstのプロパティ名
     */
    public static _MScheduleNames mst() {
        return new _MScheduleNames("mst");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _TScheduleNames extends PropertyName<TSchedule> {

        /**
         * インスタンスを構築します。
         */
        public _TScheduleNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _TScheduleNames(final String name) {
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
        public _TScheduleNames(final PropertyName<?> parent, final String name) {
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
         * playerIdのプロパティ名を返します。
         *
         * @return playerIdのプロパティ名
         */
        public PropertyName<Integer> playerId() {
            return new PropertyName<Integer>(this, "playerId");
        }

        /**
         * mstIdのプロパティ名を返します。
         *
         * @return mstIdのプロパティ名
         */
        public PropertyName<Integer> mstId() {
            return new PropertyName<Integer>(this, "mstId");
        }

        /**
         * plansのプロパティ名を返します。
         *
         * @return plansのプロパティ名
         */
        public PropertyName<Integer> plans() {
            return new PropertyName<Integer>(this, "plans");
        }

        /**
         * playerのプロパティ名を返します。
         * 
         * @return playerのプロパティ名
         */
        public _PlayerNames player() {
            return new _PlayerNames(this, "player");
        }

        /**
         * mstのプロパティ名を返します。
         * 
         * @return mstのプロパティ名
         */
        public _MScheduleNames mst() {
            return new _MScheduleNames(this, "mst");
        }
    }
}