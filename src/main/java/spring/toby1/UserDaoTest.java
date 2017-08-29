package spring.toby1;

import spring.toby1.dao.ConnectionMaker;
import spring.toby1.dao.DConnectionMaker;
import spring.toby1.dao.DaoFactory;
import spring.toby1.dao.UserDao;
import spring.toby1.domain.User;

import java.sql.SQLException;

/**
 * Created by yuuuunmi on 2017. 7. 30..
 */
public class UserDaoTest {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        UserDao dao = new DaoFactory().userDao();


        User user = new User();
        user.setId("yuuuunmi");
        user.setName("윰미고");
        user.setPassword("hihihi");

        dao.add(user);

        System.out.println(user.getId() + " 등록 성공");

        User user2 = dao.get(user.getId());

        System.out.println(user2.getName());
        System.out.println(user2.getPassword());

        System.out.println(user.getId() + " 조회 성공");
    }
}
