package spring.toby1.service.sqlservice;

import spring.toby1.exception.SqlNotFoundException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuuuunmi on 2017. 12. 25..
 */
public class HashMapSqlRegistry implements SqlRegistry {
    private Map<String, String> sqlMap = new HashMap<String, String>();

    public void registerSql(String key, String sql) {
        sqlMap.put(key, sql);
    }

    public String findSql(String key) throws SqlNotFoundException {
        String sql = sqlMap.get(key);
        if(sql == null)
            throw new SqlNotFoundException(key + "를 이용해서 SQL을 찾을 수 없습니다");
        else return sql;
    }


}
