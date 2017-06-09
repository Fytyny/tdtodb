package domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Cziczarito on 09.06.2017.
 */
public class ConnectionManager {
    private Connection connection;
    private DbType dbType;
    private Properties connectionProps;
    ConnectionManager(DbType dbType){
        this.dbType = dbType;
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        String user = connectionProps.getProperty("user");
        String password = connectionProps.getProperty("password");
        Integer portNumber = Integer.valueOf(connectionProps.getProperty("portNumber"));
        String dbName = connectionProps.getProperty("name");
        if (dbType == DbType.sqlite){
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection("jdbc:" + dbType.toString() + ":" + dbName );
        }else{
            this.connection = DriverManager.getConnection("jdbc:" + dbType.toString() + ":" + dbName, connectionProps);
        }
        return this.connection;
    }

    public Properties getConnectionProps() {
        return connectionProps;
    }

    public void setConnectionProps(Properties connectionProps) {
        this.connectionProps = connectionProps;
    }

    public DbType getDbType() {
        return dbType;
    }

    public void setDbType(DbType dbType) {
        this.dbType = dbType;
    }

    public void closeConnection() throws SQLException {
        connection.close();
        connectionProps = null;
    }
}
