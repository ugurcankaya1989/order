package com.getir.order.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@PropertySource("classpath:swagger.properties")
@Configuration
public class SwaggerConfiguration implements WebMvcConfigurer {

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Order API")
                .description("Getir API reference for developers")
                .termsOfServiceUrl("http://www.google.com")
                .contact(new Contact("Ugur Cankaya","","ugur.cankaya@gmail.com"))
                .license("Ugur Cankaya License")
                .licenseUrl("ugur.cankaya@gmail.com").version("1.0").build();
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.basePackage("com.getir.order.controller")).paths(PathSelectors.any()).build();
    }

}
