package com.vmvalle.productsapp.infrastructure.apirest.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Product Service API")
                        .description("Products API example application")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Víctor Manuel Valle")
                                .url("https://www.linkedin.com/in/v%C3%ADctor-manuel-valle-sanz-71295267/")
                                .email("vmvallesanz@gmail.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")));
    }

}