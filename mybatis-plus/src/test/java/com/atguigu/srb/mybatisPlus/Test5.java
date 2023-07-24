package com.atguigu.srb.mybatisPlus;

import com.atguigu.srb.mybatisPlus.mapper.ProductMapper;
import com.atguigu.srb.mybatisPlus.mapper.UserMapper;
import com.atguigu.srb.mybatisPlus.pojo.entity.Product;
import com.atguigu.srb.mybatisPlus.pojo.entity.User;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class Test5 {

    @Autowired
    UserMapper userMapper;

    @Autowired
    ProductMapper productMapper;


    @Test
    public void b(){

        userMapper.deleteById(26L);

    }

    @Test
    public void a(){

        // 小李查询数据库
        Product productLi = productMapper.selectById(1L);

        // 小王查询数据库
        Product productWang = productMapper.selectById(1L);

        // 小李修改价格
        productLi.setPrice(productLi.getPrice()+50);
        productMapper.updateById(productLi);

        // 小王修改价格
        productWang.setPrice(productWang.getPrice()-30);
        productMapper.updateById(productWang);

        // 数据库最终价格
        Product product = productMapper.selectById(1L);
        System.out.println(product);


    }
}
