package com.atguigu.srb.mybatisPlus.service;

import com.atguigu.srb.mybatisPlus.pojo.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface UserService extends IService<User> {
    List<User> getByName(String name);
}
