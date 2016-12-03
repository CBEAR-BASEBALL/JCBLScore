package cx.myhome.ckoshien.entity;

import cx.myhome.ckoshien.entity.LeagueNames._LeagueNames;
import cx.myhome.ckoshien.entity.PlayerNames._PlayerNames;
import cx.myhome.ckoshien.entity.TeamNames._TeamNames;
import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link TeamHistory}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2016/12/03 21:18:45")
public class TeamHistoryNames {

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
     * teamIdのプロパティ名を返します。
     * 
     * @return teamIdのプロパティ名
     */
    public static PropertyName<Integer> teamId() {
        return new PropertyName<Integer>("teamId");
    }

    /**
     * startLeagueIdのプロパティ名を返します。
     * 
     * @return startLeagueIdのプロパティ名
     */
    public static PropertyName<Integer> startLeagueId() {
        return new PropertyName<Integer>("startLeagueId");
    }

    /**
     * endLeagueIdのプロパティ名を返します。
     * 
     * @return endLeagueIdのプロパティ名
     */
    public static PropertyName<Integer> endLeagueId() {
        return new PropertyName<Integer>("endLeagueId");
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
     * teamのプロパティ名を返します。
     * 
     * @return teamのプロパティ名
     */
    public static _TeamNames team() {
        return new _TeamNames("team");
    }

    /**
     * startLeagueのプロパティ名を返します。
     * 
     * @return startLeagueのプロパティ名
     */
    public static _LeagueNames startLeague() {
        return new _LeagueNames("startLeague");
    }

    /**
     * endLeagueのプロパティ名を返します。
     * 
     * @return endLeagueのプロパティ名
     */
    public static _LeagueNames endLeague() {
        return new _LeagueNames("endLeague");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _TeamHistoryNames extends PropertyName<TeamHistory> {

        /**
         * インスタンスを構築します。
         */
        public _TeamHistoryNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _TeamHistoryNames(final String name) {
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
        public _TeamHistoryNames(final PropertyName<?> parent, final String name) {
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
         * teamIdのプロパティ名を返します。
         *
         * @return teamIdのプロパティ名
         */
        public PropertyName<Integer> teamId() {
            return new PropertyName<Integer>(this, "teamId");
        }

        /**
         * startLeagueIdのプロパティ名を返します。
         *
         * @return startLeagueIdのプロパティ名
         */
        public PropertyName<Integer> startLeagueId() {
            return new PropertyName<Integer>(this, "startLeagueId");
        }

        /**
         * endLeagueIdのプロパティ名を返します。
         *
         * @return endLeagueIdのプロパティ名
         */
        public PropertyName<Integer> endLeagueId() {
            return new PropertyName<Integer>(this, "endLeagueId");
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
         * teamのプロパティ名を返します。
         * 
         * @return teamのプロパティ名
         */
        public _TeamNames team() {
            return new _TeamNames(this, "team");
        }

        /**
         * startLeagueのプロパティ名を返します。
         * 
         * @return startLeagueのプロパティ名
         */
        public _LeagueNames startLeague() {
            return new _LeagueNames(this, "startLeague");
        }

        /**
         * endLeagueのプロパティ名を返します。
         * 
         * @return endLeagueのプロパティ名
         */
        public _LeagueNames endLeague() {
            return new _LeagueNames(this, "endLeague");
        }
    }
}