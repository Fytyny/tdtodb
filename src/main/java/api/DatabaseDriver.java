package api;

import Exceptions.NoTableException;
import domain.ConnectionManager;
import domain.Table;

/**
 * Created by Cziczarito on 08.06.2017.
 */
public interface DatabaseDriver {
    public void setConnectionManager(ConnectionManager connectionManager);
    public ConnectionManager getConnectionManager();
    public void setTable(Table table);
    public Table getTable();
    public void createTableInDb() throws NoTableException;
    public void deleteTableFromDb() throws NoTableException;
}
