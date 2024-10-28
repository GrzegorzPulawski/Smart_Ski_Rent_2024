package com.smart_ski_rent_ver1_2.security.configuration;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
@Configuration
public class Swagger {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("Security Smart Ski Rent")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.smart_ski_rent_ver1_2.security.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Smart Ski Rent")
                .description("MySql database authentication")
                .version("1.0")
                .build();
    }
    @EventListener(ApplicationReadyEvent.class)
    public void displayUrl(){
        System.out.println("Swagger-ui url: http://localhost:8080/swagger-ui/index.html");
    }
}
