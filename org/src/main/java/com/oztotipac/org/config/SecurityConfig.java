package com.oztotipac.org.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.oztotipac.org.jwt.JwtAutenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationProvider authProvider;
    private final JwtAutenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf ->
                  csrf
                  .disable()) //desactiva CSRF ya que se usa JWT
                .authorizeHttpRequests(authRequest ->
                   authRequest
                     .requestMatchers("/auth/**").permitAll() //open routes
                     .requestMatchers("/admin/**").hasRole("ADMIN") //admin access
                     .requestMatchers("/supervisor/**").hasRole("SUP") //supervisor access
                     .requestMatchers("/client/**").hasRole("CLIENT") //clien access
                     .anyRequest().authenticated() //routes with authentication
                )
                .sessionManagement(sessionManager->
                        sessionManager
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
