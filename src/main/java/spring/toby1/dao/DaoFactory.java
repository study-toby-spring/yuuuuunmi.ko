package spring.toby1.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yuuuunmi on 2017. 8. 29..
 */
@Configuration // annotation that Application Context or BeanFactory uses this config
public class DaoFactory {

    @Bean // annotation that IoC method creating object, method name = bean name => getBean() method's parameter
    public UserDao userDao(){
        // 생성자를 통한 DI 사용
        // UserDao userDao = new UserDao(connectionMaker());

        // 수정자 메소드를 통한 DI 사용
        UserDao userDao = new UserDao();
        userDao.setConnectionMaker(connectionMaker());


        return userDao;
    }


    @Bean
    public ConnectionMaker connectionMaker(){
        return new DConnectionMaker();
    }
}
