package spring.toby1.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import spring.toby1.dao.UserDao;
import spring.toby1.domain.Level;
import spring.toby1.domain.User;
import spring.toby1.exception.TestUserServiceException;
import spring.toby1.service.MockMailSender;
import spring.toby1.service.UserServiceImpl;
import spring.toby1.service.UserServiceTx;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.fail;
import static spring.toby1.service.UserServiceImpl.MIN_LOGCOUNT_FOR_SILVER;
import static spring.toby1.service.UserServiceImpl.MIN_RECCOMEND_FOR_GOLD;

/**
 * Created by yuuuunmi on 2017. 10. 17..
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/application-context.xml")
public class UserServiceTest {
    @Autowired
    UserServiceImpl userService;

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    UserDao userDao;

    @Autowired
    PlatformTransactionManager transactionManager;

    @Autowired
    MailSender mailSender;

    List<User> users;

    @Before
    public void setUp() {
        users = Arrays.asList(
                new User("bumjin", "박범진", "p1", Level.BASIC, MIN_LOGCOUNT_FOR_SILVER - 1, 0, "bumjin@a"),
                new User("joytouch", "강명성", "p2", Level.BASIC, MIN_LOGCOUNT_FOR_SILVER, 0, "joytouch@b"),
                new User("erwins", "신승한", "p3", Level.SILVER, 60, MIN_RECCOMEND_FOR_GOLD - 1, "erwins@c"),
                new User("madnite1", "이상호", "p4", Level.SILVER, 60, MIN_RECCOMEND_FOR_GOLD, "madnite1@d"),
                new User("green", "오민규", "p5", Level.GOLD, 100, Integer.MAX_VALUE, "green@e")
        );

    }

    @Test
    public void add() {
        userDao.deleteAll();

        User userWithLevel = users.get(4);
        User userWithoutLevel = users.get(0);

        userService.add(userWithLevel);
        userService.add(userWithoutLevel);

        User userWithLevelRead = userDao.get(userWithLevel.getId());
        User userWithoutLevelRead = userDao.get(userWithoutLevel.getId());

        assertThat(userWithLevelRead.getLevel(), is(userWithLevel.getLevel()));
        assertThat(userWithoutLevelRead.getLevel(), is(Level.BASIC));


    }

    @Test
    public void upgradeLevels() throws Exception {
        userDao.deleteAll();
        for (User user : users) userDao.add(user);


        MockMailSender mockMailSender = new MockMailSender();
        userServiceImpl.setMailSender(mockMailSender);

        userService.upgradeLevels();

        checkLevelUpgraded(users.get(0), false);
        checkLevelUpgraded(users.get(1), true);
        checkLevelUpgraded(users.get(2), false);
        checkLevelUpgraded(users.get(3), true);
        checkLevelUpgraded(users.get(4), false);

        List<String> request = mockMailSender.getRequests();
        assertThat(request.size(), is(2));
        assertThat(request.get(0), is(users.get(1).getEmail()));
        assertThat(request.get(1), is(users.get(3).getEmail()));


    }

    private void checkLevelUpgraded(User user, boolean upgraded) {
        User userUpdate = userDao.get(user.getId());
        if (upgraded) {
            assertThat(userUpdate.getLevel(), is(user.getLevel().nextLevel()));
        } else {
            assertThat(userUpdate.getLevel(), is(user.getLevel()));
        }
    }

    @Test
    public void bean() {
        assertThat(this.userService, is(notNullValue()));
    }


    static class TestUserService extends UserServiceImpl {
        private String id;

        private TestUserService(String id) {
            this.id = id;
        }

        protected void upgradeLevel(User user) {
            if (user.getId().equals(this.id)) throw new TestUserServiceException();
            super.upgradeLevel(user);
        }
    }

    @Test
    public void upgradeAllOrNothing() throws Exception {
        UserServiceImpl testUserService = new TestUserService(users.get(3).getId());
        testUserService.setUserDao(this.userDao);
        testUserService.setMailSender(mailSender);

        UserServiceTx userServiceTx = new UserServiceTx();
        userServiceTx.setTransactionManager(transactionManager);
        userServiceTx.setUserService(testUserService);

        userDao.deleteAll();
        for (User user : users) userDao.add(user);

        try {
            userServiceTx.upgradeLevels();
            fail("TestUserServiceException expected");
        } catch (TestUserServiceException e) { }

        checkLevelUpgraded(users.get(1), false);
        testUserService.setMailSender(mailSender);

    }

}
