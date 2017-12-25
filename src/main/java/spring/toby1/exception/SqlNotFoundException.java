package spring.toby1.exception;

/**
 * Created by yuuuunmi on 2017. 12. 25..
 */
public class SqlNotFoundException extends RuntimeException {
    public SqlNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public SqlNotFoundException(String message) {
        super(message);
    }

    public SqlNotFoundException(){
        super();
    }
}
