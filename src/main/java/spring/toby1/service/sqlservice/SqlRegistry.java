package spring.toby1.service.sqlservice;

import spring.toby1.exception.SqlNotFoundException;

/**
 * Created by yuuuunmi on 2017. 12. 25..
 */
public interface SqlRegistry {
    void registerSql(String key, String sql);

    String findSql(String key) throws SqlNotFoundException;

}
