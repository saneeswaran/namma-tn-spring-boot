package com.app.tn.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ApiConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authz -> authz.requestMatchers("/api/v1/post/**").permitAll()
                        .requestMatchers("/comments/{postId}").permitAll()
                        .requestMatchers("/child/{parentCommentId}").permitAll()
                        .requestMatchers("/comment/**").permitAll().anyRequest().authenticated());

        return http.build();
    }
}
