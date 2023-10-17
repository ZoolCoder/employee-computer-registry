package com.zoolcoder.employee.computer.registry.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(contact = @Contact(
		name = "Abdallah Emad",
		email = "abdallah.coder@gmail.com",
		url = "https://www.abdallah-coder.com"),
		description = "OpenApi documentation for managing employee computer",
		title = "Employee Device Registry API",
		version = "1.0",
		license = @License(
				name = "ZoolCoder Licence",
				url = "https://www.zoolcoder.com/en/info/privacy-policy"),
				termsOfService = "https://www.zoolcoder.com/en/info/terms-of-use"),
		servers = {
		@Server(description = "LOCAL Environment", url = "http://localhost:8080"),
		@Server(description = "PROD Environment", url = "https://zoolcoder.com/") })
public class OpenApiConfiguration {
}
