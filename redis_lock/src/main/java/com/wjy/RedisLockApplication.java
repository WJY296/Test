package com.wjy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author wangjingyang
 * @date 2021-01-24 15:05
 */
@SpringBootApplication
@MapperScan(basePackages = "com.wjy.mapper")
public class RedisLockApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(RedisLockApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(RedisLockApplication.class);
    }
}