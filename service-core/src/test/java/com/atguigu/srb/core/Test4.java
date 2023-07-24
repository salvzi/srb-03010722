package com.atguigu.srb.core;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.math.BigDecimal;


public class Test4 {


    @Test
    public void d(){

        // 初始化运算变量,不用基本数据类型
        BigDecimal b1 = new BigDecimal(0.01d);
        BigDecimal b2 = new BigDecimal(0.01f);
        System.out.println(b1);
        System.out.println(b2);

        BigDecimal b3 = new BigDecimal("0");
        System.out.println(b3);
        
        // 比较，不用比较符号
        int i = b1.compareTo(b2);
        System.out.println(i);

        // 计算，不用运算符
        BigDecimal b4 = new BigDecimal("6");
        BigDecimal b5 = new BigDecimal("7");

        BigDecimal add = b4.add(b5);
        BigDecimal subtract = b4.subtract(b5);
        BigDecimal multiply = b4.multiply(b5);
        System.out.println("==================");
        BigDecimal divide = b4.divide(b5,8,BigDecimal.ROUND_HALF_DOWN);
        System.out.println(divide);

        BigDecimal add1 = b1.add(b2);
        System.out.println(add1);
        BigDecimal bigDecimal = add1.setScale(8, BigDecimal.ROUND_HALF_DOWN);
        System.out.println(bigDecimal);


    }


}
