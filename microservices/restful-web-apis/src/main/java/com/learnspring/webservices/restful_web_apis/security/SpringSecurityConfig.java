package com.learnspring.webservices.restful_web_apis.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // check for all requests to be authenticated
        http.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated()
        );

        // If request is not authenticated then show the login web page
        http.httpBasic(Customizer.withDefaults());

        // disable CSRF
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }
}