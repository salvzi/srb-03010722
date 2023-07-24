package com.atguigu.srb.mybatisPlus.service.impl;

import com.atguigu.srb.mybatisPlus.mapper.UserMapper;
import com.atguigu.srb.mybatisPlus.pojo.entity.User;
import com.atguigu.srb.mybatisPlus.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService  {


    @Override
    public List<User> getByName(String name) {
        List<User> users = baseMapper.selectByName(name);
        return users;
    }

}
