package cx.myhome.ckoshien.entity;

import cx.myhome.ckoshien.entity.GameNames._GameNames;
import cx.myhome.ckoshien.entity.PlayerNames._PlayerNames;
import cx.myhome.ckoshien.entity.TeamNames._TeamNames;
import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link Pitching}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2016/08/24 0:03:17")
public class PitchingNames {

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
     * inningのプロパティ名を返します。
     * 
     * @return inningのプロパティ名
     */
    public static PropertyName<Double> inning() {
        return new PropertyName<Double>("inning");
    }

    /**
     * paのプロパティ名を返します。
     * 
     * @return paのプロパティ名
     */
    public static PropertyName<Integer> pa() {
        return new PropertyName<Integer>("pa");
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
     * homerunのプロパティ名を返します。
     * 
     * @return homerunのプロパティ名
     */
    public static PropertyName<Integer> homerun() {
        return new PropertyName<Integer>("homerun");
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
     * runsのプロパティ名を返します。
     * 
     * @return runsのプロパティ名
     */
    public static PropertyName<Integer> runs() {
        return new PropertyName<Integer>("runs");
    }

    /**
     * completeのプロパティ名を返します。
     * 
     * @return completeのプロパティ名
     */
    public static PropertyName<Integer> complete() {
        return new PropertyName<Integer>("complete");
    }

    /**
     * shutoutのプロパティ名を返します。
     * 
     * @return shutoutのプロパティ名
     */
    public static PropertyName<Integer> shutout() {
        return new PropertyName<Integer>("shutout");
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
     * saveのプロパティ名を返します。
     * 
     * @return saveのプロパティ名
     */
    public static PropertyName<Integer> save() {
        return new PropertyName<Integer>("save");
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
    public static class _PitchingNames extends PropertyName<Pitching> {

        /**
         * インスタンスを構築します。
         */
        public _PitchingNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _PitchingNames(final String name) {
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
        public _PitchingNames(final PropertyName<?> parent, final String name) {
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
         * inningのプロパティ名を返します。
         *
         * @return inningのプロパティ名
         */
        public PropertyName<Double> inning() {
            return new PropertyName<Double>(this, "inning");
        }

        /**
         * paのプロパティ名を返します。
         *
         * @return paのプロパティ名
         */
        public PropertyName<Integer> pa() {
            return new PropertyName<Integer>(this, "pa");
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
         * homerunのプロパティ名を返します。
         *
         * @return homerunのプロパティ名
         */
        public PropertyName<Integer> homerun() {
            return new PropertyName<Integer>(this, "homerun");
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
         * runsのプロパティ名を返します。
         *
         * @return runsのプロパティ名
         */
        public PropertyName<Integer> runs() {
            return new PropertyName<Integer>(this, "runs");
        }

        /**
         * completeのプロパティ名を返します。
         *
         * @return completeのプロパティ名
         */
        public PropertyName<Integer> complete() {
            return new PropertyName<Integer>(this, "complete");
        }

        /**
         * shutoutのプロパティ名を返します。
         *
         * @return shutoutのプロパティ名
         */
        public PropertyName<Integer> shutout() {
            return new PropertyName<Integer>(this, "shutout");
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
         * saveのプロパティ名を返します。
         *
         * @return saveのプロパティ名
         */
        public PropertyName<Integer> save() {
            return new PropertyName<Integer>(this, "save");
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