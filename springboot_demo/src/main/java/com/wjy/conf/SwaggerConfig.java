package com.wjy.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @author wangjingyang
 * @date 2021-02-11 21:30
 */
@Configuration //开启swagger2
@EnableSwagger2
public class SwaggerConfig {

    @Bean
     public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                //是否开启 wsagger
                .enable(true)
                .apiInfo(apiInfo())
                .select()
                //扫描指定的包
                .apis(RequestHandlerSelectors.basePackage("com.wjy.controller"))
                //过滤什么路径  any() 什么都不过滤
                .paths(PathSelectors.any())
                .build();
     }

    private ApiInfo apiInfo(){
        //作者信息
        Contact contact = new Contact("王静洋","没有","17681815392@163.com");
        return new ApiInfo(
                "SwaggerApi文档", //标题
                "描述", //描述
                "v1.0", //版本
                "urn:tos",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList()
        );
    }
}