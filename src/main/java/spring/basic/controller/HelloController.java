package spring.basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import spring.basic.domain.Hello;
import spring.basic.util.HelloFactory;


/**
 * Created by yuuuunmi on 2017. 7. 22..
 */
@Controller
public class HelloController {

    @Autowired
    private HelloFactory helloFactory;

    @RequestMapping(
        path = "/hello",
        method = {RequestMethod.GET, RequestMethod.POST}
    )
    @ResponseBody
    public String hello(@RequestParam("id") int id){



        Hello instance = helloFactory.createInstance();

        return instance.toString();
        /*if(id > 10) {
            return "out of range";
        } else {

            return "Hello, world! " + id;
        }*/

    }

}
