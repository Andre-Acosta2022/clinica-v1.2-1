package com.clinic.client_service.configuration;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
public class FeignConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> {
            Object credentials = SecurityContextHolder.getContext().getAuthentication().getCredentials();
            if (credentials instanceof String) {
                template.header("Authorization", "Bearer " + credentials);
            }
        };
    }
}
