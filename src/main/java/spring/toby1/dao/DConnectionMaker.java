package spring.toby1.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by yuuuunmi on 2017. 8. 29..
 */
public class DConnectionMaker implements ConnectionMaker {

    public Connection makeConnection() throws ClassNotFoundException, SQLException {
        // D 사의 독자적인 방법으로 Connection을 생성하는 코드
        Class.forName("com.mysql.jdbc.Driver");
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost/spring?useSSL=false", "root", "rha09222");

        return c;
    }
}
