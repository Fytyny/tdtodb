package databaseUtils;

import implementations.SQLiteDriver;
import api.DatabaseDriver;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Created by Cziczarito on 09.06.2017.
 */
@Configuration
public class Beans {

    private DbType dbType;
    public void setDbType(DbType dbType) {
        this.dbType = dbType;
        connectionManager().setDbType(dbType);
    }

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public DbType dbType(){
        if (dbType == null) dbType = DbType.getDefaultType();
        return dbType;
    }

    @Bean
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    public ConnectionManager connectionManager(){
        return new ConnectionManager(dbType());
    }

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public DatabaseDriver databaseDriver(){
        if (dbType() == DbType.sqlite) return new SQLiteDriver();
        else return null;
    }


}
