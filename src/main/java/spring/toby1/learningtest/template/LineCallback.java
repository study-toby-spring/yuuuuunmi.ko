package spring.toby1.learningtest.template;

/**
 * Created by yuuuunmi on 2017. 10. 12..
 */
public interface LineCallback<T> {
    T doSomethingWithLine(String line, T value);
}
