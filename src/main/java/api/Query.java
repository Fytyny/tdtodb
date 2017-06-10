package api;

import domain.Beans;
import domain.ConnectionManager;
import domain.Table;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

/**
 * Created by Cziczarito on 08.06.2017.
 */
public interface Query {
    public String getQuery();
    public default boolean execute() throws SQLException, ClassNotFoundException {
        String query = getQuery();
        ApplicationContext context = new AnnotationConfigApplicationContext(Beans.class);
        ConnectionManager connectionManeger = context.getBean("connectionManager", ConnectionManager.class);
        Connection connection = connectionManeger.getConnection();
        Statement statement = connection.createStatement();
        boolean result = statement.execute(query);
        statement.close();
        connection.close();
        return result;
    }
    // mpzna podac prepared statemnt w getQuery
    public default boolean executeBatch(Table table, Map<String,String>[] values, PreparedStatementsSetStrategy preparedStatementsSetStrategy) throws SQLException, ClassNotFoundException {
        String query = getQuery();
        ApplicationContext context = new AnnotationConfigApplicationContext(Beans.class);
        ConnectionManager connectionManeger = context.getBean("connectionManager", ConnectionManager.class);
        Connection connection = connectionManeger.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatementsSetStrategy.addToBatch(preparedStatement, values);

        preparedStatement.executeBatch();
        preparedStatement.close();
        connection.close();
        return true;
    }
}
