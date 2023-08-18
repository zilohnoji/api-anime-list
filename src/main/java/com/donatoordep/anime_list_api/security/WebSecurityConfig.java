package com.donatoordep.anime_list_api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private JWTAuthenticationFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // Desativando segurança CSRF
        http.csrf(AbstractHttpConfigurer::disable);

        // Habilitando o gerenciamento de sessão como STATELESS
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.authorizeHttpRequests(authorize -> {
            authorize.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();
        });

        // Configuração reservada para o endpoint /auth
        http.securityMatcher("/v1/auth/**", "/v1/users/**", "/v1/anime/**", "/v1/orders/**")
                .authorizeHttpRequests(authorize -> {
                    /* /auth */
                    authorize.requestMatchers(HttpMethod.POST, "/v1/auth/register").permitAll();
                    authorize.requestMatchers(HttpMethod.POST, "/v1/auth/login").permitAll();
                    /* /users */
                    authorize.requestMatchers(HttpMethod.GET, "/v1/users").authenticated();
                    authorize.requestMatchers(HttpMethod.GET, "/v1/users/me").authenticated();
                    authorize.requestMatchers(HttpMethod.GET, "/v1/users/my-cart").authenticated();
                    /* /anime */
                    authorize.requestMatchers(HttpMethod.POST, "/v1/anime").hasRole("ADMIN");
                    authorize.requestMatchers(HttpMethod.GET, "/v1/anime").permitAll();
                    authorize.requestMatchers(HttpMethod.GET, "/v1/anime/all").permitAll();
                    authorize.requestMatchers(HttpMethod.GET, "/v1/anime/{id}").permitAll();
                    /* /orders */
                    authorize.requestMatchers(HttpMethod.POST, "/v1/orders").authenticated();
                });

        http.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
        configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
