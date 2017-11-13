package spring.toby1.learningtest.jdk;

/**
 * Created by yuuuunmi on 2017. 11. 14..
 */
public class HelloTarget implements Hello {
    public String sayHello(String name) {
        return "Hello " + name;
    }

    public String sayHi(String name) {
        return "Hi " + name;
    }

    public String sayThankU(String name) {
        return "Thank You " + name;
    }
}
