package com.wjy.swagger.controller;

import com.wjy.swagger.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangjingyang
 * @date 2021-02-11 21:25
 */
@RestController
@Api(tags = "[hello控制器]",value = "hello")
public class HellerController {

    @RequestMapping(value = "hello",method = RequestMethod.GET)
    @ApiOperation(value = "hello",response = User.class,httpMethod = "GET")
    public User hello(){
        return new User();
    }
}
