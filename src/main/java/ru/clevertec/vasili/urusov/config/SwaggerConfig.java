package ru.clevertec.vasili.urusov.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {


    @Bean
    public OpenAPI springApplicationOpenAPI() {
        var info = new Info()
                .title("API. Clevertec")
                .description("Test task: clevertec"
                        + " - [Task](https://clevertec.ru/)\n ");

        return new OpenAPI()
                .info(info);
    }

}
