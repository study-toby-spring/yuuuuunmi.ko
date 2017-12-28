package spring.toby1.test;

import org.junit.After;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import spring.toby1.service.sqlservice.EmbeddedDbSqlRegistry;
import spring.toby1.service.sqlservice.UpdatableSqlRegistry;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.HSQL;

/**
 * Created by yuuuunmi on 2017. 12. 28..
 */
public class EmbeddedDbSqlRegistryTest extends AbstractUpdatableSqlRegistryTest {
    EmbeddedDatabase db;

    protected UpdatableSqlRegistry createUpdatableSqlRegistry() {
        db = new EmbeddedDatabaseBuilder()
                .setType(HSQL).addScript("classpath:embeddedDB/schema.sql")
                .build();

        EmbeddedDbSqlRegistry embeddedDbSqlRegistry = new EmbeddedDbSqlRegistry();
        embeddedDbSqlRegistry.setDataSource(db);
        return embeddedDbSqlRegistry;
    }

    @After
    public void tearDown(){
        db.shutdown();
    }
}
