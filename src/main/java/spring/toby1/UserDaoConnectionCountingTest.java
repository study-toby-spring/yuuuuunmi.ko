package spring.toby1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.toby1.dao.CountingConnectionMaker;
import spring.toby1.dao.CountingDaoFactory;
import spring.toby1.dao.DaoFactory;
import spring.toby1.dao.UserDao;
import spring.toby1.domain.User;

import java.sql.SQLException;

/**
 * Created by yuuuunmi on 2017. 7. 30..
 */
public class UserDaoConnectionCountingTest {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        ApplicationContext context = new AnnotationConfigApplicationContext(CountingDaoFactory.class);

        UserDao dao = context.getBean("userDao", UserDao.class);

        CountingConnectionMaker ccm = context.getBean("connectionMaker", CountingConnectionMaker.class);
        System.out.println("Connection counter : " + ccm.getCounter());

    }
}
