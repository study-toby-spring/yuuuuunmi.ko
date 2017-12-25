package spring.toby1.service.sqlservice;

import spring.toby1.exception.SqlRetrievalFailureException;

/**
 * Created by yuuuunmi on 2017. 12. 15..
 */
public interface SqlService {
    String getSql(String key) throws SqlRetrievalFailureException;
}
