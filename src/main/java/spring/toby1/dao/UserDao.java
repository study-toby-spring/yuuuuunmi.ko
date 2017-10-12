package spring.toby1.dao;

import spring.toby1.domain.User;

import java.util.List;

/**
 * Created by yuuuunmi on 2017. 10. 12..
 */
public interface UserDao {
    void add(User user);
    User get(String id);
    List<User> getAll();
    void deleteAll();
    int getCount();
}
