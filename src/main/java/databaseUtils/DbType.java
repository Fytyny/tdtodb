package databaseUtils;

/**
 * Created by Cziczarito on 09.06.2017.
 */
public enum DbType {
    sqlite, mysql;

    public static DbType getDefaultType(){
        return DbType.sqlite;
    }
}
