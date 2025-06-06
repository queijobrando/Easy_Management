package com.estoque.gerenciador.management.easy.easymanagement.config.security;

import com.estoque.gerenciador.management.easy.easymanagement.service.UsuarioService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())// Aplicação pra aplicação
                .formLogin(configurer ->
                        configurer.loginPage("/login").defaultSuccessUrl("/home", true).permitAll()) // Formulario de login web
                .authorizeHttpRequests(autorize -> {

                    autorize.requestMatchers(HttpMethod.POST, "/api/produtos/**").hasRole("ADMIN");
                    autorize.requestMatchers(HttpMethod.DELETE, "/api/produtos/**").hasRole("ADMIN");
                    autorize.requestMatchers(HttpMethod.GET, "/api/produtos/**").hasAnyRole("ADMIN", "USER");

                    autorize.requestMatchers(HttpMethod.POST, "/categorias/**").hasRole("ADMIN");
                    autorize.requestMatchers(HttpMethod.DELETE, "/categorias/**").hasRole("ADMIN");
                    autorize.requestMatchers(HttpMethod.GET, "/categorias/**").hasAnyRole("ADMIN", "USER");

                    autorize.requestMatchers(HttpMethod.POST, "/usuarios/**").permitAll();
                    autorize.requestMatchers("/css/**").permitAll();

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
    public UserDetailsService userDetailsService(UsuarioService service){

       /* UserDetails user1 = User.builder()
                .username("usuario")
                .password(encoder.encode("123"))
                .roles("USER")
                .build();

        UserDetails user2 = User.builder()
                .username("admin")
                .password(encoder.encode("321"))
                .roles("ADMIN")
                .build();
        */
        return new CustomUserDetailsService(service);
    }
}
