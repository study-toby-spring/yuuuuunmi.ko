package spring.basic.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

/**
 * Created by yuuuunmi on 2017. 7. 29..
 */

@Configuration
public class HelloConfiguration {

    @Bean
    public Random random(){
        return new Random();
    }


}
