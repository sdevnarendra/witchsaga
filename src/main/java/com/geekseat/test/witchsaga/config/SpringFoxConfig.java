package com.geekseat.test.witchsaga.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {
    @Bean
    public Docket apiDocket() {
    	return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.geekseat.test.witchsaga.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }
    
    // Addition
    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "Witch Saga Solutions",
                "Software for solving Witch Problem",
                "1.0.0",
                " - ",
                new Contact("Narendra"," - "," - "),
                "GNU",
                "LICENSE URL",
                Collections.emptyList()
        );
    }

    
}