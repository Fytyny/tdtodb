import api.DatabaseDriver;
import domain.Beans;
import domain.ConnectionManager;
import domain.DbType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;

/**
 * Created by Cziczarito on 10.06.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Beans.class, loader = AnnotationConfigContextLoader.class)
public class SpringTest {
    @Resource
    Beans beans;
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
        beans.setDbType(DbType.mysql);
        assertEquals(DbType.mysql, beans.dbType());
    }
    @Test
    public void connectionMenagerTest(){
        assertEquals(beans.connectionManager(), connectionManager);
        assertEquals(databaseDriver.getConnectionManager(), connectionManager);
    }

}
