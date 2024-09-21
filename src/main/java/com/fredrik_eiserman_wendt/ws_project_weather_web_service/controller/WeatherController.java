package com.fredrik_eiserman_wendt.ws_project_weather_web_service.controller;

import com.fredrik_eiserman_wendt.ws_project_weather_web_service.model.LocationModel;
import com.fredrik_eiserman_wendt.ws_project_weather_web_service.model.WeatherModel;
import com.fredrik_eiserman_wendt.ws_project_weather_web_service.service.GeoCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/weather")
public class WeatherController {
    
    
    private WebClient webClient;
    private GeoCodeService geoCodeService;
    private static String WEATHER_API_BASE_URL = "https://api.open-meteo.com/v1/forecast";
    private LocationModel locationModel;
    
    
    @Autowired
    public WeatherController(WebClient.Builder webClient,  GeoCodeService geoCodeService) {
        this.webClient = webClient
                .baseUrl(WEATHER_API_BASE_URL)
                .build();
       
        this.geoCodeService = geoCodeService;
    }
    
    
    /*
     * när man anropar */
//    todo; change from block
    @GetMapping
    public Mono<WeatherModel> fetchLocalWeather(@RequestParam String countryCode, @RequestParam String city, @RequestParam String state) {
        locationModel = geoCodeService.getLocator(countryCode, city, state);
        return webClient.get()
                .uri(weather -> weather
                        .queryParam("latitude", locationModel.getLat())
                        .queryParam("longitude", locationModel.getLon())
                        .queryParam("hourly", "temperature_2m")
                        .build()
                
                )
                .retrieve()
                .bodyToMono(WeatherModel.class);
    }
    
    
}
