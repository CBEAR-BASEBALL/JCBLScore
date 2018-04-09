package cx.myhome.ckoshien.entity;

import cx.myhome.ckoshien.entity.BattingDetailNames._BattingDetailNames;
import cx.myhome.ckoshien.entity.BattingSumNames._BattingSumNames;
import cx.myhome.ckoshien.entity.PitchingNames._PitchingNames;
import cx.myhome.ckoshien.entity.TScheduleNames._TScheduleNames;
import cx.myhome.ckoshien.entity.TeamNames._TeamNames;
import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link Player}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2018/04/08 16:30:11")
public class PlayerNames {

    /**
     * idのプロパティ名を返します。
     * 
     * @return idのプロパティ名
     */
    public static PropertyName<Integer> id() {
        return new PropertyName<Integer>("id");
    }

    /**
     * nameのプロパティ名を返します。
     * 
     * @return nameのプロパティ名
     */
    public static PropertyName<String> name() {
        return new PropertyName<String>("name");
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
     * loginIdのプロパティ名を返します。
     * 
     * @return loginIdのプロパティ名
     */
    public static PropertyName<String> loginId() {
        return new PropertyName<String>("loginId");
    }

    /**
     * passwordのプロパティ名を返します。
     * 
     * @return passwordのプロパティ名
     */
    public static PropertyName<String> password() {
        return new PropertyName<String>("password");
    }

    /**
     * authorityのプロパティ名を返します。
     * 
     * @return authorityのプロパティ名
     */
    public static PropertyName<Integer> authority() {
        return new PropertyName<Integer>("authority");
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
     * scPasswordのプロパティ名を返します。
     * 
     * @return scPasswordのプロパティ名
     */
    public static PropertyName<String> scPassword() {
        return new PropertyName<String>("scPassword");
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
     * pitchingListのプロパティ名を返します。
     * 
     * @return pitchingListのプロパティ名
     */
    public static _PitchingNames pitchingList() {
        return new _PitchingNames("pitchingList");
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
     * TScheduleListのプロパティ名を返します。
     * 
     * @return TScheduleListのプロパティ名
     */
    public static _TScheduleNames TScheduleList() {
        return new _TScheduleNames("TScheduleList");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _PlayerNames extends PropertyName<Player> {

        /**
         * インスタンスを構築します。
         */
        public _PlayerNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _PlayerNames(final String name) {
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
        public _PlayerNames(final PropertyName<?> parent, final String name) {
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
         * nameのプロパティ名を返します。
         *
         * @return nameのプロパティ名
         */
        public PropertyName<String> name() {
            return new PropertyName<String>(this, "name");
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
         * loginIdのプロパティ名を返します。
         *
         * @return loginIdのプロパティ名
         */
        public PropertyName<String> loginId() {
            return new PropertyName<String>(this, "loginId");
        }

        /**
         * passwordのプロパティ名を返します。
         *
         * @return passwordのプロパティ名
         */
        public PropertyName<String> password() {
            return new PropertyName<String>(this, "password");
        }

        /**
         * authorityのプロパティ名を返します。
         *
         * @return authorityのプロパティ名
         */
        public PropertyName<Integer> authority() {
            return new PropertyName<Integer>(this, "authority");
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
         * scPasswordのプロパティ名を返します。
         *
         * @return scPasswordのプロパティ名
         */
        public PropertyName<String> scPassword() {
            return new PropertyName<String>(this, "scPassword");
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
         * pitchingListのプロパティ名を返します。
         * 
         * @return pitchingListのプロパティ名
         */
        public _PitchingNames pitchingList() {
            return new _PitchingNames(this, "pitchingList");
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
         * TScheduleListのプロパティ名を返します。
         * 
         * @return TScheduleListのプロパティ名
         */
        public _TScheduleNames TScheduleList() {
            return new _TScheduleNames(this, "TScheduleList");
        }
    }
}