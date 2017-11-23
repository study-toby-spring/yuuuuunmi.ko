package spring.toby1.service.proxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * Created by yuuuunmi on 2017. 11. 23..
 */
public class TransactionAdvice implements MethodInterceptor {
    PlatformTransactionManager transactionManager;

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        TransactionStatus status =
                this.transactionManager.getTransaction(new DefaultTransactionDefinition());

        try{
            Object ret = methodInvocation.proceed();
            this.transactionManager.commit(status);
            return ret;
        } catch (RuntimeException e){
            this.transactionManager.rollback(status);
            throw e;
        }
    }
}
