package com.novalabs.task.config;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.novalabs.task.controller"))
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfo()).securitySchemes(Lists.newArrayList(apiKey()))
                ;
    }

    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                .title("Nova Labs Task")
                .description("Nova Labs Api Documentation")
                .version("1.0").contact(new Contact("Ankush Karande", "", "ankushkarande555@gmail.com"))
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey(HttpHeaders.AUTHORIZATION, "Authorization", "header");
    }
}
