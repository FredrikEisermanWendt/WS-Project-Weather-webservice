package com.fredrik_eiserman_wendt.ws_project_weather_web_service.controller;

import com.fredrik_eiserman_wendt.ws_project_weather_web_service.model.LocationModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/v1/location")
public class GeocodeController {

//https://geocode.maps.co/search?q=555+5th+Ave+New+York+NY+10017+US&api_key=api_key

//    https://geocode.maps.co/search?city=Stockholm&country=se&county=Stockholm+County&api_key=66ec15307c9a0191546411hdz9e4ea4

//    https://geocode.maps.co/search?city=Stockholm&country=se&state=stockholm+kommun&api_key=66ec15307c9a0191546411hdz9e4ea4

//    https://geocode.maps.co/search?city=Stockholm&country=se&state=solna+kommun&api_key=66ec15307c9a0191546411hdz9e4ea4
    
    /*
    * för att söka på stad krävs
    * country=<landskod>
    * city=<ort>
    * state=<kommun>
    */
    
    private WebClient webClient;
    @Value("${geocode.api.key}")
    private String apiKey;
    private static final String BASE_GEOCODE_URL = "https://geocode.maps.co/search";
    
    
    public GeocodeController(WebClient.Builder webClient) {
        this.webClient = webClient
                .baseUrl(BASE_GEOCODE_URL)
                .build();
    }
    
    
    @GetMapping
    public Mono<List<LocationModel>> fetchLocation(String countryCode, String city, String state){
        return webClient.get()
                .uri(location -> location
                        .queryParam("country", countryCode)
                        .queryParam("city", city)
                        .queryParam("state", state)
                        .queryParam("api_key", apiKey)
                        .build()
                )
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<LocationModel>>() {});
    }
    
    

}
