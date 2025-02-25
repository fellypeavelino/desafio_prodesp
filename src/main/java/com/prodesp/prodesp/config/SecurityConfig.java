/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prodesp.prodesp.config;

import com.prodesp.prodesp.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author Usuario
 */
@Configuration
public class SecurityConfig {
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
        String[] urlsPermitidas = {"/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html", "/token", "/usuarios/login"};
        
        http
            .csrf(csrf -> csrf.disable()) // Desabilita CSRF para APIs REST
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Sem sessões
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(urlsPermitidas).permitAll() // Permite acesso ao Swagger e login
                .requestMatchers("/auth/**").permitAll() // Permite autenticação
                .anyRequest().authenticated() // Todas as outras precisam de autenticação
            )
            .addFilterBefore(jwtUtil, UsernamePasswordAuthenticationFilter.class); // Adiciona o filtro JWT
        
        return http.build();
    }
}
