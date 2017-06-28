package DatabaseDriversTests;

import api.DatabaseDriver;
import api.Query;
import databaseUtils.Column;
import databaseUtils.ConnectionManager;
import databaseUtils.DatabaseBeans;
import databaseUtils.Table;
import exceptions.NoTableException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;

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
        ApplicationContext context = new AnnotationConfigApplicationContext(DatabaseBeans.class);
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
        //custom location - working as long as you are not in c root or c/windows, uncomment this to check
        /*connectionManager.getConnectionProps().setProperty("dbname","C:/Log/test.db");
        assertNotEquals(null, connectionManager.getConnection()); */
    }

    @Test
    public void putTableToDbTest() throws NoTableException, SQLException, ClassNotFoundException {
        //without id
        Table table = new Table("Tabela1");
        testTable(table);
        //with id
        Table tableWithId = new Table("Tabela2", new Column("id", "integer", true,true));
        testTable(tableWithId);
    }
    private void testTable(Table table) throws SQLException, ClassNotFoundException {
        table.getColumns().add(new Column("Kolumna1", "varchar(32)"));
        table.getColumns().add(new Column("Kolumna2", "int"));
        databaseDriver.setTable(table);
        try{
            databaseDriver.createTableInDb();
        }catch (Exception e){
            e.printStackTrace();
        }

        isTableInDB(table.getName());
        String columnNamePattern = "Kolumna1";
        int expectedType = Types.VARCHAR;
        isColumnInTable(table.getName(), columnNamePattern, expectedType);
        columnNamePattern = "Kolumna2";
        expectedType = Types.INTEGER;
        isColumnInTable(table.getName(), columnNamePattern, expectedType);
    }

    private boolean isTableInDB(String tableNamePattern) throws SQLException, ClassNotFoundException {
        DatabaseMetaData databaseMetaData = connectionManager.getConnection().getMetaData();
        String   catalog          = null;
        String   schemaPattern    = null;
        String[] types            = null;
        ResultSet result = databaseMetaData.getTables(catalog, schemaPattern, tableNamePattern, types );
        String tableName = null;
        while(result.next()) {
            tableName = result.getString(3);
        }
        return tableNamePattern == tableName;
    }

    public void isColumnInTable( String   tableNamePattern, String columnNamePattern, int expectedType) throws SQLException, ClassNotFoundException {
        DatabaseMetaData databaseMetaData = connectionManager.getConnection().getMetaData();
        String   catalog          = null;
        String   schemaPattern    = null;
        ResultSet result = databaseMetaData.getColumns(catalog, schemaPattern, tableNamePattern, columnNamePattern);

        String columnName = null;
        int columnType = 0;
        while(result.next()){
            columnName = result.getString(4);
            columnType = result.getInt(5);
        }

        assertEquals(columnNamePattern, columnName);
        assertEquals(expectedType, columnType);
    }

    @Test
    public void insertValuesIntoTableTest() throws NoTableException, SQLException, ClassNotFoundException {
        Table table = new Table("Tabela1");
        table.getColumns().add(new Column("Kolumna1", "varchar(32)"));
        table.getColumns().add(new Column("Kolumna2", "int"));
        databaseDriver.setTable(table);
        try{
            databaseDriver.createTableInDb();
        }catch (Exception e){
            e.printStackTrace();
        }
        Collection<Map<String,String>> values = new LinkedList<>();
        String test = "t";
        for (Integer i =0; i<20; i++){
            HashMap<String, String> nap = new HashMap<>();
            nap.put("Kolumna1",test);
            nap.put("Kolumna2",i.toString());
            values.add(nap);
            test += "t";
        }
        try{
            databaseDriver.insertIntoTableInDb(values);
            String query = "select * from " + table.getName() + " order by Kolumna2 asc";
            Query query1 = () ->{
                return query;
            };
            ResultSet resultSet = query1.getAll(connectionManager);
            int j = 0;
            String test2 = "t";
            while (resultSet.next()){
                int i = resultSet.getInt("Kolumna2");
                String string = resultSet.getString("Kolumna1");
                assertEquals(j,i);
                assertEquals(test2, string);
                test2 += "t";
                j++;
            }

        }catch (Exception e){
            e.printStackTrace();
        }

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
