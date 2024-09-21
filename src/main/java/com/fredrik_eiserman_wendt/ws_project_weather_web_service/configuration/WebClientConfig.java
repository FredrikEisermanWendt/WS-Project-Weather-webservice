package com.fredrik_eiserman_wendt.ws_project_weather_web_service.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WebClientConfig {
    
    
    @Bean
    public WebClient.Builder WebClientBuilder(){
        return WebClient.builder();
    }
    
}
