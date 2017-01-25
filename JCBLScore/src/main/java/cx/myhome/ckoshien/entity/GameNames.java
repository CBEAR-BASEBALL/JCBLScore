package cx.myhome.ckoshien.entity;

import cx.myhome.ckoshien.entity.BattingDetailNames._BattingDetailNames;
import cx.myhome.ckoshien.entity.BattingSumNames._BattingSumNames;
import cx.myhome.ckoshien.entity.LeagueNames._LeagueNames;
import cx.myhome.ckoshien.entity.PitchingNames._PitchingNames;
import cx.myhome.ckoshien.entity.ResultNames._ResultNames;
import cx.myhome.ckoshien.entity.TeamNames._TeamNames;
import java.sql.Date;
import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link Game}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2016/12/03 21:18:45")
public class GameNames {

    /**
     * gameIdのプロパティ名を返します。
     * 
     * @return gameIdのプロパティ名
     */
    public static PropertyName<Integer> gameId() {
        return new PropertyName<Integer>("gameId");
    }

    /**
     * gameDateのプロパティ名を返します。
     * 
     * @return gameDateのプロパティ名
     */
    public static PropertyName<Date> gameDate() {
        return new PropertyName<Date>("gameDate");
    }

    /**
     * gameNumberのプロパティ名を返します。
     * 
     * @return gameNumberのプロパティ名
     */
    public static PropertyName<Integer> gameNumber() {
        return new PropertyName<Integer>("gameNumber");
    }

    /**
     * firstTeamのプロパティ名を返します。
     * 
     * @return firstTeamのプロパティ名
     */
    public static PropertyName<Integer> firstTeam() {
        return new PropertyName<Integer>("firstTeam");
    }

    /**
     * lastTeamのプロパティ名を返します。
     * 
     * @return lastTeamのプロパティ名
     */
    public static PropertyName<Integer> lastTeam() {
        return new PropertyName<Integer>("lastTeam");
    }

    /**
     * firstRunのプロパティ名を返します。
     * 
     * @return firstRunのプロパティ名
     */
    public static PropertyName<Integer> firstRun() {
        return new PropertyName<Integer>("firstRun");
    }

    /**
     * lastRunのプロパティ名を返します。
     * 
     * @return lastRunのプロパティ名
     */
    public static PropertyName<Integer> lastRun() {
        return new PropertyName<Integer>("lastRun");
    }

    /**
     * top1stのプロパティ名を返します。
     * 
     * @return top1stのプロパティ名
     */
    public static PropertyName<Integer> top1st() {
        return new PropertyName<Integer>("top1st");
    }

    /**
     * bottom1stのプロパティ名を返します。
     * 
     * @return bottom1stのプロパティ名
     */
    public static PropertyName<Integer> bottom1st() {
        return new PropertyName<Integer>("bottom1st");
    }

    /**
     * top2ndのプロパティ名を返します。
     * 
     * @return top2ndのプロパティ名
     */
    public static PropertyName<Integer> top2nd() {
        return new PropertyName<Integer>("top2nd");
    }

    /**
     * bottom2ndのプロパティ名を返します。
     * 
     * @return bottom2ndのプロパティ名
     */
    public static PropertyName<Integer> bottom2nd() {
        return new PropertyName<Integer>("bottom2nd");
    }

    /**
     * top3rdのプロパティ名を返します。
     * 
     * @return top3rdのプロパティ名
     */
    public static PropertyName<Integer> top3rd() {
        return new PropertyName<Integer>("top3rd");
    }

    /**
     * bottom3rdのプロパティ名を返します。
     * 
     * @return bottom3rdのプロパティ名
     */
    public static PropertyName<Integer> bottom3rd() {
        return new PropertyName<Integer>("bottom3rd");
    }

    /**
     * top4thのプロパティ名を返します。
     * 
     * @return top4thのプロパティ名
     */
    public static PropertyName<Integer> top4th() {
        return new PropertyName<Integer>("top4th");
    }

    /**
     * bottom4thのプロパティ名を返します。
     * 
     * @return bottom4thのプロパティ名
     */
    public static PropertyName<Integer> bottom4th() {
        return new PropertyName<Integer>("bottom4th");
    }

    /**
     * top5thのプロパティ名を返します。
     * 
     * @return top5thのプロパティ名
     */
    public static PropertyName<Integer> top5th() {
        return new PropertyName<Integer>("top5th");
    }

