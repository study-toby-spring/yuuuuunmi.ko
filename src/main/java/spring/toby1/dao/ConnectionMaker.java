package spring.toby1.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by yuuuunmi on 2017. 8. 29..
 */
public interface ConnectionMaker {
    public Connection makeConnection() throws ClassNotFoundException, SQLException;
}
