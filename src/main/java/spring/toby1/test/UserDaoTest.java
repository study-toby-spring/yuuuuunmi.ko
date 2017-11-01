package spring.toby1.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.toby1.dao.UserDao;
import spring.toby1.domain.Level;
import spring.toby1.domain.User;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by yuuuunmi on 2017. 7. 30..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/application-context.xml")
public class UserDaoTest {

    @Autowired
    private UserDao dao;

    @Autowired
    private DataSource dataSource;

    private User user1 ;
    private User user2 ;
    private User user3 ;



    @Before
    public void setUp(){
        this.user1 = new User("gyumee", "윰미고", "20141128", Level.BASIC, 1, 0, "gyumee@n.com");
        this.user2 = new User("leegw700", "윰미꼬", "20141128", Level.SILVER, 55, 10, "leegw700@d.com");
        this.user3 = new User("bumjin", "윰미코", "20141128", Level.GOLD, 100, 40, "bumjin@g.com");
    }


    @Test
    public void addAndGet() throws ClassNotFoundException, SQLException {

        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.add(user1);
        dao.add(user2);
        assertThat(dao.getCount(), is(2));

        User userget1 = dao.get(user1.getId());
        checkSameUser(userget1, user1);

        User userget2 = dao.get(user2.getId());
        checkSameUser(userget2, user2);
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

    @Test
    public void getAll() throws ClassNotFoundException, SQLException {
        dao.deleteAll();

        List<User> users0 = dao.getAll();
        assertThat(users0.size(), is(0));

        dao.add(user1);
        List<User> users1 = dao.getAll();
        assertThat(users1.size(), is(1));
        checkSameUser(user1, users1.get(0));

        dao.add(user2);
        List<User> users2 = dao.getAll();
        assertThat(users2.size(), is(2));
        checkSameUser(user1, users2.get(0));
        checkSameUser(user2, users2.get(1));

        dao.add(user3);
        List<User> users3 = dao.getAll();
        assertThat(users3.size(), is(3));
        checkSameUser(user3, users3.get(0));
        checkSameUser(user1, users3.get(1));
        checkSameUser(user2, users3.get(2));

    }

    @Test(expected = DataAccessException.class)
    public void duplicateKey(){
        dao.deleteAll();

        dao.add(user1);
        dao.add(user1);

    }

    @Test
    public void update(){
        dao.deleteAll();

        dao.add(user1);
        dao.add(user2);

        user1.setName("오민규");
        user1.setPassword("springno6");
        user1.setLevel(Level.GOLD);
        user1.setLogin(1000);
        user1.setRecommend(999);
        user1.setEmail("springno6@h.com");
        dao.update(user1);

        User user1update = dao.get(user1.getId());
        checkSameUser(user1, user1update);
        User user2same = dao.get(user2.getId());
        checkSameUser(user2, user2same);

    }

    @Test
    public void getEmail(){
        dao.deleteAll();
        dao.add(user1);
        dao.add(user2);
        dao.add(user3);

        User userGet1 = dao.get(user1.getId());
        assertThat(userGet1.getEmail(), is(user1.getEmail()));

        User userGet2 = dao.get(user2.getId());
        assertThat(userGet2.getEmail(), is(user2.getEmail()));

        User userGet3 = dao.get(user3.getId());
        assertThat(userGet3.getEmail(), is(user3.getEmail()));


    }

    private void checkSameUser(User user1, User user2) {
        assertThat(user1.getId(), is(user2.getId()));
        assertThat(user1.getName(), is(user2.getName()));
        assertThat(user1.getPassword(), is(user2.getPassword()));
        assertThat(user1.getLevel(), is(user2.getLevel()));
        assertThat(user1.getLogin(), is(user2.getLogin()));
        assertThat(user1.getRecommend(), is(user2.getRecommend()));
    }



    public static void main(String[] args){
        JUnitCore.main("spring.toby1.test.UserDaoTest");
    }
}
