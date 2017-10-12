package spring.toby1.learningtest.template;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by yuuuunmi on 2017. 10. 12..
 */
public class CalcSumTest {
    Calculator calculator;
    String numFilepath;

    @Before public void setUp(){
        this.calculator = new Calculator();
        this.numFilepath = getClass().getResource("/numbers.txt").getPath();
    }


    @Test public void sumOfNumbers() throws IOException{
        assertThat(calculator.calcSum(this.numFilepath), is(10));
    }

    @Test public void multiplyOfNumbers() throws IOException{
        assertThat(calculator.calcMultiply(this.numFilepath), is(24));
    }

    @Test public void concatenateStrings() throws IOException{
        assertThat(calculator.concatenateCallback(this.numFilepath), is("1234"));
    }
}
