package com.atguigu.srb.mybatisPlus;

import com.atguigu.srb.mybatisPlus.mapper.UserMapper;
import com.atguigu.srb.mybatisPlus.pojo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class Test1 {

    @Autowired
    UserMapper userMapper;

    @Test
    public void c(){

        // 输入一个参数：n，根据参数，查询名字中包含n的所有user信息
        String n = "n";
        List<User> users = userMapper.selectByName(n);
        System.out.println(users);
    }


    @Test
    public void b(){
        User user = new User();
        user.setName("1231");
        user.setAge(12);
        userMapper.insert(user);
    }

    @Test
    public void a(){
        List<User> list = userMapper.selectList(null);
        List<Map<String, Object>> maps = userMapper.selectMaps(null);
        System.out.println(maps);
        System.out.println(list);
    }
}
