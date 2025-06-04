package com.estoque.gerenciador.management.easy.easymanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(configurer ->
                        configurer.loginPage("/login").permitAll()) // Formulario de login web
                //.formLogin(configurer -> configurer.loginPage("/login.html").successForwardUrl("/home.html"))
                .httpBasic(Customizer.withDefaults())// Aplicação pra aplicação
                .authorizeHttpRequests(autorize ->
                        autorize.anyRequest().authenticated()) //tem q estar autenticado para requisições
                .build();
    }
}
