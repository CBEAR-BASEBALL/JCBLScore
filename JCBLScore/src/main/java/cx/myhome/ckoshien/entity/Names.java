package cx.myhome.ckoshien.entity;

import cx.myhome.ckoshien.entity.BattingDetailNames._BattingDetailNames;
import cx.myhome.ckoshien.entity.BattingSumNames._BattingSumNames;
import cx.myhome.ckoshien.entity.GameNames._GameNames;
import cx.myhome.ckoshien.entity.LeagueNames._LeagueNames;
import cx.myhome.ckoshien.entity.PitchingNames._PitchingNames;
import cx.myhome.ckoshien.entity.PlayerNames._PlayerNames;
import cx.myhome.ckoshien.entity.ResultNames._ResultNames;
import cx.myhome.ckoshien.entity.TeamNames._TeamNames;
import javax.annotation.Generated;

/**
 * 名前クラスの集約です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesAggregateModelFactoryImpl"}, date = "2015/07/02 23:44:49")
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
}