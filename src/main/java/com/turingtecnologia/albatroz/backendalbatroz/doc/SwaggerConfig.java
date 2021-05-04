package com.turingtecnologia.albatroz.backendalbatroz.doc;

import java.util.List;

import com.google.common.collect.Lists;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//Classe que configura o Swagger
@Configuration
@EnableSwagger2
@Import(springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    @Bean
    public Docket swaggerSpringfoxDocketq() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()).build().apiInfo(informacoesAPI())
                .securityContexts(Lists.newArrayList(securityContent()))
                .securitySchemes(Lists.newArrayList(new ApiKey("JWT", AUTHORIZATION_HEADER, "header")));
    }

    private SecurityContext securityContent() {
        return SecurityContext.builder()
            .securityReferences(defaultAuth())
            .forPaths(PathSelectors.regex("/admin/.*"))
            .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(new SecurityReference("JWT", authorizationScopes));
    }

    private ApiInfo informacoesAPI() {
        return new ApiInfoBuilder()
                .title("Clinica Sorrir API")
                .description("Essa API consiste em realizar o cadastro de clientes, agendamento, cancelamento de consultas e notificação de horários."+
                                      "\n Para fazer autenticação digite Bearer + o seu token.  Ex.: Bearer eyJhbGciOiJIUzUxMiJ9...")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .contact(new Contact("Turing Tecnologia", "http://turingtecnologia.com", "turing.tec.dev@gmail.com"))
                .build();
    }
}
