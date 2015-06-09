package cx.myhome.ckoshien.entity;

import cx.myhome.ckoshien.entity.GameNames._GameNames;
import cx.myhome.ckoshien.entity.PlayerNames._PlayerNames;
import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link Pitching}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2015/06/09 15:33:32")
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
     * resultのプロパティ名を返します。
     * 
     * @return resultのプロパティ名
     */
    public static PropertyName<Integer> result() {
        return new PropertyName<Integer>("result");
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
         * resultのプロパティ名を返します。
         *
         * @return resultのプロパティ名
         */
        public PropertyName<Integer> result() {
            return new PropertyName<Integer>(this, "result");
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
    }
}