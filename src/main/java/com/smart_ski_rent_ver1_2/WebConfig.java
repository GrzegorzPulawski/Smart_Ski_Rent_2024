package com.smart_ski_rent_ver1_2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // umożliwia CORS dla wszystkich endpointów
                .allowedOrigins(
                        "http://localhost:8080",
                        "http://localhost:3000", // lokalny frontend
                        "https://smart-ski-rent-01-35db1f76586e.herokuapp.com" // adres produkcyjny
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // metody HTTP
                .allowedHeaders("*"); // wszystkie nagłówki


    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
    }
}
