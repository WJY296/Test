package com.wjy.webconf;


import com.wjy.listener.MyServletContextListener;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 86176
 */
@Configuration
public class MyWebConfig implements WebMvcConfigurer {
    /**
     * 注册监听器
     *
     * @return
     */
    @Bean
    public ServletListenerRegistrationBean registrationBean() {
        ServletListenerRegistrationBean registrationBean = new ServletListenerRegistrationBean();
        registrationBean.setListener(new MyServletContextListener());
        return registrationBean;
    }


}
