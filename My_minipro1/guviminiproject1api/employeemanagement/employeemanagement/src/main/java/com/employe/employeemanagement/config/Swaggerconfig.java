package com.employe.employeemanagement.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class Swaggerconfig {

    @Bean
    public OpenAPI customopenapi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Employee webflux Reactive APIs")
                        .version("1.0")
                        .description("Reactive Programming APIs")
                        .license(new License().name("Licensed...1.0")));
    }
}
