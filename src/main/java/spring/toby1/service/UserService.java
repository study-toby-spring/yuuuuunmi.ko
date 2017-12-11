package spring.toby1.service;

import org.springframework.transaction.annotation.Transactional;
import spring.toby1.domain.User;

import java.util.List;

/**
 * Created by yuuuunmi on 2017. 11. 5..
 */
@Transactional
public interface UserService {
    void add(User user);
    void deleteAll();
    void update(User user);
    void upgradeLevels() throws Exception;


    @Transactional(readOnly = true)
    User get(String id);

    @Transactional(readOnly = true)
    List<User> getAll();

}
