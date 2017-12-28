package spring.toby1.exception;

/**
 * Created by yuuuunmi on 2017. 12. 28..
 */
public class SqlUpdateFailureException extends RuntimeException{

    public SqlUpdateFailureException() {
        super();
    }

    public SqlUpdateFailureException(String message) {
        super(message);
    }

    public SqlUpdateFailureException(String message, Throwable cause) {
        super(message, cause);
    }
}
