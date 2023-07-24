package com.atguigu.srb.mybatisPlus.mapper;

import com.atguigu.srb.mybatisPlus.pojo.entity.Product;
import com.atguigu.srb.mybatisPlus.pojo.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {

}
