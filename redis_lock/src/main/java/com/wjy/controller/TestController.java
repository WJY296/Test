package com.wjy.controller;

import com.wjy.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wangjingyang
 * @date 2021-01-24 15:23
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {
    @Resource
    private UserService userService;
    @RequestMapping(value = "test1",method = RequestMethod.GET)
    public Boolean test(){
        return userService.test();
    }

    @RequestMapping(value = "test2",method = RequestMethod.GET)
    public Boolean test2(){
        return userService.test2();
    }

}
