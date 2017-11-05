package spring.toby1.service;

import spring.toby1.domain.User;

/**
 * Created by yuuuunmi on 2017. 11. 5..
 */
public interface UserService {
    void add(User user);
    void upgradeLevels() throws Exception;

}
