package com.fredrik_eiserman_wendt.ws_project_weather_web_service.controller;

import com.fredrik_eiserman_wendt.ws_project_weather_web_service.model.LocationModel;
import com.fredrik_eiserman_wendt.ws_project_weather_web_service.model.WeatherModel;
import com.fredrik_eiserman_wendt.ws_project_weather_web_service.model.WeatherUriParam;
import com.fredrik_eiserman_wendt.ws_project_weather_web_service.service.GeoCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.HashMap;

@RestController
@RequestMapping("/v1/weather")
public class WeatherController {
    
    
    private final WebClient webClient;
    private final GeoCodeService geoCodeService;
    private static final String WEATHER_API_BASE_URL = "https://api.open-meteo.com/v1/forecast";
    private LocationModel chosenLocation;
    
    
    @Autowired
    public WeatherController(WebClient.Builder webClient, GeoCodeService geoCodeService) {
        this.webClient = webClient
                .baseUrl(WEATHER_API_BASE_URL)
                .build();
        
        this.geoCodeService = geoCodeService;
    }
    
//    Get method for location, to be used with other Gets to get the right coordinates
    @GetMapping("/location/{country_code}/{city}")
    public ResponseEntity<LocationModel> fetchLocation(@PathVariable("country_code") String countryCode, @PathVariable("city") String city, @RequestParam(value = "state", required = false) String state) {
        chosenLocation = geoCodeService.getLocator(countryCode, city, state);
        if (chosenLocation == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(chosenLocation);
    }
    
    
    // TODO: 2024-09-22 Why 400 call when missing param, set to Pathvariable
//    Depricated Method, to be removed
    @GetMapping()
    public Mono<ResponseEntity<WeatherModel>> fetchLocalWeather(@RequestParam String countrycode, @RequestParam String city, @RequestParam String state) {
        return Mono.justOrEmpty(geoCodeService.getLocator(countrycode, city, state))
                .flatMap(locationModel -> webClient.get()
                        .uri(uriBuilder -> uriBuilder
                                .queryParam(WeatherUriParam.LATITUDE.uriParam, locationModel.getLat())
                                .queryParam(WeatherUriParam.LONGITUDE.uriParam, locationModel.getLon())
                                .queryParam("hourly", "temperature_2m,rain")
                                .build())
                        .retrieve()
                        .bodyToMono(WeatherModel.class)
                        .map(weatherModel -> ResponseEntity.ok(weatherModel))
                        .onErrorReturn(ResponseEntity.status(404).build())
                
                )
                .switchIfEmpty(Mono.just(ResponseEntity.status(404).build()));
    }
    
    
    // TODO: 2024-09-22 fix problem with hourly params
    //    &start_date=2024-05-15&end_date=2024-05-16
    @GetMapping("/location/{country_code}/{city}/forecast/date/{start_date}/{end_date}")
    public Mono<ResponseEntity<WeatherModel>> fetchPastDayWeather(@PathVariable("country_code") String countryCode,
                                                                  @PathVariable("city") String city,
                                                                  @RequestParam(value = "state", required = false) String state,
                                                                  @PathVariable("start_date") String startDate,
                                                                  @PathVariable("end_date") String endDate) {
        if (!fetchLocation(countryCode, city, state).getStatusCode().is2xxSuccessful()) {
            return Mono.just(ResponseEntity.noContent().build());
        }
        
        String uri = UriComponentsBuilder.fromUriString("https://api.open-meteo.com/v1/forecast")
                .queryParam(WeatherUriParam.LATITUDE.uriParam, chosenLocation.getLat())
                .queryParam(WeatherUriParam.LONGITUDE.uriParam, chosenLocation.getLon())
                .queryParam("hourly", "temperature_2m")  // Single hourly parameter
                .queryParam("start_date", startDate)
                .queryParam("end_date", endDate)
                .build()
                .toUriString();
        
        // Log the constructed URI
        System.out.println("Constructed URI: " + uri);
        
        
        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(WeatherModel.class)
                .map(weatherModel -> {
                    System.out.println(weatherModel);
                    return ResponseEntity.ok(weatherModel);
                })
                .onErrorReturn(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        
        
    }

//    https://api.open-meteo.com/v1/forecast?latitude=59.3294&longitude=18.0687&hourly=temperature_2m,relative_humidity_2m,apparent_temperature,precipitation,wind_speed_10m,wind_direction_10m&daily=temperature_2m_max,temperature_2m_min,daylight_duration,precipitation_sum,precipitation_hours,wind_speed_10m_max,wind_direction_10m_dominant&start_date=2024-05-15&end_date=2024-05-16
//    get hourly Weather from historic day
//    get daily weather for coming 7 days
//    get daliy weather for favorite location 7 days
//
    
    
}
