package spring.toby1.test;

import org.junit.Before;
import org.junit.Test;
import spring.toby1.exception.SqlNotFoundException;
import spring.toby1.exception.SqlUpdateFailureException;
import spring.toby1.service.sqlservice.ConcurrentHashMapRegistry;
import spring.toby1.service.sqlservice.UpdatableSqlRegistry;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by yuuuunmi on 2017. 12. 28..
 */
public class ConcurrentHashMapSqlRegistryTest {
    UpdatableSqlRegistry sqlRegistry;

    @Before
    public void setUp(){
        sqlRegistry = new ConcurrentHashMapRegistry();
        sqlRegistry.registerSql("KEY1","SQL1");
        sqlRegistry.registerSql("KEY2","SQL2");
        sqlRegistry.registerSql("KEY3","SQL3");

    }

    @Test
    public void find(){
        checkFindResult("SQL1","SQL2","SQL3");
    }

    private void checkFindResult(String expected1, String expected2, String expected3) {
        assertThat(sqlRegistry.findSql("KEY1"),is(expected1));
        assertThat(sqlRegistry.findSql("KEY2"),is(expected2));
        assertThat(sqlRegistry.findSql("KEY3"),is(expected3));
    }

    @Test(expected = SqlNotFoundException.class)
    public void unknownKey(){
        sqlRegistry.findSql("SQL9999!@#$");
    }

    @Test
    public void updateSingle(){
        sqlRegistry.updateSql("KEY2","Modified2");
        checkFindResult("SQL1","Modified2","SQL3");
    }

    @Test
    public void updateMulti(){
        Map<String, String> sqlmap = new HashMap<String, String>();
        sqlmap.put("KEY1", "Modified1");
        sqlmap.put("KEY3", "Modified3");

        sqlRegistry.updateSql(sqlmap);
        checkFindResult("Modified1", "SQL2","Modified3");

    }

    @Test(expected = SqlUpdateFailureException.class)
    public void updateWithNotExistingKey(){
        sqlRegistry.updateSql("SQL9999!@#$", "Modified2");
    }









}
