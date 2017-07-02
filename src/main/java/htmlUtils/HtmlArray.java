package htmlUtils;

import databaseUtils.Table;

import java.util.List;
import java.util.Map;

public class HtmlArray {
    private int numberOfColumns;
    private String[] columnsNames;
    private List<Map<String,String>> values;

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public void setNumberOfColumns(int numberOfColumns) {
        this.numberOfColumns = numberOfColumns;
    }

    public String[] getColumnsNames() {
        return columnsNames;
    }

    public void setColumnsNames(String[] columnsNames) {
        this.columnsNames = columnsNames;
    }

    public List<Map<String, String>> getValues() {
        return values;
    }

    public void setValues(List<Map<String, String>> values) {
        this.values = values;
    }

    public Table toTable(){
        return  null;
    }
    public String[] generateColumnsTypes(){
        String[] columnTypes = new String[numberOfColumns];

        for (Integer i=0; i<numberOfColumns;i++){
            Integer length = 0;
            String valuesSum = "";
            //columnTypes[i] = "varchar(" + length + ")";
            for (Map<String,String> map : values){
                String value = map.get(i.toString());
                if (value.length()>length){
                    length = value.length();
                }
                valuesSum += value;
            }
            if (valuesSum.matches("[0-9]+\\.")){
                columnTypes[i] = "double";
            }else if(valuesSum.matches("[0-9]+")){
                columnTypes[i] = "int";
            }else{
                columnTypes[i] = "varchar";
            }

            columnTypes[i] += "(" + length.toString() + ")";
        }
        return columnTypes;
    }
}
