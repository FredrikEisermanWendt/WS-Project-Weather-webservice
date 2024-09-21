package com.fredrik_eiserman_wendt.ws_project_weather_web_service.controller;

import com.fredrik_eiserman_wendt.ws_project_weather_web_service.model.LocationModel;
import com.fredrik_eiserman_wendt.ws_project_weather_web_service.model.WeatherModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/v1/weather")
public class WeatherController {
    
    
    private WebClient webClient;
    private GeocodeController geocodeController;
    private static String WEATHER_API_BASE_URL = "https://api.open-meteo.com/v1/forecast";
    private LocationModel locationModel;
    
    
    @Autowired
    public WeatherController(WebClient.Builder webClient, GeocodeController geocodeController) {
        this.webClient = webClient
                .baseUrl(WEATHER_API_BASE_URL)
                .build();
        this.geocodeController = geocodeController;
    }
    
    
    /*
     * när man anropar */
//    todo; change from block
    @GetMapping
    public Mono<ResponseEntity<WeatherModel>> fetchLocalWeather(@RequestParam String countryCode, @RequestParam String city, @RequestParam String state) {
        try {
            locationModel = geocodeController.fetchLocation(countryCode, city, state).block().getFirst();
        }catch (Exception e){
            return Mono.just(ResponseEntity.notFound().build());
        }
        
        
        
        
    }
    
    
    @GetMapping
    public Mono<WeatherModel> fetchLocal(@RequestParam String countryCode, @RequestParam String city, @RequestParam String state) {
        return geocodeController.fetchLocation(countryCode, city, state)
                .flatMap(locationList -> {
                    if (locationList.isEmpty()) {
                        return Mono.error(new RuntimeException("Location not found"));
                    }
                    
                    // Assuming you want the first location in the list
                    LocationModel location = locationList.get(0);
                    
                    // Construct the weather API URL based on location data
                    String weatherUrl = String.format(
                            "https://api.open-meteo.com/v1/forecast?latitude=%f&longitude=%f&hourly=temperature_2m",
                            location.getLat(), location.getLon()
                    );
                    
                    return webClient.get()
                            .uri(weatherUrl)
                            .retrieve()
                            .bodyToMono(WeatherModel.class)
                            .onErrorResume(e -> {
                                // Handle any errors from the weather API call here
                                return Mono.error(new RuntimeException("Failed to fetch weather data", e));
                            });
                })
                .onErrorResume(e -> {
                    // Handle errors from the location API call here
                    return Mono.error(new RuntimeException("Failed to fetch location data", e));
                });
    }
    
    
}
