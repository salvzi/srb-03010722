package com.atguigu.srb.mybatisPlus;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication//(exclude = DataSourceAutoConfiguration.class)
public class ServiceMybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceMybatisPlusApplication.class,args);
    }
}
