package spring.toby1;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import spring.toby1.dao.UserDao;
import spring.toby1.domain.User;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by yuuuunmi on 2017. 7. 30..
 */
public class UserDaoTest {

    @Test
    public void addAndGet() throws ClassNotFoundException, SQLException {

//        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);

        ApplicationContext context = new GenericXmlApplicationContext("application-context.xml");

        UserDao dao = context.getBean("userDao", UserDao.class);


        User user = new User();
        user.setId("170919");
        user.setName("tndydlf");
        user.setPassword("WednesDays");

        dao.add(user);

        User user2 = dao.get(user.getId());

        assertThat(user2.getName(), is(user.getName()));
        assertThat(user2.getPassword(), is(user.getPassword()));



    }

    public static void main(String[] args){
        JUnitCore.main("spring.toby1.UserDaoTest");
    }
}
