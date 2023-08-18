package com.donatoordep.anime_list_api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfigurer implements WebMvcConfigurer {

    public void addCorsMappint(CorsRegistry registry){
        registry.addMapping("/**");
    }
}
