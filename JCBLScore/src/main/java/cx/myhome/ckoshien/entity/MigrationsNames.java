package cx.myhome.ckoshien.entity;

import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link Migrations}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2018/04/08 16:30:11")
public class MigrationsNames {

    /**
     * idのプロパティ名を返します。
     * 
     * @return idのプロパティ名
     */
    public static PropertyName<Integer> id() {
        return new PropertyName<Integer>("id");
    }

    /**
     * migrationのプロパティ名を返します。
     * 
     * @return migrationのプロパティ名
     */
    public static PropertyName<String> migration() {
        return new PropertyName<String>("migration");
    }

    /**
     * batchのプロパティ名を返します。
     * 
     * @return batchのプロパティ名
     */
    public static PropertyName<Integer> batch() {
        return new PropertyName<Integer>("batch");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _MigrationsNames extends PropertyName<Migrations> {

        /**
         * インスタンスを構築します。
         */
        public _MigrationsNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _MigrationsNames(final String name) {
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
        public _MigrationsNames(final PropertyName<?> parent, final String name) {
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
         * migrationのプロパティ名を返します。
         *
         * @return migrationのプロパティ名
         */
        public PropertyName<String> migration() {
            return new PropertyName<String>(this, "migration");
        }

        /**
         * batchのプロパティ名を返します。
         *
         * @return batchのプロパティ名
         */
        public PropertyName<Integer> batch() {
            return new PropertyName<Integer>(this, "batch");
        }
    }
}