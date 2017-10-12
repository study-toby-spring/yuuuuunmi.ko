package spring.toby1.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

/**
 * Created by yuuuunmi on 2017. 8. 29..
 */
@Configuration // annotation that Application Context or BeanFactory uses this config
public class DaoFactory {

    @Bean // annotation that IoC method creating object, method name = bean name => getBean() method's parameter
    public UserDao userDao(){
        // 생성자를 통한 DI 사용
        // UserDaoJdbc userDaoJdbc = new UserDaoJdbc(connectionMaker());

        // 수정자 메소드를 통한 DI 사용
        UserDaoJdbc userDaoJdbc = new UserDaoJdbc();
        userDaoJdbc.setDataSource(dataSource());


        return userDaoJdbc;
    }


    @Bean
    public DataSource dataSource(){
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

        dataSource.setDriverClass(com.mysql.jdbc.Driver.class);
        dataSource.setUrl("jdbc:mysql://localhost/spring?useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("rha09222");

        return dataSource;
    }
}
