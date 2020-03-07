package cn.admin.springboot04webrestfulcrud.config;

import cn.admin.springboot04webrestfulcrud.compont.LoginInterceptor;
import cn.admin.springboot04webrestfulcrud.compont.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 使用WebMvcConfigurer来扩展SpringMVC功能
 *
 * 通过@Configuration注解的类将被作为配置类使用，且使用@Configuration注解的类本身也是一个Bean,
 * 使用 @Configuration 注解需要作为配置的类，表示该类将定义Bean的元数据，
 *
 * 表示在该类中将定义Bean
 * 配置元数据
 *
 * 使用 @Bean 注解相应的方法，该方法名默认就是Bean的名称，该方法返回值就是Bean的对象
 * @author Wang
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/admin").setViewName("success");
    }

    /**
     * 注册拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //SpringBoot已经做好了静态资源映射，所以不用拦截静态资源(.css,.js)
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/index.html","/user/login","/","/webjars/**","/static/asserts/**");
    }

    /**
     * 自定义的 webMvcConfigurer 会和默认的一起起作用
     * 将组件注入到容器中
     * @return
     */
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }
        };



        return webMvcConfigurer;
    }


    /**
     * 这里必须写localeResolver，否则加载不到容器中
     * @return
     */
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }
}
