package spring.toby1.service.sqlservice;

/**
 * Created by yuuuunmi on 2017. 12. 25..
 */
public class DefaultSqlService extends BaseSqlService {
    public DefaultSqlService() {
        setSqlReader(new JaxbXmlSqlReader());
        setSqlRegistry(new HashMapSqlRegistry());
    }
}
