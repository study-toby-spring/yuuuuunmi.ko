package spring.toby1.service.sqlservice;

import org.springframework.core.io.Resource;
import org.springframework.oxm.Unmarshaller;
import spring.toby1.exception.SqlRetrievalFailureException;
import spring.toby1.service.jaxb.SqlType;
import spring.toby1.service.jaxb.Sqlmap;

import javax.annotation.PostConstruct;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;

/**
 * Created by yuuuunmi on 2017. 12. 27..
 */
public class OxmSqlService implements SqlService {
    private final BaseSqlService baseSqlService = new BaseSqlService();


    private final OxmSqlReader oxmSqlReader = new OxmSqlReader();
    private SqlRegistry sqlRegistry = new HashMapSqlRegistry();

    public void setSqlRegistry(SqlRegistry sqlRegistry) {
        this.sqlRegistry = sqlRegistry;
    }

    public void setUnmarshaller(Unmarshaller unmarshaller) {
        this.oxmSqlReader.setUnmarshaller(unmarshaller);
    }

    public void setSqlmap(Resource sqlmap) {
        this.oxmSqlReader.setSqlmap(sqlmap);
    }

    @PostConstruct
    public void loadSql() {
        this.baseSqlService.setSqlReader(this.oxmSqlReader);
        this.baseSqlService.setSqlRegistry(this.sqlRegistry);

        this.baseSqlService.loadSql();
    }

    public String getSql(String key) throws SqlRetrievalFailureException {
        return this.baseSqlService.getSql(key);
    }

    private class OxmSqlReader implements SqlReader {

        private Unmarshaller unmarshaller;

        private Resource sqlmap; //= new ClassPathResource("/sql/sqlmap.xml");

        public void setUnmarshaller(Unmarshaller unmarshaller) {
            this.unmarshaller = unmarshaller;
        }

        public void setSqlmap(Resource sqlmap) {

            this.sqlmap = sqlmap;
        }

        public void read(SqlRegistry sqlRegistry) {

            try {
                Source source = new StreamSource(sqlmap.getInputStream());
                Sqlmap sqlmap = (Sqlmap) this.unmarshaller.unmarshal(source);
                for (SqlType sql : sqlmap.getSql()) {
                    sqlRegistry.registerSql(sql.getKey(), sql.getValue());
                }
            } catch (IOException e) {
                throw new IllegalStateException(this.sqlmap.getFilename() + "을 가져올 수 없습니다", e);
            }

        }
    }


}
