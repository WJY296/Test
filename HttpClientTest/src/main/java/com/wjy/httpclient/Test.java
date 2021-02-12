package com.wjy.httpclient;


import com.alibaba.fastjson.JSONObject;
import com.wjy.httpclient.conf.MyConf;
import com.wjy.httpclient.entity.MyEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @author wangjingyang
 * @date 2021-01-02 14:58
 */
@RestController
@RequestMapping(value = "/test")
public class Test {

    @Resource
    private MyConf myConf;

    @RequestMapping(value = "/login")
    public void test() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost("http://localhost:8080/ph/admin/login");


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("loginName","admin");
        jsonObject.put("password","123456");

        StringEntity entity = new StringEntity(jsonObject.toJSONString(), "UTF-8");

        // post请求是将参数放在请求体里面传过去的;这里将entity放入post请求体中
        httpPost.setEntity(entity);

        httpPost.setHeader("Content-Type", "application/json;charset=utf8");
        HttpResponse httpResponse = httpClient.execute(httpPost);
        HttpEntity entity1 = httpResponse.getEntity();

        System.out.println(entity1.toString());


    }

    @RequestMapping(value = "/test")
    public void test1()  {
        List<String> urls = myConf.getUrls();
        System.out.println(urls);

    }

    public static void main(String[] args) {
        int code = MyEnum.FLE.getCode();
        System.out.println(code);

    }
    public static boolean isNullStr(String str) {
        boolean flag = false;
        if (str == null || "".equals(str.trim()) || "null".equalsIgnoreCase(str) || "undefined".equalsIgnoreCase(str)) {
            flag = true;
        }
        return flag;
    }

}
