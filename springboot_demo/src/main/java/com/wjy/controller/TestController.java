package com.wjy.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author wangjingyang
 * @date 2021-02-10 14:38
 */
@RestController
@RequestMapping(value = "test")
public class TestController {

    @Value("#{${test.map1}}")
    private Map<String,String> map1;

    @Value("#{${test.map2}}")
    private Map<String,Integer> map2;

    @GetMapping("test2")
    public void test2(){
        System.out.println(map1);
        System.out.println(map2);
    }
}
