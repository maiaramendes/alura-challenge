package br.alura.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashSet;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private Contact createContact() {
        return new Contact("Maiara Mendes", "maiarampereira@live.com", "maiaramendes.dev");
    }

    private ApiInfoBuilder apiInfo() {
        final ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
        apiInfoBuilder.title("#5 Alura Challenge");
        apiInfoBuilder.description("Project to develop alura's challenge! #alurachallengebackend5");
        apiInfoBuilder.version("1.0");
        apiInfoBuilder.termsOfServiceUrl("www.maiaramendes.dev");
        apiInfoBuilder.licenseUrl("maiarampereira@live.com");
        apiInfoBuilder.contact(createContact());
        return apiInfoBuilder;
    }

    @Bean
    public Docket api() {
        final Docket docket = new Docket(DocumentationType.SWAGGER_2);
        docket.select()
                .apis(RequestHandlerSelectors.basePackage("br.alura.controller"))
                .build()
                .pathMapping("/swagger")
                .apiInfo(this.apiInfo().build())
                .consumes(new HashSet<>(Arrays.asList("application/json")))
                .produces(new HashSet<>(Arrays.asList("application/json")));
        return docket;
    }
}
