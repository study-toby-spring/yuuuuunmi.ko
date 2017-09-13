package spring.toby1;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import spring.toby1.dao.DaoFactory;
import spring.toby1.dao.UserDao;
import spring.toby1.domain.User;

import java.sql.SQLException;

/**
 * Created by yuuuunmi on 2017. 7. 30..
 */
public class UserDaoTest {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);

//        ApplicationContext context = new GenericXmlApplicationContext("application-context.xml");

        UserDao dao = context.getBean("userDao", UserDao.class);


        User user = new User();
        user.setId("1709131");
        user.setName("수요일s");
        user.setPassword("wednesdays");

        dao.add(user);

        System.out.println(user.getId() + " 등록 성공");

        User user2 = dao.get(user.getId());

        System.out.println(user2.getName());
        System.out.println(user2.getPassword());

        System.out.println(user.getId() + " 조회 성공");
    }
}
