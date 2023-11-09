package com.harshit.yourcartbackend.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.AuthenticationFilter;

@Configuration
public class WebSecurityConfig {
    private JWTRequestFilter jwtRequestFilter;

    public WebSecurityConfig(JWTRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().disable().csrf().disable();
//        to apply manual filter that is to be executed
        http.addFilterBefore(jwtRequestFilter, AuthorizationFilter.class);

        http.authorizeHttpRequests()
                .requestMatchers("/product", "auth/register", "auth/login").permitAll()
                .anyRequest().authenticated();

        return http.build();
    }
}
