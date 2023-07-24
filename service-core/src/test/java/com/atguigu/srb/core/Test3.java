package com.atguigu.srb.core;


import com.alibaba.excel.EasyExcel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;


import java.util.ArrayList;
import java.util.List;
@SpringBootTest
public class Test3 {

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void d(){

        redisTemplate.opsForHash().put("map2","k1","v1");

    }

    @Test
    public void c(){

        redisTemplate.opsForValue().set("k1","v1");
        Object k1 = redisTemplate.opsForValue().get("k1");
        System.out.println(k1);

    }


    @Test
    public void b(){

        Object k1 = redisTemplate.opsForValue().get("k1");
        System.out.println(k1);

    }

}
