package spring.toby1.service.sqlservice;

import spring.toby1.exception.SqlNotFoundException;
import spring.toby1.exception.SqlRetrievalFailureException;

import javax.annotation.PostConstruct;

/**
 * Created by yuuuunmi on 2017. 12. 25..
 */
public class BaseSqlService implements SqlService {
    protected SqlReader sqlReader;

    protected SqlRegistry sqlRegistry;

    public void setSqlReader(SqlReader sqlReader) {
        this.sqlReader = sqlReader;
    }

    public void setSqlRegistry(SqlRegistry sqlRegistry) {
        this.sqlRegistry = sqlRegistry;
    }

    @PostConstruct
    public void loadSql() {
        this.sqlReader.read(this.sqlRegistry);
    }

    public String getSql(String key) throws SqlRetrievalFailureException {
        try {
            return this.sqlRegistry.findSql(key);
        } catch (SqlNotFoundException e) {
            throw new SqlRetrievalFailureException(e.getMessage());
        }
    }
}
