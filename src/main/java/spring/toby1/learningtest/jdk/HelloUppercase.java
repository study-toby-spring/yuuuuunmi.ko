package spring.toby1.learningtest.jdk;

/**
 * Created by yuuuunmi on 2017. 11. 14..
 */
public class HelloUppercase implements Hello {
    Hello hello;

    public HelloUppercase(Hello hello) {
        this.hello = hello;
    }

    public String sayHello(String name) {
        return hello.sayHello(name).toUpperCase();
    }

    public String sayHi(String name) {
        return hello.sayHi(name).toUpperCase();
    }

    public String sayThankU(String name) {
        return hello.sayThankU(name).toUpperCase();
    }
}
