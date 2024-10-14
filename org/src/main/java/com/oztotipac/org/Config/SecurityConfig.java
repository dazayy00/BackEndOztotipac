package com.oztotipac.org.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desactiva CSRF solo si no se necesita
                .cors(Customizer.withDefaults()) // Habilita la configuración de CORS definida
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(HttpMethod.POST, "/oztotipac/auth/login", "/oztotipac/user/register").permitAll() // Permitir solo rutas específicas para POST sin autenticación
                        .anyRequest().authenticated()) // Exigir autenticación para todas las demás rutas
                .httpBasic(Customizer.withDefaults()); // Habilita autenticación HTTP básica
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

