package cx.myhome.ckoshien.entity;

import cx.myhome.ckoshien.entity.GameNames._GameNames;
import cx.myhome.ckoshien.entity.PlayerNames._PlayerNames;
import cx.myhome.ckoshien.entity.TeamNames._TeamNames;
import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link BattingDetail}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2014/12/09 16:58:07")
public class BattingDetailNames {

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
     * resultのプロパティ名を返します。
     * 
     * @return resultのプロパティ名
     */
    public static PropertyName<Integer> result() {
        return new PropertyName<Integer>("result");
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
     * myteamIdのプロパティ名を返します。
     * 
     * @return myteamIdのプロパティ名
     */
    public static PropertyName<Integer> myteamId() {
        return new PropertyName<Integer>("myteamId");
    }

    /**
     * phFlagのプロパティ名を返します。
     * 
     * @return phFlagのプロパティ名
     */
    public static PropertyName<Integer> phFlag() {
        return new PropertyName<Integer>("phFlag");
    }

    /**
     * missFlagのプロパティ名を返します。
     * 
     * @return missFlagのプロパティ名
     */
    public static PropertyName<Integer> missFlag() {
        return new PropertyName<Integer>("missFlag");
    }

    /**
     * pitcherIdのプロパティ名を返します。
     * 
     * @return pitcherIdのプロパティ名
     */
    public static PropertyName<Integer> pitcherId() {
        return new PropertyName<Integer>("pitcherId");
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
     * pitcherのプロパティ名を返します。
     * 
     * @return pitcherのプロパティ名
     */
    public static _PlayerNames pitcher() {
        return new _PlayerNames("pitcher");
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
    public static class _BattingDetailNames extends PropertyName<BattingDetail> {

        /**
         * インスタンスを構築します。
         */
        public _BattingDetailNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _BattingDetailNames(final String name) {
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
        public _BattingDetailNames(final PropertyName<?> parent, final String name) {
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
         * resultのプロパティ名を返します。
         *
         * @return resultのプロパティ名
         */
        public PropertyName<Integer> result() {
            return new PropertyName<Integer>(this, "result");
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
         * myteamIdのプロパティ名を返します。
         *
         * @return myteamIdのプロパティ名
         */
        public PropertyName<Integer> myteamId() {
            return new PropertyName<Integer>(this, "myteamId");
        }

        /**
         * phFlagのプロパティ名を返します。
         *
         * @return phFlagのプロパティ名
         */
        public PropertyName<Integer> phFlag() {
            return new PropertyName<Integer>(this, "phFlag");
        }

        /**
         * missFlagのプロパティ名を返します。
         *
         * @return missFlagのプロパティ名
         */
        public PropertyName<Integer> missFlag() {
            return new PropertyName<Integer>(this, "missFlag");
        }

        /**
         * pitcherIdのプロパティ名を返します。
         *
         * @return pitcherIdのプロパティ名
         */
        public PropertyName<Integer> pitcherId() {
            return new PropertyName<Integer>(this, "pitcherId");
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
         * pitcherのプロパティ名を返します。
         * 
         * @return pitcherのプロパティ名
         */
        public _PlayerNames pitcher() {
            return new _PlayerNames(this, "pitcher");
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