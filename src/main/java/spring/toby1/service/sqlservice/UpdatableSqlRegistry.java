package spring.toby1.service.sqlservice;

import spring.toby1.exception.SqlUpdateFailureException;

import java.util.Map;

/**
 * Created by yuuuunmi on 2017. 12. 28..
 */
public interface UpdatableSqlRegistry extends SqlRegistry{

    public void updateSql(String key, String sql) throws SqlUpdateFailureException;
    public void updateSql(Map<String, String> sqlmap) throws SqlUpdateFailureException;
}
