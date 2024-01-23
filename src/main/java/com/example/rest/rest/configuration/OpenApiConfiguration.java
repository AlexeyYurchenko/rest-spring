package com.example.rest.rest.configuration;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI openApiDescription() {
        Server localhostServer = new Server();
        localhostServer.setUrl("http://localhost:8080");
        localhostServer.setDescription("Local env");

        Server productionServer = new Server();
        productionServer.setUrl("http://some.prod.url");
        localhostServer.setDescription("Production env");

        Contact contact = new Contact();
        contact.setName("Ivan Ivanov");
        contact.setEmail("ivan@mail.ru");
        contact.setUrl("http://some.url");

        License nitLicense = new License().name("GNU AGPLv3")
                .url("https://choosealincense.com/license/agpl-3.0/");

        Info info = new Info()
                .title("Client orders API")
                .version("1.0")
                .contact(contact)
                .description("API for orders")
                .termsOfService("http://some.url")
                .license(nitLicense);

        return new OpenAPI().info(info).servers(List.of(localhostServer,productionServer));
    }
}
