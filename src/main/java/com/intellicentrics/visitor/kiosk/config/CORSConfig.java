package com.intellicentrics.visitor.kiosk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Configuration
public class CORSConfig {

    @Bean
    public WebMvcConfigurerAdapter corsConfigurer() {
        return new WebMvcConfigurerAdapter() {	
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                    .allowedMethods(HttpMethod.GET.name(),
                            HttpMethod.PUT.name(),
                            HttpMethod.POST.name(),
                            HttpMethod.DELETE.name(),
                            HttpMethod.OPTIONS.name())
                    .allowedOrigins("http://localhost:8080", "http://localhost:4200")
                    .allowedHeaders("Content-Type");
            }
        };
    }

}
