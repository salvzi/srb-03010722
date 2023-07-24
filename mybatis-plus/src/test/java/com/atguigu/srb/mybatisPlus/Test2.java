package com.atguigu.srb.mybatisPlus;


import com.atguigu.srb.mybatisPlus.pojo.entity.User;
import com.atguigu.srb.mybatisPlus.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class Test2 {

    @Autowired
    UserService userService;

    @Test
    public void b(){

        String name = "n";
        List<User> users = userService.getByName(name);
        System.out.println(users);

    }

    @Test
    public void a(){
        List<User> list = userService.list();
        Map<String, Object> map = userService.getMap(null);
        List<Map<String, Object>> maps = userService.listMaps();

        System.out.println(list);
        System.out.println(map);

    }
}
