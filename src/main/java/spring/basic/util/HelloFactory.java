package spring.basic.util;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import spring.basic.domain.Hello;

import java.util.Random;

/**
 * Created by yuuuunmi on 2017. 7. 29..
 */

@Data
//@Component // 스프링에서 관리하는 대상이 됨
public class HelloFactory {


    @Autowired
    @Qualifier("idGenerator")
    private Random random;



/*
    @PostConstruct
    public void initialize(){
        this.random = new Random();
    }
*/
    public Hello createInstance(){
        return new Hello(random.nextInt(100), "Hello");
    }

}
