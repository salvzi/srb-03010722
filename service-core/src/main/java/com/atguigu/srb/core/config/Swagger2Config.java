package com.atguigu.srb.core.config;


import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class Swagger2Config {

    @Bean
    public Docket adminDock(){
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("积分等管理接口")
                .description("积分等用户接口")
                .contact(new Contact("Helen", "http://atguigu.com", "55317332@qq.com"))
                .build();
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName("core-admin")
                .apiInfo(apiInfo)
                .select()
                .paths(Predicates.and(PathSelectors.regex("/admin/.*")))
                .build();
        return docket;
    }

    @Bean
    public Docket apiDock(){
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("积分等用户接口")
                .description("积分等用户接口")
                .contact(new Contact("Helen", "http://atguigu.com", "55317332@qq.com"))
                .build();
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName("core-api")
                .apiInfo(apiInfo)
                .select()
                .paths(Predicates.and(PathSelectors.regex("/api/.*")))
                .build();
        return docket;
    }

}
