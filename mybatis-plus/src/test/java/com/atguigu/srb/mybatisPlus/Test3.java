package com.atguigu.srb.mybatisPlus;

import com.atguigu.srb.mybatisPlus.mapper.UserMapper;
import com.atguigu.srb.mybatisPlus.pojo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Test3 {

    @Autowired
    UserMapper userMapper;


    @Test
    public void b(){

        userMapper.deleteById(26L);

    }

    @Test
    public void a(){

//        User user = new User();
//        user.setId(26L);
//        user.setName("abc123");

        User user1 = userMapper.selectById(26L);
        user1.setName("张三");
        userMapper.updateById(user1);

    }
}
