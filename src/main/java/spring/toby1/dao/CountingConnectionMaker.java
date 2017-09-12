package spring.toby1.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by yuuuunmi on 2017. 9. 12..
 */
public class CountingConnectionMaker implements ConnectionMaker {

    int counter = 0;
    private ConnectionMaker realConnectionMaker;

    public CountingConnectionMaker(ConnectionMaker realConnectionMaker) {
        this.realConnectionMaker = realConnectionMaker;
    }

    public Connection makeConnection() throws ClassNotFoundException, SQLException {
        this.counter++;
        return realConnectionMaker.makeConnection();

    }

    public int getCounter() {
        return counter;
    }
}
