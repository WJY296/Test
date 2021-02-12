package com.baizhi.controller;


import com.baizhi.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public Map login(String username, String password) {
        System.out.println(username + "=======" + password);
        Map map = new HashMap();

        //        1.封装到令牌中
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
//        2.获取主体
        Subject subject = SecurityUtils.getSubject();

        //        Session的使用和以前一样
//        获取Session Shiro提供了一种更加简单的方法
        Session session = subject.getSession();

//        3.通过主体登录
        subject.login(token);
//            获取用户名
        String username1 = SecurityUtils.getSubject().getPrincipal().toString();
        System.out.println("ssss" + username1);
        if (username1 != null) {
            map.put("res", true);
        } else {
            map.put("res", false);
        }
        return map;
    }


    @RequestMapping("/regist")
    public Map regist(String username, String password) {
        Map map = new HashMap();
        try {
            userService.regist(username, password);
            map.put("res", true);
        } catch (Exception e) {
            map.put("res", false);
            e.printStackTrace();
        }
        return map;
    }
}
