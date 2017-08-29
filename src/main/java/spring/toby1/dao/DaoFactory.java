package spring.toby1.dao;

/**
 * Created by yuuuunmi on 2017. 8. 29..
 */
public class DaoFactory {
    public UserDao userDao(){
        UserDao userDao = new UserDao(connectionMaker());
        return userDao;
    }

    public ConnectionMaker connectionMaker(){
        return new DConnectionMaker();
    }
}
