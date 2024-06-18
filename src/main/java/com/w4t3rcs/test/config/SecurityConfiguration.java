package com.w4t3rcs.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(authorizationManager -> authorizationManager.requestMatchers("/api/v1.0/*", "/api/v1.0/users/*", "api/v1.0/articles/*").permitAll()
                        .requestMatchers("/security").authenticated())
                .oauth2ResourceServer(resourceServerConfigurer -> resourceServerConfigurer.jwt(Customizer.withDefaults()))
                .build();
    }
}
