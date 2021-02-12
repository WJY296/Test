package com.wjy.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController

public class TestController {
    @GetMapping("addSession")
    public String addSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("name", "haixiang");
        return "当前在线人数" + session.getAttribute("sessionCount") + "人";
    }

    @GetMapping("removeSession")
    public String removeSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "当前在线人数" + session.getAttribute("sessionCount") + "人";
    }


}