    /**
     * bottom5thのプロパティ名を返します。
     * 
     * @return bottom5thのプロパティ名
     */
    public static PropertyName<Integer> bottom5th() {
        return new PropertyName<Integer>("bottom5th");
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
     * commentのプロパティ名を返します。
     * 
     * @return commentのプロパティ名
     */
    public static PropertyName<String> comment() {
        return new PropertyName<String>("comment");
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
     * battingSumListのプロパティ名を返します。
     * 
     * @return battingSumListのプロパティ名
     */
    public static _BattingSumNames battingSumList() {
        return new _BattingSumNames("battingSumList");
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
     * leagueのプロパティ名を返します。
     * 
     * @return leagueのプロパティ名
     */
    public static _LeagueNames league() {
        return new _LeagueNames("league");
    }

    /**
     * pitchingListのプロパティ名を返します。
     * 
     * @return pitchingListのプロパティ名
     */
    public static _PitchingNames pitchingList() {
        return new _PitchingNames("pitchingList");
    }

    /**
     * resultListのプロパティ名を返します。
     * 
     * @return resultListのプロパティ名
     */
    public static _ResultNames resultList() {
        return new _ResultNames("resultList");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _GameNames extends PropertyName<Game> {

        /**
         * インスタンスを構築します。
         */
        public _GameNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _GameNames(final String name) {
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
        public _GameNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
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
         * gameDateのプロパティ名を返します。
         *
         * @return gameDateのプロパティ名
         */
        public PropertyName<Date> gameDate() {
            return new PropertyName<Date>(this, "gameDate");
        }

        /**
         * gameNumberのプロパティ名を返します。
         *
         * @return gameNumberのプロパティ名
         */
        public PropertyName<Integer> gameNumber() {
            return new PropertyName<Integer>(this, "gameNumber");
        }

        /**
         * firstTeamのプロパティ名を返します。
         *
         * @return firstTeamのプロパティ名
         */
        public PropertyName<Integer> firstTeam() {
            return new PropertyName<Integer>(this, "firstTeam");
        }

        /**
         * lastTeamのプロパティ名を返します。
         *
         * @return lastTeamのプロパティ名
         */
        public PropertyName<Integer> lastTeam() {
            return new PropertyName<Integer>(this, "lastTeam");
        }

        /**
         * firstRunのプロパティ名を返します。
         *
         * @return firstRunのプロパティ名
         */
        public PropertyName<Integer> firstRun() {
            return new PropertyName<Integer>(this, "firstRun");
        }

        /**
         * lastRunのプロパティ名を返します。
         *
         * @return lastRunのプロパティ名
         */
        public PropertyName<Integer> lastRun() {
            return new PropertyName<Integer>(this, "lastRun");
        }

        /**
         * top1stのプロパティ名を返します。
         *
         * @return top1stのプロパティ名
         */
        public PropertyName<Integer> top1st() {
            return new PropertyName<Integer>(this, "top1st");
        }

        /**
         * bottom1stのプロパティ名を返します。
         *
         * @return bottom1stのプロパティ名
         */
        public PropertyName<Integer> bottom1st() {
            return new PropertyName<Integer>(this, "bottom1st");
        }

        /**
         * top2ndのプロパティ名を返します。
         *
         * @return top2ndのプロパティ名
         */
        public PropertyName<Integer> top2nd() {
            return new PropertyName<Integer>(this, "top2nd");
        }

        /**
         * bottom2ndのプロパティ名を返します。
         *
         * @return bottom2ndのプロパティ名
         */
        public PropertyName<Integer> bottom2nd() {
            return new PropertyName<Integer>(this, "bottom2nd");
        }

        /**
         * top3rdのプロパティ名を返します。
         *
         * @return top3rdのプロパティ名
         */
        public PropertyName<Integer> top3rd() {
            return new PropertyName<Integer>(this, "top3rd");
        }

        /**
         * bottom3rdのプロパティ名を返します。
         *
         * @return bottom3rdのプロパティ名
         */
        public PropertyName<Integer> bottom3rd() {
            return new PropertyName<Integer>(this, "bottom3rd");
        }

        /**
         * top4thのプロパティ名を返します。
         *
         * @return top4thのプロパティ名
         */
        public PropertyName<Integer> top4th() {
            return new PropertyName<Integer>(this, "top4th");
        }

        /**
         * bottom4thのプロパティ名を返します。
         *
         * @return bottom4thのプロパティ名
         */
        public PropertyName<Integer> bottom4th() {
            return new PropertyName<Integer>(this, "bottom4th");
        }

        /**
         * top5thのプロパティ名を返します。
         *
         * @return top5thのプロパティ名
         */
        public PropertyName<Integer> top5th() {
            return new PropertyName<Integer>(this, "top5th");
        }

        /**
         * bottom5thのプロパティ名を返します。
         *
         * @return bottom5thのプロパティ名
         */
        public PropertyName<Integer> bottom5th() {
            return new PropertyName<Integer>(this, "bottom5th");
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
         * commentのプロパティ名を返します。
         *
         * @return commentのプロパティ名
         */
        public PropertyName<String> comment() {
            return new PropertyName<String>(this, "comment");
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
         * battingSumListのプロパティ名を返します。
         * 
         * @return battingSumListのプロパティ名
         */
        public _BattingSumNames battingSumList() {
            return new _BattingSumNames(this, "battingSumList");
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
         * leagueのプロパティ名を返します。
         * 
         * @return leagueのプロパティ名
         */
        public _LeagueNames league() {
            return new _LeagueNames(this, "league");
        }

        /**
         * pitchingListのプロパティ名を返します。
         * 
         * @return pitchingListのプロパティ名
         */
        public _PitchingNames pitchingList() {
            return new _PitchingNames(this, "pitchingList");
        }

        /**
         * resultListのプロパティ名を返します。
         * 
         * @return resultListのプロパティ名
         */
        public _ResultNames resultList() {
            return new _ResultNames(this, "resultList");
        }
    }
}