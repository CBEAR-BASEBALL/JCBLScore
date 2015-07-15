package cx.myhome.ckoshien.entity;

import cx.myhome.ckoshien.entity.GameNames._GameNames;
import cx.myhome.ckoshien.entity.LeagueNames._LeagueNames;
import cx.myhome.ckoshien.entity.TeamNames._TeamNames;
import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link Result}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2015/07/10 16:39:37")
public class ResultNames {

    /**
     * idのプロパティ名を返します。
     * 
     * @return idのプロパティ名
     */
    public static PropertyName<Integer> id() {
        return new PropertyName<Integer>("id");
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
     * opponentのプロパティ名を返します。
     * 
     * @return opponentのプロパティ名
     */
    public static PropertyName<Integer> opponent() {
        return new PropertyName<Integer>("opponent");
    }

    /**
     * winのプロパティ名を返します。
     * 
     * @return winのプロパティ名
     */
    public static PropertyName<Integer> win() {
        return new PropertyName<Integer>("win");
    }

    /**
     * loseのプロパティ名を返します。
     * 
     * @return loseのプロパティ名
     */
    public static PropertyName<Integer> lose() {
        return new PropertyName<Integer>("lose");
    }

    /**
     * drawのプロパティ名を返します。
     * 
     * @return drawのプロパティ名
     */
    public static PropertyName<Integer> draw() {
        return new PropertyName<Integer>("draw");
    }

    /**
     * gameIdのプロパティ名を返します。
     * 
     * @return gameIdのプロパティ名
     */
    public static PropertyName<Integer> gameId() {
        return new PropertyName<Integer>("gameId");
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
     * teamのプロパティ名を返します。
     * 
     * @return teamのプロパティ名
     */
    public static _TeamNames team() {
        return new _TeamNames("team");
    }

    /**
     * team2のプロパティ名を返します。
     * 
     * @return team2のプロパティ名
     */
    public static _TeamNames team2() {
        return new _TeamNames("team2");
    }

    /**
     * gameのプロパティ名を返します。
     * 
     * @return gameのプロパティ名
     */
    public static _GameNames game() {
        return new _GameNames("game");
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
    public static class _ResultNames extends PropertyName<Result> {

        /**
         * インスタンスを構築します。
         */
        public _ResultNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _ResultNames(final String name) {
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
        public _ResultNames(final PropertyName<?> parent, final String name) {
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
         * teamIdのプロパティ名を返します。
         *
         * @return teamIdのプロパティ名
         */
        public PropertyName<Integer> teamId() {
            return new PropertyName<Integer>(this, "teamId");
        }

        /**
         * opponentのプロパティ名を返します。
         *
         * @return opponentのプロパティ名
         */
        public PropertyName<Integer> opponent() {
            return new PropertyName<Integer>(this, "opponent");
        }

        /**
         * winのプロパティ名を返します。
         *
         * @return winのプロパティ名
         */
        public PropertyName<Integer> win() {
            return new PropertyName<Integer>(this, "win");
        }

        /**
         * loseのプロパティ名を返します。
         *
         * @return loseのプロパティ名
         */
        public PropertyName<Integer> lose() {
            return new PropertyName<Integer>(this, "lose");
        }

        /**
         * drawのプロパティ名を返します。
         *
         * @return drawのプロパティ名
         */
        public PropertyName<Integer> draw() {
            return new PropertyName<Integer>(this, "draw");
        }

        /**
         * gameIdのプロパティ名を返します。
         *
         * @return gameIdのプロパティ名
         */
        public PropertyName<Integer> gameId() {
            return new PropertyName<Integer>(this, "gameId");
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
         * teamのプロパティ名を返します。
         * 
         * @return teamのプロパティ名
         */
        public _TeamNames team() {
            return new _TeamNames(this, "team");
        }

        /**
         * team2のプロパティ名を返します。
         * 
         * @return team2のプロパティ名
         */
        public _TeamNames team2() {
            return new _TeamNames(this, "team2");
        }

        /**
         * gameのプロパティ名を返します。
         * 
         * @return gameのプロパティ名
         */
        public _GameNames game() {
            return new _GameNames(this, "game");
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