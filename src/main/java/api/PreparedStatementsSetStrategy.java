package api;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

/**
 * Created by Cziczarito on 10.06.2017.
 */
public interface PreparedStatementsSetStrategy {
    public void addToBatch(PreparedStatement preparedStatement, Collection<Map<String,String>> values) throws SQLException;
}
