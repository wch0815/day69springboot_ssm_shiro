package com.cheng.controller;

import com.cheng.pojo.User;
import com.cheng.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;

@Controller
public class UserController {

    @Resource
    private IUserService userService;

    @GetMapping("/{path}")
    public String getPath(@PathVariable("path") String path){
        return path;
    }


    @GetMapping("/")
    public String perPage(){
        return "login";
    }

    @PostMapping("/login")
    public String login(String username, String password){

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(username,password);

        subject.login(token);

        System.out.println(subject.hasRole("班主任"));

        if (subject.isAuthenticated())
            return "main.html";
        else
            return "index";
    }
}
