package cx.myhome.ckoshien.entity;

import cx.myhome.ckoshien.entity.GameNames._GameNames;
import cx.myhome.ckoshien.entity.PlayerNames._PlayerNames;
import cx.myhome.ckoshien.entity.TeamNames._TeamNames;
import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link BattingSum}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2015/06/18 22:37:12")
public class BattingSumNames {

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
     * gameIdのプロパティ名を返します。
     * 
     * @return gameIdのプロパティ名
     */
    public static PropertyName<Integer> gameId() {
        return new PropertyName<Integer>("gameId");
    }

    /**
     * tpaのプロパティ名を返します。
     * 
     * @return tpaのプロパティ名
     */
    public static PropertyName<Integer> tpa() {
        return new PropertyName<Integer>("tpa");
    }

    /**
     * atBatsのプロパティ名を返します。
     * 
     * @return atBatsのプロパティ名
     */
    public static PropertyName<Integer> atBats() {
        return new PropertyName<Integer>("atBats");
    }

    /**
     * hitのプロパティ名を返します。
     * 
     * @return hitのプロパティ名
     */
    public static PropertyName<Integer> hit() {
        return new PropertyName<Integer>("hit");
    }

    /**
     * rbiのプロパティ名を返します。
     * 
     * @return rbiのプロパティ名
     */
    public static PropertyName<Integer> rbi() {
        return new PropertyName<Integer>("rbi");
    }

    /**
     * fourBallのプロパティ名を返します。
     * 
     * @return fourBallのプロパティ名
     */
    public static PropertyName<Integer> fourBall() {
        return new PropertyName<Integer>("fourBall");
    }

    /**
     * strikeOutのプロパティ名を返します。
     * 
     * @return strikeOutのプロパティ名
     */
    public static PropertyName<Integer> strikeOut() {
        return new PropertyName<Integer>("strikeOut");
    }

    /**
     * twobaseのプロパティ名を返します。
     * 
     * @return twobaseのプロパティ名
     */
    public static PropertyName<Integer> twobase() {
        return new PropertyName<Integer>("twobase");
    }

    /**
     * homerunのプロパティ名を返します。
     * 
     * @return homerunのプロパティ名
     */
    public static PropertyName<Integer> homerun() {
        return new PropertyName<Integer>("homerun");
    }

    /**
     * myteamIdのプロパティ名を返します。
     * 
     * @return myteamIdのプロパティ名
     */
    public static PropertyName<Integer> myteamId() {
        return new PropertyName<Integer>("myteamId");
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
     * playerのプロパティ名を返します。
     * 
     * @return playerのプロパティ名
     */
    public static _PlayerNames player() {
        return new _PlayerNames("player");
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
     * myTeamのプロパティ名を返します。
     * 
     * @return myTeamのプロパティ名
     */
    public static _TeamNames myTeam() {
        return new _TeamNames("myTeam");
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
     * @author S2JDBC-Gen
     */
    public static class _BattingSumNames extends PropertyName<BattingSum> {

        /**
         * インスタンスを構築します。
         */
        public _BattingSumNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _BattingSumNames(final String name) {
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
        public _BattingSumNames(final PropertyName<?> parent, final String name) {
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
         * gameIdのプロパティ名を返します。
         *
         * @return gameIdのプロパティ名
         */
        public PropertyName<Integer> gameId() {
            return new PropertyName<Integer>(this, "gameId");
        }

        /**
         * tpaのプロパティ名を返します。
         *
         * @return tpaのプロパティ名
         */
        public PropertyName<Integer> tpa() {
            return new PropertyName<Integer>(this, "tpa");
        }

        /**
         * atBatsのプロパティ名を返します。
         *
         * @return atBatsのプロパティ名
         */
        public PropertyName<Integer> atBats() {
            return new PropertyName<Integer>(this, "atBats");
        }

        /**
         * hitのプロパティ名を返します。
         *
         * @return hitのプロパティ名
         */
        public PropertyName<Integer> hit() {
            return new PropertyName<Integer>(this, "hit");
        }

        /**
         * rbiのプロパティ名を返します。
         *
         * @return rbiのプロパティ名
         */
        public PropertyName<Integer> rbi() {
            return new PropertyName<Integer>(this, "rbi");
        }

        /**
         * fourBallのプロパティ名を返します。
         *
         * @return fourBallのプロパティ名
         */
        public PropertyName<Integer> fourBall() {
            return new PropertyName<Integer>(this, "fourBall");
        }

        /**
         * strikeOutのプロパティ名を返します。
         *
         * @return strikeOutのプロパティ名
         */
        public PropertyName<Integer> strikeOut() {
            return new PropertyName<Integer>(this, "strikeOut");
        }

        /**
         * twobaseのプロパティ名を返します。
         *
         * @return twobaseのプロパティ名
         */
        public PropertyName<Integer> twobase() {
            return new PropertyName<Integer>(this, "twobase");
        }

        /**
         * homerunのプロパティ名を返します。
         *
         * @return homerunのプロパティ名
         */
        public PropertyName<Integer> homerun() {
            return new PropertyName<Integer>(this, "homerun");
        }

        /**
         * myteamIdのプロパティ名を返します。
         *
         * @return myteamIdのプロパティ名
         */
        public PropertyName<Integer> myteamId() {
            return new PropertyName<Integer>(this, "myteamId");
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
         * playerのプロパティ名を返します。
         * 
         * @return playerのプロパティ名
         */
        public _PlayerNames player() {
            return new _PlayerNames(this, "player");
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
         * myTeamのプロパティ名を返します。
         * 
         * @return myTeamのプロパティ名
         */
        public _TeamNames myTeam() {
            return new _TeamNames(this, "myTeam");
        }

        /**
         * teamのプロパティ名を返します。
         * 
         * @return teamのプロパティ名
         */
        public _TeamNames team() {
            return new _TeamNames(this, "team");
        }
    }
}