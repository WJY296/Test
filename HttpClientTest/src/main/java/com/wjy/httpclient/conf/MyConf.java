package com.wjy.httpclient.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangjingyang
 * @date 2021-01-10 14:21
 */
@Data
@ConfigurationProperties(prefix = "test.list")
@Configuration
public class MyConf {
    private List<String>  urls= new ArrayList<>();
}
