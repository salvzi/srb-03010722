package com.atguigu.srb.core;


import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Test2 {

    @Test
    public void b(){

        // 读取学生点名表
        EasyExcel.read("d:/a.xlsx",Student.class,new MyExcelListener()).sheet("学生点名表").doRead();



    }

    @Test
    public void a(){

        // 写出学生点名表
        List<Student> list = new ArrayList<>();

        for (int i = 0; i < 108; i++) {
            Student student = new Student();
            student.setName("小"+i);
            student.setAge(i+10);
            list.add(student);
        }

        // excel输出
        EasyExcel.write("d:/a.xlsx",Student.class).sheet("学生点名表").doWrite(list);

    }
}
