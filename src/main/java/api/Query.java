package api;

import domain.Table;

/**
 * Created by Cziczarito on 08.06.2017.
 */
public interface Query {
    public String getQuery();
    public default boolean execute(){
        String query = getQuery();
        return true;
    }
    // mpzna podac prepared statemnt w getQuery
    public default boolean executeBatch(Table table){
        String query = getQuery();
        return true;
    }
}
