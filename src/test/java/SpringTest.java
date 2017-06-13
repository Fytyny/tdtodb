import api.DatabaseDriver;
import databaseUtils.DatabaseBeans;
import databaseUtils.ConnectionManager;
import databaseUtils.DbType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.Assert.assertEquals;

/**
 * Created by Cziczarito on 10.06.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DatabaseBeans.class, loader = AnnotationConfigContextLoader.class)
public class SpringTest {
    @Autowired
    DatabaseBeans databaseBeans;
    @Autowired
    private DbType dbType;
    @Autowired
    private ConnectionManager connectionManager;
    @Autowired
    private DatabaseDriver databaseDriver;
    @Test
    public void dbTypeTest(){
        assertEquals(DbType.sqlite,dbType);
        assertEquals(connectionManager.getDbType(),dbType);
        databaseBeans.setDbType(DbType.mysql);
        assertEquals(DbType.mysql, databaseBeans.dbType());
    }
    @Test
    public void connectionMenagerTest(){
        assertEquals(databaseBeans.connectionManager(), connectionManager);
        assertEquals(databaseDriver.getConnectionManager(), connectionManager);
    }

}
