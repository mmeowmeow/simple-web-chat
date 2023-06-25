package com.example.chat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/h2/**")).permitAll()
                        .requestMatchers("/ws/**").permitAll()
                        .anyRequest().permitAll()
                )
                .formLogin(withDefaults())
                .httpBasic(withDefaults())
                .headers(headers -> headers.frameOptions().disable())
                .cors().disable()
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2/**"))
                        .ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/users/**"))
                        .ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/app/**"))
                        .ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/stomp/**"))
                        .ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/chat/**"))
                        .ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/topic/**"))
                        .ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/ws/**"))
                        .ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/employees/**")));
        return http.build();
    }

}
