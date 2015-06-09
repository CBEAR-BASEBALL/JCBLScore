package cx.myhome.ckoshien.entity;

import cx.myhome.ckoshien.entity.BattingDetailNames._BattingDetailNames;
import cx.myhome.ckoshien.entity.BattingSumNames._BattingSumNames;
import cx.myhome.ckoshien.entity.GameNames._GameNames;
import cx.myhome.ckoshien.entity.PlayerNames._PlayerNames;
import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link Team}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2015/06/09 15:33:32")
public class TeamNames {

    /**
     * teamIdのプロパティ名を返します。
     * 
     * @return teamIdのプロパティ名
     */
    public static PropertyName<Integer> teamId() {
        return new PropertyName<Integer>("teamId");
    }

    /**
     * teamNameのプロパティ名を返します。
     * 
     * @return teamNameのプロパティ名
     */
    public static PropertyName<String> teamName() {
        return new PropertyName<String>("teamName");
    }

    /**
     * battingDetailListのプロパティ名を返します。
     * 
     * @return battingDetailListのプロパティ名
     */
    public static _BattingDetailNames battingDetailList() {
        return new _BattingDetailNames("battingDetailList");
    }

    /**
     * battingDetailList2のプロパティ名を返します。
     * 
     * @return battingDetailList2のプロパティ名
     */
    public static _BattingDetailNames battingDetailList2() {
        return new _BattingDetailNames("battingDetailList2");
    }

    /**
     * battingSumListのプロパティ名を返します。
     * 
     * @return battingSumListのプロパティ名
     */
    public static _BattingSumNames battingSumList() {
        return new _BattingSumNames("battingSumList");
    }

    /**
     * battingSumList2のプロパティ名を返します。
     * 
     * @return battingSumList2のプロパティ名
     */
    public static _BattingSumNames battingSumList2() {
        return new _BattingSumNames("battingSumList2");
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
     * gameList2のプロパティ名を返します。
     * 
     * @return gameList2のプロパティ名
     */
    public static _GameNames gameList2() {
        return new _GameNames("gameList2");
    }

    /**
     * playerListのプロパティ名を返します。
     * 
     * @return playerListのプロパティ名
     */
    public static _PlayerNames playerList() {
        return new _PlayerNames("playerList");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _TeamNames extends PropertyName<Team> {

        /**
         * インスタンスを構築します。
         */
        public _TeamNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _TeamNames(final String name) {
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
        public _TeamNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * teamIdのプロパティ名を返します。
         *
         * @return teamIdのプロパティ名
         */
        public PropertyName<Integer> teamId() {
            return new PropertyName<Integer>(this, "teamId");
        }

        /**
         * teamNameのプロパティ名を返します。
         *
         * @return teamNameのプロパティ名
         */
        public PropertyName<String> teamName() {
            return new PropertyName<String>(this, "teamName");
        }

        /**
         * battingDetailListのプロパティ名を返します。
         * 
         * @return battingDetailListのプロパティ名
         */
        public _BattingDetailNames battingDetailList() {
            return new _BattingDetailNames(this, "battingDetailList");
        }

        /**
         * battingDetailList2のプロパティ名を返します。
         * 
         * @return battingDetailList2のプロパティ名
         */
        public _BattingDetailNames battingDetailList2() {
            return new _BattingDetailNames(this, "battingDetailList2");
        }

        /**
         * battingSumListのプロパティ名を返します。
         * 
         * @return battingSumListのプロパティ名
         */
        public _BattingSumNames battingSumList() {
            return new _BattingSumNames(this, "battingSumList");
        }

        /**
         * battingSumList2のプロパティ名を返します。
         * 
         * @return battingSumList2のプロパティ名
         */
        public _BattingSumNames battingSumList2() {
            return new _BattingSumNames(this, "battingSumList2");
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
         * gameList2のプロパティ名を返します。
         * 
         * @return gameList2のプロパティ名
         */
        public _GameNames gameList2() {
            return new _GameNames(this, "gameList2");
        }

        /**
         * playerListのプロパティ名を返します。
         * 
         * @return playerListのプロパティ名
         */
        public _PlayerNames playerList() {
            return new _PlayerNames(this, "playerList");
        }
    }
}