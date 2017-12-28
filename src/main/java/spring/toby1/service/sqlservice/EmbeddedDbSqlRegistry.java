package spring.toby1.service.sqlservice;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import spring.toby1.exception.SqlNotFoundException;
import spring.toby1.exception.SqlUpdateFailureException;

import javax.sql.DataSource;
import java.util.Map;

/**
 * Created by yuuuunmi on 2017. 12. 28..
 */
public class EmbeddedDbSqlRegistry implements UpdatableSqlRegistry {
    JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void registerSql(String key, String sql) {
        jdbcTemplate.update("insert into sqlmap(key_, sql_) VALUES(?,?)", key, sql);

    }

    public String findSql(String key) throws SqlNotFoundException {
        try {
            return jdbcTemplate.queryForObject("select sql_ from sqlmap where key_ = ?", String.class, key);
        } catch (EmptyResultDataAccessException e) {
            throw new SqlNotFoundException(key + "에 해ㅔ당하는 SQL을 찾을 수 없습니다. ", e);
        }
    }

    public void updateSql(String key, String sql) throws SqlUpdateFailureException {
        int affected = jdbcTemplate.update("update sqlmap set sql_ = ? where key_= ?", sql, key);
        if(affected == 0){
            throw new SqlUpdateFailureException(key + "에 해당하는 SQL을 찾을 수 없습니다.");
        }
    }

    public void updateSql(Map<String, String> sqlmap) throws SqlUpdateFailureException {
        for(Map.Entry<String, String> entry : sqlmap.entrySet()){
            updateSql(entry.getKey(), entry.getValue());
        }
    }
}
