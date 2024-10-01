package com.smart_ski_rent_ver1_2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // umożliwia CORS dla wszystkich endpointów
                .allowedOrigins("http://localhost:3000") // zmień na swój frontendowy adres (port, domena)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // metody HTTP
                .allowedHeaders("*"); // wszystkie nagłówki
    }
}
