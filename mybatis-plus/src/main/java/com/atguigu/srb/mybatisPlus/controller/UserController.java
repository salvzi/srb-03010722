package com.atguigu.srb.mybatisPlus.controller;


import com.atguigu.srb.mybatisPlus.pojo.entity.User;
import com.atguigu.srb.mybatisPlus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/user/")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/list")
    public List<User> getList(HttpServletRequest request){
        String token2 = request.getHeader("token2");

        Cookie[] cookies = request.getCookies();
        List<User> list = userService.list();
        return list;
    }
}
