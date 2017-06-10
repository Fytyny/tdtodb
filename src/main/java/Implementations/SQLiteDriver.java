package Implementations;

import Exceptions.NoTableException;
import api.DatabaseDriver;
import api.PreparedStatementsSetStrategy;
import api.Query;
import domain.Column;
import domain.ConnectionManager;
import domain.Table;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

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

    private boolean createTable() throws SQLException, ClassNotFoundException {
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
    private boolean insertIntoTable(Map<String, String>[] values) throws SQLException, ClassNotFoundException {
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
        return query.executeBatch(table, values, new PreparedStatementsSetStrategy(){
            @Override
            public void addToBatch(PreparedStatement preparedStatement, Map<String, String>[] values) throws SQLException {
                for (int j = 0; j<values.length; j++){
                    int columnIndex = 0;
                    for (Column i : table.getColumns()){
                        if (!i.isGenerated()){
                            columnIndex++;
                            if (i.getType().contains("int")) preparedStatement.setInt(columnIndex, Integer.valueOf(values[j].get(i.getName())));
                            else if (i.getType().contains("char")) preparedStatement.setString(columnIndex, values[j].get(i.getName()));
                        }
                    }
                    preparedStatement.addBatch();
                }

            }
        });
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
