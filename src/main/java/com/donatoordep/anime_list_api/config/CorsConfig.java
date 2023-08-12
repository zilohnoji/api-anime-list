package com.donatoordep.anime_list_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // Permitir qualquer origem
        config.addAllowedOrigin("*");

        // Permitir todos os métodos HTTP (GET, POST, PUT, etc.)
        config.addAllowedMethod("*");

        // Permitir todos os headers
        config.addAllowedHeader("*");

        // Expor alguns headers, se necessário
        config.addExposedHeader("header1");
        config.addExposedHeader("header2");

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
