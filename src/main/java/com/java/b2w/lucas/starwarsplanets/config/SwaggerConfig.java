package com.java.b2w.lucas.starwarsplanets.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

@Configuration
public class SwaggerConfig
{
    Contact contact = new Contact("Lucas Oliveira Silva",
            "http://github.com/lucasolsi",
            "lucasolsi@gmail.com");

    ApiInfo apiInfo = new ApiInfo("Star Wars planets Doc",
            "REST API for creating and retrieving Star Wars planets. " +
                    "When creating a new planet, this API will communicate with SWAPI to retrieve number of films" +
                    " that this planet appeared.",
            "1.0", "", contact, "", "", new ArrayList<>());

    @Bean
    public Docket apiDocket()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                //.protocols(new HashSet<>(Collections.singletonList("HTTP")))
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.java.b2w.lucas.starwarsplanets"))
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}
