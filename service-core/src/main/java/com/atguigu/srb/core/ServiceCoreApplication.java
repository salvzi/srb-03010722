package com.atguigu.srb.core;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication//(exclude = DataSourceAutoConfiguration.class)
@ComponentScan({"com.atguigu.srb.mq","com.atguigu.srb.common","com.atguigu.srb.core","com.atguigu.srb.base"})
@EnableSwagger2
public class ServiceCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceCoreApplication.class,args);
    }
}
