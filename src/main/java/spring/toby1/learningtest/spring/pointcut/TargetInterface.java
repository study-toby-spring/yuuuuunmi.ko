package spring.toby1.learningtest.spring.pointcut;

/**
 * Created by yuuuunmi on 2017. 12. 9..
 */
public interface TargetInterface {
    public void hello();
    public void hello(String a);
    public int minus(int a, int b) throws RuntimeException;
    public int plus(int a, int b);

}
