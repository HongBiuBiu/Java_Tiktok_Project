package com.tictok;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfig {

    @Bean
    public Docket defaultApi2() {
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        //.title("swagger-bootstrap-ui-demo RESTful APIs")
                        .description("...")
                        .termsOfServiceUrl("http://www.google.com/")
                        .contact(new Contact("NYU", "http://www.google.com/", "ms13475@nyu.edu"))
                        .version("1.0")
                        .build())
                .groupName("2.X")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tictok.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}
