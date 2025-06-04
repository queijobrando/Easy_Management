package com.estoque.gerenciador.management.easy.easymanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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
                .authorizeHttpRequests(autorize -> {

                    autorize.requestMatchers(HttpMethod.POST, "/produtos/**").hasRole("ADMIN");
                    autorize.requestMatchers(HttpMethod.DELETE, "/produtos/**").hasRole("ADMIN");
                    autorize.requestMatchers(HttpMethod.GET, "/produtos/**").hasAnyRole("ADMIN", "USER");

                    autorize.requestMatchers(HttpMethod.POST, "/categorias/**").hasRole("ADMIN");
                    autorize.requestMatchers(HttpMethod.DELETE, "/categorias/**").hasRole("ADMIN");
                    autorize.requestMatchers(HttpMethod.GET, "/categorias/**").hasAnyRole("ADMIN", "USER");

                    autorize.requestMatchers("/lotes/**").hasAnyRole("ADMIN", "USER");
                    autorize.requestMatchers("/movimentacao/**").hasAnyRole("ADMIN", "USER");

                    autorize.anyRequest().authenticated();

                })
                .build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder){

        UserDetails user1 = User.builder()
                .username("usuario")
                .password(encoder.encode("123"))
                .roles("USER")
                .build();

        UserDetails user2 = User.builder()
                .username("admin")
                .password(encoder.encode("321"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user1, user2);
    }
}
