package api;

import databaseUtils.ConnectionManager;
import databaseUtils.Table;
import exceptions.NoTableException;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

/**
 * Created by Cziczarito on 08.06.2017.
 */
public interface DatabaseDriver {
    public void setConnectionManager(ConnectionManager connectionManager);
    public ConnectionManager getConnectionManager();
    public void setTable(Table table);
    public Table getTable();
    public void createTableInDb() throws NoTableException, SQLException, ClassNotFoundException;
    public void deleteTableFromDb() throws NoTableException;
    public boolean insertIntoTableInDb(Collection<Map<String,String>> values) throws SQLException;
}
