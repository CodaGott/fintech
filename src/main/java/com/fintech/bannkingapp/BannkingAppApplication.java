package com.fintech.bannkingapp;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Fintech Project",
				description = "Backend Rest API for a Bank",
				version = "v1.0",
				contact = @Contact(
						name = "Chukwuebuka",
						email = "cuyanwune@outlook.com",
						url = "www.github.com/codagott"
				),
				license = @License(
						name = "Coda Gott",
						url = "www.github.com/codagott"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Java Backend Fintech",
				url = "www.github.com/codagott"
		)
)
public class BannkingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BannkingAppApplication.class, args);
	}

}
