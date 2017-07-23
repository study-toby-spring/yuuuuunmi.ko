package spring.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by yuuuunmi on 2017. 7. 22..
 */
@Controller
public class HelloController {

    @RequestMapping(
        path = "/hello",
        method = {RequestMethod.GET, RequestMethod.POST}
    )
    @ResponseBody
    public String hello(@RequestParam("id") int id){
        return "Hello, world! " + id;
    }

}
