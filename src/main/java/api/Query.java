package api;

import databaseUtils.ConnectionManager;
import databaseUtils.Table;

import java.sql.*;
import java.util.Collection;
import java.util.Map;

/**
 * Created by Cziczarito on 08.06.2017.
 */
public interface Query {
    public String getQuery();
    public default boolean execute(ConnectionManager connectionManager) throws SQLException, ClassNotFoundException {
        String query = getQuery();
        Connection connection = connectionManager.getConnection();
        Statement statement = connection.createStatement();
        boolean result = statement.execute(query);
        statement.close();
        connection.close();
        return result;
    }
    // mpzna podac prepared statemnt w getQuery
    public default boolean executeBatch(Table table, Collection<Map<String,String>> values, PreparedStatementsSetStrategy preparedStatementsSetStrategy, ConnectionManager connectionManager) throws SQLException, ClassNotFoundException {
        String query = getQuery();
        Connection connection = connectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatementsSetStrategy.addToBatch(preparedStatement, values);
        preparedStatement.executeBatch();
        preparedStatement.close();
        connection.close();
        return true;
    }
    public default ResultSet getAll(ConnectionManager connectionManager) throws SQLException, ClassNotFoundException {
        String query = getQuery();
        Connection connection = connectionManager.getConnection();
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }
}
