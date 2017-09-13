package spring.toby1.dao;

import spring.toby1.domain.User;

import javax.sql.DataSource;
import java.sql.*;


/**
 * Created by yuuuunmi on 2017. 7. 5..
 */
public class UserDao {

    private DataSource dataSource;

    private ConnectionMaker connectionMaker;

    public UserDao() {
    }

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

//    }

//    public void setConnectionMaker(ConnectionMaker connectionMaker) {
//        this.connectionMaker = connectionMaker;
//    }

    public void add(User user) throws ClassNotFoundException, SQLException {

//        Connection c = connectionMaker.makeConnection();
        Connection c = dataSource.getConnection();

        PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) VALUES (?,?,?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {


//        Connection c = connectionMaker.makeConnection();
        Connection c = dataSource.getConnection();

        PreparedStatement ps = c.prepareStatement("select * from users where id=?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();

        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        c.close();

        return user;
    }

}
