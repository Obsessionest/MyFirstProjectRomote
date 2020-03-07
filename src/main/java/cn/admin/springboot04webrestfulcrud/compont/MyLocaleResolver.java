package cn.admin.springboot04webrestfulcrud.compont;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 自定义国际化配置，为了在链接上携带区域信息
 * @author Wang
 */
public class MyLocaleResolver implements LocaleResolver {

    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        // 从request中获取参数l的值
        String l = httpServletRequest.getParameter("l");
        // 获取默认的区域信息
        Locale locale = Locale.getDefault();
        // 如果参数l不为空,说明携带的有区域信息，分隔参数，设置新的区域信息
        //zh_CN或者en_US
        if ( !StringUtils.isEmpty(l) ){
            String[] s = l.split("_");
            locale = new Locale(s[0],s[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
