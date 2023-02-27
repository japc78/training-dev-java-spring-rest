package com.japcdev.userapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {
	@Bean
	public OpenAPI openApi() {
		return new OpenAPI()
				.info(new Info()
						.title("UserApp api")
						.description("Test open api")
						.version("v1.0")
						.contact(new Contact().name("JapcDev").url("https://japcdev.com")
								.email("japc.dev@gmail.com"))
						.termsOfService("MIT")
						.license(new License().name("License").url("#"))
				);
	}
}