package spring.toby1.exception;

/**
 * Created by yuuuunmi on 2017. 12. 15..
 */
public class SqlRetrievalFailureException extends RuntimeException {
    public SqlRetrievalFailureException(String message, Throwable cause) {
        super(message, cause);
    }

    public SqlRetrievalFailureException(String message) {

        super(message);
    }
}
