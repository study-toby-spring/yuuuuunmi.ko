package spring.toby1.service;

import spring.toby1.domain.User;

import java.util.List;

/**
 * Created by yuuuunmi on 2017. 11. 5..
 */
public interface UserService {
    void add(User user);
    User get(String id);
    List<User> getAll();
    void deleteAll();
    void update(User user);
    void upgradeLevels() throws Exception;

}
