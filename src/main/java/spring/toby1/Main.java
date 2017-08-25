package spring.toby1;

import spring.toby1.dao.UserDao;
import spring.toby1.domain.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by yuuuunmi on 2017. 7. 30..
 */
public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        UserDao dao = new UserDao() {
            @Override
            public Connection getConnection() throws ClassNotFoundException, SQLException {
                Class.forName("com.mysql.jdbc.Driver");

                Connection c = DriverManager.getConnection("jdbc:mysql://localhost/spring?useSSL=false", "root", "rha09222");

                return c;
            }
        };

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
