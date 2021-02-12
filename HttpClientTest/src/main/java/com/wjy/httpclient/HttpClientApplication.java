package com.wjy.httpclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * @author belive
 */
@SpringBootApplication(scanBasePackages =  {"com.wjy.httpclient"})
@EnableScheduling
public class HttpClientApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(HttpClientApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(HttpClientApplication.class);
    }
}
