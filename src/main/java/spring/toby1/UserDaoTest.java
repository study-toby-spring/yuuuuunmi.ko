package spring.toby1;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.toby1.dao.UserDao;
import spring.toby1.domain.User;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by yuuuunmi on 2017. 7. 30..
 */

public class UserDaoTest {


    private UserDao dao;

    private User user1 ;
    private User user2 ;
    private User user3 ;

    @Before
    public void setUp(){

        dao = new UserDao();

        DataSource dataSource = new SingleConnectionDataSource("jdbc:mysql://localhost/testdb","root","spring", true);
        dao.setDataSource(dataSource);
        
        this.user1 = new User("170919", "윰미고", "20141128");
        this.user2 = new User("170918", "윰미꼬", "20141128");
        this.user3 = new User("170917", "윰미코", "20141128");
    }


    @Test
    public void addAndGet() throws ClassNotFoundException, SQLException {

        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.add(user1);
        dao.add(user2);
        assertThat(dao.getCount(), is(2));

        User userget1 = dao.get(user1.getId());
        assertThat(userget1.getName(), is(user1.getName()));
        assertThat(userget1.getPassword(), is(user1.getPassword()));

        User userget2 = dao.get(user2.getId());
        assertThat(userget2.getName(), is(user2.getName()));
        assertThat(userget2.getPassword(), is(user2.getPassword()));

    }

    @Test
    public void count() throws SQLException, ClassNotFoundException {

        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.add(user1);
        assertThat(dao.getCount(), is(1));

        dao.add(user2);
        assertThat(dao.getCount(), is(2));

        dao.add(user3);
        assertThat(dao.getCount(), is(3));
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void getUserFailure() throws SQLException, ClassNotFoundException {

        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.get("unknown_id");

    }


    public static void main(String[] args){
        JUnitCore.main("spring.toby1.UserDaoTest");
    }
}
