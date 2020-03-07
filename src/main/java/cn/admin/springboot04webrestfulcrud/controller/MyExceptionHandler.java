package cn.admin.springboot04webrestfulcrud.controller;

import cn.admin.springboot04webrestfulcrud.exception.UserNotExistException;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * ControllerAdvice controller增强器,可以对controller中被@RequestMapping标注的方法加一些逻辑处理
 * @author Wang
 */
@ControllerAdvice
public class MyExceptionHandler {

//    /**
//     * 全局异常捕获处理
//     * @param e
//     * @return
//     */
//    @ResponseBody
//    @ExceptionHandler(UserNotExistException.class)
//    public Map handleException(Exception e){
//        Map<String,Object> map = new HashMap<String,Object>();
//        map.put("code","username not exist");
//        map.put("message",e.getMessage());
//        return map;
//    }

    @ExceptionHandler(UserNotExistException.class)
    public String handleException(Exception e, HttpServletRequest request){
        //设置自己定义的状态码 4xx 5xx 否则页面还是200状态码 不会进入定制错误页面的解析过程
        request.setAttribute("javax.servlet.error.status_code",500);;
        Map<String,Object> map = new HashMap<>();
        map.put("code","username not exist");
        map.put("message",e.getMessage());
        request.setAttribute("ext",map);
        //转发到/error SpringBoot处理异常的默认路径类路径下的error文件夹下的页面
        return "forward:/error";
    }
}
