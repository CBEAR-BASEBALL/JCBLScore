package cx.myhome.ckoshien.entity;

import cx.myhome.ckoshien.entity.LeagueNames._LeagueNames;
import cx.myhome.ckoshien.entity.PlayerNames._PlayerNames;
import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link TitleHolder}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2018/04/08 16:30:11")
public class TitleHolderNames {

    /**
     * playerIdのプロパティ名を返します。
     * 
     * @return playerIdのプロパティ名
     */
    public static PropertyName<Integer> playerId() {
        return new PropertyName<Integer>("playerId");
    }

    /**
     * leagueIdのプロパティ名を返します。
     * 
     * @return leagueIdのプロパティ名
     */
    public static PropertyName<Integer> leagueId() {
        return new PropertyName<Integer>("leagueId");
    }

    /**
     * eventTypeのプロパティ名を返します。
     * 
     * @return eventTypeのプロパティ名
     */
    public static PropertyName<Integer> eventType() {
        return new PropertyName<Integer>("eventType");
    }

    /**
     * valueのプロパティ名を返します。
     * 
     * @return valueのプロパティ名
     */
    public static PropertyName<Double> value() {
        return new PropertyName<Double>("value");
    }

    /**
     * lockFlgのプロパティ名を返します。
     * 
     * @return lockFlgのプロパティ名
     */
    public static PropertyName<Integer> lockFlg() {
        return new PropertyName<Integer>("lockFlg");
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
     * leagueのプロパティ名を返します。
     * 
     * @return leagueのプロパティ名
     */
    public static _LeagueNames league() {
        return new _LeagueNames("league");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _TitleHolderNames extends PropertyName<TitleHolder> {

        /**
         * インスタンスを構築します。
         */
        public _TitleHolderNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _TitleHolderNames(final String name) {
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
        public _TitleHolderNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
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
         * leagueIdのプロパティ名を返します。
         *
         * @return leagueIdのプロパティ名
         */
        public PropertyName<Integer> leagueId() {
            return new PropertyName<Integer>(this, "leagueId");
        }

        /**
         * eventTypeのプロパティ名を返します。
         *
         * @return eventTypeのプロパティ名
         */
        public PropertyName<Integer> eventType() {
            return new PropertyName<Integer>(this, "eventType");
        }

        /**
         * valueのプロパティ名を返します。
         *
         * @return valueのプロパティ名
         */
        public PropertyName<Double> value() {
            return new PropertyName<Double>(this, "value");
        }

        /**
         * lockFlgのプロパティ名を返します。
         *
         * @return lockFlgのプロパティ名
         */
        public PropertyName<Integer> lockFlg() {
            return new PropertyName<Integer>(this, "lockFlg");
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
         * leagueのプロパティ名を返します。
         * 
         * @return leagueのプロパティ名
         */
        public _LeagueNames league() {
            return new _LeagueNames(this, "league");
        }
    }
}