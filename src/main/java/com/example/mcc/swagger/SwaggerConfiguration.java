package com.example.mcc.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.mcc.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(info())
                .securitySchemes(List.of(apiKey()))
                .securityContexts(List.of(securityContext()));
    }
    // JWT SecurityContext 구성
    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return List.of(new SecurityReference("Authorization", authorizationScopes));
    }
    private ApiKey apiKey(){
        return new ApiKey("Authorization" ,"Authorization","header");
    }
    private ApiInfo info(){
        return new ApiInfoBuilder()
                .title("MCC 동아리 홈페이지 제작 api 문서")
                .description("api 문서")
                .version("1.0.0")
                .build();
    }
}