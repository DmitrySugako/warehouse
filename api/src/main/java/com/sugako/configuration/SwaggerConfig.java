package com.sugako.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "WMS Project",
                version = "1.0",
                description = "Address storage",
                contact = @Contact(
                        name = "Dmitry Sugako",
                        email = "dd.sugako@gmail.com"
                ),
                license = @License(
                        name = "MIT Licence",
                        url = "https://opensource.org/licenses/mit-license.php"
                )
        )
)

public class SwaggerConfig {
    public OpenAPI customOpenAPI() {
        return new OpenAPI();
    }
}
