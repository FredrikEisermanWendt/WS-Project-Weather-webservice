package com.fredrik_eiserman_wendt.ws_project_weather_web_service.service;

import com.fredrik_eiserman_wendt.ws_project_weather_web_service.controller.GeocodeController;
import com.fredrik_eiserman_wendt.ws_project_weather_web_service.model.LocationModel;
import com.fredrik_eiserman_wendt.ws_project_weather_web_service.model.Locator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class GeoCodeService {
    
    
    private final WebClient webClient;
    @Value("${geocode.api.key}")
    private String apiKey;
    private static final String BASE_GEOCODE_URL = "https://geocode.maps.co/search";
    
    @Autowired
    public GeoCodeService(WebClient.Builder webClientBuilder) {
       
        this.webClient = webClientBuilder
                .baseUrl(BASE_GEOCODE_URL)
                .build();
    }
    
    public LocationModel getCoordinateLocator(String countryCode, String city, String state){
        LocationModel location;
        List<LocationModel> locations= getLocation(countryCode, city, state).block();
        
        if (locations == null || locations.isEmpty()){
            return location = null;
        }
        
        return locations.getFirst();
    }
    
    
    public Mono<List<LocationModel>> getLocation(String countryCode, String city, String state){
        return webClient.get()
                .uri(tempLocation -> tempLocation
                        .queryParam("country", countryCode)
                        .queryParam("city", city)
                        .queryParam("state", state)
                        .queryParam("api_key", apiKey)
                        .build()
                )
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<LocationModel>>() {
                
                })
                .onErrorReturn(Collections.emptyList());
    }

    
}
