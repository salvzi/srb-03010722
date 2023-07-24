package com.atguigu.srb.mybatisPlus;

import com.atguigu.srb.mybatisPlus.mapper.UserMapper;
import com.atguigu.srb.mybatisPlus.pojo.entity.User;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class Test4 {

    @Autowired
    UserMapper userMapper;


    @Test
    public void b(){

        userMapper.deleteById(26L);

    }

    @Test
    public void a(){

        IPage<User> iPage = new Page<>();
        iPage.setCurrent(2);
        iPage.setSize(4);

        IPage<User> userIPage = userMapper.selectPage(iPage, null);

        long total = userIPage.getTotal();
        long current = userIPage.getCurrent();
        long size = userIPage.getSize();
        List<User> records = userIPage.getRecords();

        System.out.println(total);
        System.out.println(current);
        System.out.println(size);
        System.out.println(records);


    }
}
