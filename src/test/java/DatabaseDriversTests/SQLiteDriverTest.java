package DatabaseDriversTests;

import domain.Beans;
import domain.ConnectionManager;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.Properties;

import static org.junit.Assert.assertNotEquals;

/**
 * Created by Cziczarito on 10.06.2017.
 */
public class SQLiteDriverTest {
    ConnectionManager connectionManager;
    @Before
    public void before() throws SQLException, ClassNotFoundException {
        ApplicationContext context = new AnnotationConfigApplicationContext(Beans.class);
        connectionManager = context.getBean("connectionManager", ConnectionManager.class);
        Properties connectionProps = new Properties();
        connectionProps.setProperty("dbname","Test.db");
        connectionManager.setConnectionProps(connectionProps);
    }

    @Test
    public void beginTest() throws SQLException, ClassNotFoundException {
        assertNotEquals(null, connectionManager);
        assertNotEquals(null, connectionManager.getConnection());
    }
}
