package api;

import Exceptions.NoTableException;
import domain.ConnectionManager;
import domain.Table;

import java.sql.SQLException;

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
}
