package DatabaseDriversTests;

import Exceptions.NoTableException;
import api.DatabaseDriver;
import domain.Beans;
import domain.Column;
import domain.ConnectionManager;
import domain.Table;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by Cziczarito on 10.06.2017.
 */
public class SQLiteDriverTest {
    ConnectionManager connectionManager;
    DatabaseDriver databaseDriver;
    @Before
    public void before() throws SQLException, ClassNotFoundException {
        ApplicationContext context = new AnnotationConfigApplicationContext(Beans.class);
        connectionManager = context.getBean("connectionManager", ConnectionManager.class);
        Properties connectionProps = new Properties();
        connectionProps.setProperty("dbname","Test.db");
        connectionManager.setConnectionProps(connectionProps);
        databaseDriver = (DatabaseDriver) context.getBean("databaseDriver");
    }

    @Test
    public void beginTest() throws SQLException, ClassNotFoundException {
        assertNotEquals(null, connectionManager);
        assertNotEquals(null, connectionManager.getConnection());
        //custom location - working, uncomment this to check
        /*connectionManager.getConnectionProps().setProperty("dbname","C:/Log/test.db");
        assertNotEquals(null, connectionManager.getConnection()); */
    }

    @Test
    public void putTableToDbTest() throws NoTableException, SQLException, ClassNotFoundException {
        //without id
        Table table = new Table("Tabela1");

        table.getColumns().add(new Column("Kolumna1", "varchar(32)"));
        table.getColumns().add(new Column("Kolumna2", "int"));
        databaseDriver.setTable(table);
        try{
            databaseDriver.createTableInDb();
        }catch (Exception e){
            e.printStackTrace();
        }

        DatabaseMetaData databaseMetaData = connectionManager.getConnection().getMetaData();
        String   catalog          = null;
        String   schemaPattern    = null;
        String   tableNamePattern = null;
        String[] types            = null;

        String tableName = null;

        ResultSet result = databaseMetaData.getTables(
                catalog, schemaPattern, tableNamePattern, types );

        while(result.next()) {
            tableName = result.getString(3);
        }

        assertEquals(table.getName(), tableName);

    }

    @After
    public void afterTests() throws SQLException {
        connectionManager.closeConnection();
        String fileDir = connectionManager.getConnectionProps().getProperty("dbname");
        //dont want to keep test db file on drive
        File file = new File(fileDir);
        file.delete();

    }
}
