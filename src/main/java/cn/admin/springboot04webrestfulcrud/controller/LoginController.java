package cn.admin.springboot04webrestfulcrud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 登录
 * @author Wang
 */
@Controller
public class LoginController {

    /**
     * 在此处定义账号密码是为了避免魔法值
     */
    static final String PASSWORD = "123456";
    static final String USERNAME = "admin";

    @PostMapping("/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String,Object> map,
                        HttpSession session){

        if (USERNAME.equals(username) && PASSWORD.equals(password)){
            //登录成功.为了防止表单重复提交，可以重定向到主页
            session.setAttribute("loginUser",username);
            return "redirect:/main.html";
        }else {
            //登录失败
            map.put("msg","登录密码失败");
            return "login";
        }
    }
}
