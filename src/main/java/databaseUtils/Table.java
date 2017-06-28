package databaseUtils;

import java.util.LinkedList;

public class Table {
    private String name;
    private LinkedList<Column> columns;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<Column> getColumns() {
        return columns;
    }

    public void setColumns(LinkedList<Column> columns) {
        this.columns = columns;
    }

    public Table(){

    }

    public Table(String name) {

        this.name = name;
        this.columns = new LinkedList();
    }

    public Table(String name, Column id){
        this.name = name;
        this.columns = new LinkedList();
        this.columns.add(id);
    }
}
