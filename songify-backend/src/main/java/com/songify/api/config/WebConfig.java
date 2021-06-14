package com.songify.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableJpaAuditing
public class WebConfig implements WebMvcConfigurer {

//    @Override
//    public void addCorsMappings(CorsRegistry registry){
//        final long MAX_AGE_SECS = 360000; //how long the results of a preflight request can be cached
//
//        registry.addMapping("/**")
//                .allowedOrigins("http://localhost:3000", "http:localhost:8080")
//                .allowedMethods("GET", "POST", "PUT", "DELETE")
//                .allowedHeaders("Access-Control-Allow-Origin", "*");
//
////                .maxAge(MAX_AGE_SECS);
//    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
