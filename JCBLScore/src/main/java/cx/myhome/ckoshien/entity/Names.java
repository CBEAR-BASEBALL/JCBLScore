package cx.myhome.ckoshien.entity;

import cx.myhome.ckoshien.entity.BattingDetailNames._BattingDetailNames;
import cx.myhome.ckoshien.entity.BattingSumNames._BattingSumNames;
import cx.myhome.ckoshien.entity.GameNames._GameNames;
import cx.myhome.ckoshien.entity.LeagueNames._LeagueNames;
import cx.myhome.ckoshien.entity.MScheduleNames._MScheduleNames;
import cx.myhome.ckoshien.entity.PitchingNames._PitchingNames;
import cx.myhome.ckoshien.entity.PlayerNames._PlayerNames;
import cx.myhome.ckoshien.entity.ResultNames._ResultNames;
import cx.myhome.ckoshien.entity.TScheduleNames._TScheduleNames;
import cx.myhome.ckoshien.entity.TeamHistoryNames._TeamHistoryNames;
import cx.myhome.ckoshien.entity.TeamNames._TeamNames;
import cx.myhome.ckoshien.entity.WeatherNames._WeatherNames;
import javax.annotation.Generated;

/**
 * 名前クラスの集約です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesAggregateModelFactoryImpl"}, date = "2016/12/03 21:18:45")
public class Names {

    /**
     * {@link BattingDetail}の名前クラスを返します。
     * 
     * @return BattingDetailの名前クラス
     */
    public static _BattingDetailNames battingDetail() {
        return new _BattingDetailNames();
    }

    /**
     * {@link BattingSum}の名前クラスを返します。
     * 
     * @return BattingSumの名前クラス
     */
    public static _BattingSumNames battingSum() {
        return new _BattingSumNames();
    }

    /**
     * {@link Game}の名前クラスを返します。
     * 
     * @return Gameの名前クラス
     */
    public static _GameNames game() {
        return new _GameNames();
    }

    /**
     * {@link League}の名前クラスを返します。
     * 
     * @return Leagueの名前クラス
     */
    public static _LeagueNames league() {
        return new _LeagueNames();
    }

    /**
     * {@link MSchedule}の名前クラスを返します。
     * 
     * @return MScheduleの名前クラス
     */
    public static _MScheduleNames mSchedule() {
        return new _MScheduleNames();
    }

    /**
     * {@link Pitching}の名前クラスを返します。
     * 
     * @return Pitchingの名前クラス
     */
    public static _PitchingNames pitching() {
        return new _PitchingNames();
    }

    /**
     * {@link Player}の名前クラスを返します。
     * 
     * @return Playerの名前クラス
     */
    public static _PlayerNames player() {
        return new _PlayerNames();
    }

    /**
     * {@link Result}の名前クラスを返します。
     * 
     * @return Resultの名前クラス
     */
    public static _ResultNames result() {
        return new _ResultNames();
    }

    /**
     * {@link Team}の名前クラスを返します。
     * 
     * @return Teamの名前クラス
     */
    public static _TeamNames team() {
        return new _TeamNames();
    }

    /**
     * {@link TeamHistory}の名前クラスを返します。
     * 
     * @return TeamHistoryの名前クラス
     */
    public static _TeamHistoryNames teamHistory() {
        return new _TeamHistoryNames();
    }

    /**
     * {@link TSchedule}の名前クラスを返します。
     * 
     * @return TScheduleの名前クラス
     */
    public static _TScheduleNames tSchedule() {
        return new _TScheduleNames();
    }

    /**
     * {@link Weather}の名前クラスを返します。
     * 
     * @return Weatherの名前クラス
     */
    public static _WeatherNames weather() {
        return new _WeatherNames();
    }
}