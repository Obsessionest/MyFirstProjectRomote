package cn.admin.springboot04webrestfulcrud.compont;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * 给容器中添加自定义的ErrorAttributes，目的是为了将我们定制的数据携带出去
 * @author Wang
 */
@Component
public class MyErrorAttributes extends DefaultErrorAttributes {

    public MyErrorAttributes() {
        super(true);
    }

    /**
     * 返回值的map就是页面和json能获取的所有字段
     * @param webRequest
     * @param includeStackTrace
     * @return
     */
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String,Object> map = super.getErrorAttributes(webRequest, includeStackTrace);
        //    int SCOPE_REQUEST = 0;
        //    int SCOPE_SESSION = 1;
        //    java.lang.String REFERENCE_REQUEST = "request";
        //    java.lang.String REFERENCE_SESSION = "session";

        // 我们的异常处理器携带的数据
        Map<String,Object> ext = (Map<String, Object>) webRequest.getAttribute("ext",0);
        map.put("ext",ext);
        map.put("user","admin");
        return map;
    }
}
