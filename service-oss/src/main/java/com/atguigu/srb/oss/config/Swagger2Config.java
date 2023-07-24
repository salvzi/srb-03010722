package com.atguigu.srb.oss.config;


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
    public Docket ossDock(){
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("oss接口")
                .description("oss接口")
                .contact(new Contact("Helen", "http://atguigu.com", "55317332@qq.com"))
                .build();
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName("oss")
                .apiInfo(apiInfo)
                .select()
                .paths(Predicates.and(PathSelectors.regex("/api/oss/.*")))
                .build();
        return docket;
    }


}
