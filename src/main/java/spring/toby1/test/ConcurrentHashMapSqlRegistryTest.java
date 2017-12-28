package spring.toby1.test;

import spring.toby1.service.sqlservice.ConcurrentHashMapRegistry;
import spring.toby1.service.sqlservice.UpdatableSqlRegistry;

/**
 * Created by yuuuunmi on 2017. 12. 28..
 */
public class ConcurrentHashMapSqlRegistryTest extends AbstractUpdatableSqlRegistryTest {
    protected UpdatableSqlRegistry createUpdatableSqlRegistry() {
        return new ConcurrentHashMapRegistry();
    }
}
