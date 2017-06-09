package Implementations;

import Exceptions.NoTableException;
import api.DatabaseDriver;
import api.Query;
import domain.Column;
import domain.ConnectionManager;
import domain.Table;

import javax.annotation.Resource;
import java.sql.Connection;

/**
 * Created by Cziczarito on 08.06.2017.
 */
public class SQLiteDriver implements DatabaseDriver {
    private Table table;
    private Query query;
    private Connection connection;
    private ConnectionManager connectionManager;

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public ConnectionManager getConnectionManager() {
        return connectionManager;
    }

    @Resource
    public void setConnectionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Table getTable() {
        return table;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private boolean createTable(){
        query = () ->{
            String result = new String();
            result += "create table if not exist " + table.getName() + "(";
            for (Column i : table.getColumns()){
                result += i.getName();
                result += i.getType();
                if (i.isPrimaryKey()) result+= "primary key ";
                if (i.isGenerated()) result += "auto_increment ";
                if (i != table.getColumns().getLast()) result += ",";
                else result += ")";
            }

            return result;
        };
        return query.execute();
    }
    private boolean insertIntoTable(String[][] data){
        query = () ->{
            String result = new String();
            String pom = new String("(");
            result += "insert into table " + table.getName() + "(";
            for (Column i : table.getColumns()){
                if(!i.isGenerated()){
                    result+=i.getName();
                    pom+= "?";
                }
                if (i != table.getColumns().getLast()){
                    result += ",";
                    pom += ",";
                }
                else{
                    result+=") values ";
                    pom+=")";
                }
            }
            result+=pom;
            return result;
        };
        return query.executeBatch(table);
    }

    @Override
    public void createTableInDb() throws NoTableException {
        if (table == null) throw new NoTableException();
    }

    @Override
    public void deleteTableFromDb() throws NoTableException{
        if (table == null) throw new NoTableException();

    }

}
