package ru.clevertec.vasili.urusov.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@OpenAPIDefinition
public class SwaggerConfig {


    @Bean
    public OpenAPI springApplicationOpenAPI() {
        var info = new Info()
                .title("API. Clevertec")
                .version("1.0.0")
                .description("## Test task: clevertec\n"
                        + " - [Task](https://clevertec.ru/)\n ");

        return new OpenAPI()
                .info(info);
    }

}
