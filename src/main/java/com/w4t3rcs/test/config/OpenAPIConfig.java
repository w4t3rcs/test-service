package com.w4t3rcs.test.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(servers = {
        @Server(url = "http://localhost:8080", description = "Main server of the app")
}, info = @Info(title = "Test service documentation", version = "1.0"))
@Configuration
public class OpenAPIConfig {
}
