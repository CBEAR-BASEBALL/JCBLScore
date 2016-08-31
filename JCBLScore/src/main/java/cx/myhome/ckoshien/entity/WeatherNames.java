package cx.myhome.ckoshien.entity;

import java.sql.Date;
import java.sql.Timestamp;
import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link Weather}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2016/08/31 22:47:54")
public class WeatherNames {

    /**
     * dateのプロパティ名を返します。
     * 
     * @return dateのプロパティ名
     */
    public static PropertyName<Date> date() {
        return new PropertyName<Date>("date");
    }

    /**
     * imgのプロパティ名を返します。
     * 
     * @return imgのプロパティ名
     */
    public static PropertyName<String> img() {
        return new PropertyName<String>("img");
    }

    /**
     * weatherのプロパティ名を返します。
     * 
     * @return weatherのプロパティ名
     */
    public static PropertyName<String> weather() {
        return new PropertyName<String>("weather");
    }

    /**
     * hightempのプロパティ名を返します。
     * 
     * @return hightempのプロパティ名
     */
    public static PropertyName<String> hightemp() {
        return new PropertyName<String>("hightemp");
    }

    /**
     * lowtempのプロパティ名を返します。
     * 
     * @return lowtempのプロパティ名
     */
    public static PropertyName<String> lowtemp() {
        return new PropertyName<String>("lowtemp");
    }

    /**
     * regtimeのプロパティ名を返します。
     * 
     * @return regtimeのプロパティ名
     */
    public static PropertyName<Timestamp> regtime() {
        return new PropertyName<Timestamp>("regtime");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _WeatherNames extends PropertyName<Weather> {

        /**
         * インスタンスを構築します。
         */
        public _WeatherNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _WeatherNames(final String name) {
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
        public _WeatherNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * dateのプロパティ名を返します。
         *
         * @return dateのプロパティ名
         */
        public PropertyName<Date> date() {
            return new PropertyName<Date>(this, "date");
        }

        /**
         * imgのプロパティ名を返します。
         *
         * @return imgのプロパティ名
         */
        public PropertyName<String> img() {
            return new PropertyName<String>(this, "img");
        }

        /**
         * weatherのプロパティ名を返します。
         *
         * @return weatherのプロパティ名
         */
        public PropertyName<String> weather() {
            return new PropertyName<String>(this, "weather");
        }

        /**
         * hightempのプロパティ名を返します。
         *
         * @return hightempのプロパティ名
         */
        public PropertyName<String> hightemp() {
            return new PropertyName<String>(this, "hightemp");
        }

        /**
         * lowtempのプロパティ名を返します。
         *
         * @return lowtempのプロパティ名
         */
        public PropertyName<String> lowtemp() {
            return new PropertyName<String>(this, "lowtemp");
        }

        /**
         * regtimeのプロパティ名を返します。
         *
         * @return regtimeのプロパティ名
         */
        public PropertyName<Timestamp> regtime() {
            return new PropertyName<Timestamp>(this, "regtime");
        }
    }
}