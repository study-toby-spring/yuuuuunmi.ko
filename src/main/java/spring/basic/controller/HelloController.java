package spring.basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
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

    @RequestMapping(
            path = "/",
            method = {RequestMethod.GET, RequestMethod.POST}
    )
    public String index(Model model){
        // Body가 String 으로 나간다는것 .
        // ResponseBody가 안붙으면 View가 나감

        model.addAttribute("title", "Hello :D");
        return "hello";
    }

    @RequestMapping(
            path = "/index",
            method = {RequestMethod.GET, RequestMethod.POST}
    )
    public ModelAndView index2(){

        ModelAndView mav = new ModelAndView();

        mav.addObject("title","Hello");
        ㄴ아.setViewName("hello");

        return mav;
    }

}
