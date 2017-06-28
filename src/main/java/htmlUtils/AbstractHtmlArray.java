package htmlUtils;

import databaseUtils.Column;
import databaseUtils.Table;

import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Cziczarito on 11.06.2017.
 */
public abstract class AbstractHtmlArray {
    private int columnsNumber;
    private String[] columnsNames;
    private Map<String, String>[] values;
    public abstract Table toTable();
    public abstract LinkedList<Column> generateColumnsTypes();

    public int getColumnsNumber() {
        return columnsNumber;
    }

    public void setColumnsNumber(int columnsNumber) {
        this.columnsNumber = columnsNumber;
    }

    public String[] getColumnsNames() {
        return columnsNames;
    }

    public void setColumnsNames(String[] columnsNames) {
        this.columnsNames = columnsNames;
    }

    public Map<String, String>[] getValues() {
        return values;
    }

    public void setValues(Map<String, String>[] values) {
        this.values = values;
    }
}
