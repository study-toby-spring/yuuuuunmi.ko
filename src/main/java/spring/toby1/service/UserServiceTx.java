package spring.toby1.service;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import spring.toby1.domain.User;

import java.util.List;

/**
 * Created by yuuuunmi on 2017. 11. 5..
 */
public class UserServiceTx implements UserService {

    UserService userService;
    PlatformTransactionManager transactionManager;

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    public void add(User user) {
        userService.add(user);
    }

    public User get(String id) {
        return null;
    }

    public List<User> getAll() {
        return null;
    }

    public void deleteAll() {

    }

    public void update(User user) {

    }

    public void upgradeLevels() throws Exception {
        TransactionStatus status = this.transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {

            userService.upgradeLevels();
            this.transactionManager.commit(status);
        } catch (RuntimeException e ){
            this.transactionManager.rollback(status);
            throw e;
        }
    }
}
