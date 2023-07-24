package com.atguigu.srb.core.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        System.out.println("新增时候触发");
        this.strictUpdateFill(metaObject,"updateTime", LocalDateTime.class,LocalDateTime.now());

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        System.out.println("更新时候触发");
        this.strictUpdateFill(metaObject,"updateTime", LocalDateTime.class,LocalDateTime.now());
    }
}
