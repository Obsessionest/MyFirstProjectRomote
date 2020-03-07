package cn.admin.springboot04webrestfulcrud.controller;

import cn.admin.springboot04webrestfulcrud.exception.UserNotExistException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author Wang
 */
@Controller
public class HelloController {

    @ResponseBody
    @RequestMapping("hello/{username}")
    public String hello(@PathVariable("username") String username){
        if ("aaa".equals(username)){
            throw new UserNotExistException();
        }
        return "hello";
    }

    @RequestMapping("/success")
    public String success(Map<String,Object> map){
        map.put("hello","你好");
        return "success";
    }

}
