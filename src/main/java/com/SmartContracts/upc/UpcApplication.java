package com.SmartContracts.upc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class UpcApplication {

	public static void main(String[] args) {
		SpringApplication.run(UpcApplication.class, args);
	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/smartcontract/v1/**") // Aplica a todas las rutas
						.allowedOrigins("*") // URL de tu frontend
						.allowedMethods("*")
						.allowedHeaders("*");
			}
		};
	}
}
