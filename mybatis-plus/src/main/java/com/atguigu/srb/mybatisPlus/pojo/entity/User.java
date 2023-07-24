package com.atguigu.srb.mybatisPlus.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("user")
public class User {

    //@TableId(type = IdType.ASSIGN_ID)
    @TableId(type = IdType.AUTO)// 使用mysql自增主键
    private Long id;// 默认雪花id，表中数据量过大

    @TableField("name")
    private String name;
    @TableField
    private Integer age;
    @TableField
    private String email;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField(value = "update_time",exist = true,fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(value = "is_deleted")
    @TableLogic
    private Integer deleted;

    @Version
    private Integer version;
}