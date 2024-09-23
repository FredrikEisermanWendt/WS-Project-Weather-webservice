package com.fredrik_eiserman_wendt.ws_project_weather_web_service.controller;

import com.fredrik_eiserman_wendt.ws_project_weather_web_service.model.LocationModel;
import com.fredrik_eiserman_wendt.ws_project_weather_web_service.model.UserFavoriteLocation;
import com.fredrik_eiserman_wendt.ws_project_weather_web_service.model.WeatherModel;
import com.fredrik_eiserman_wendt.ws_project_weather_web_service.model.WeatherUriParam;
import com.fredrik_eiserman_wendt.ws_project_weather_web_service.service.GeoCodeService;
import com.fredrik_eiserman_wendt.ws_project_weather_web_service.service.UserService;
import com.fredrik_eiserman_wendt.ws_project_weather_web_service.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/v1/weather")
public class WeatherController {
    
    
    private final WebClient webClient;
    private final GeoCodeService geoCodeService;
    private static final String WEATHER_API_BASE_URL = "https://api.open-meteo.com/v1/forecast";
    private LocationModel chosenLocation;
    private UserController userController;
    private WeatherService weatherService;
    
    
    @Autowired
    public WeatherController(WebClient.Builder webClient, GeoCodeService geoCodeService, UserController userController, WeatherService weatherService) {
        this.webClient = webClient
                .baseUrl(WEATHER_API_BASE_URL)
                .build();
        
        this.geoCodeService = geoCodeService;
        this.userController = userController;
        this.weatherService = weatherService;
    }
    
    
    @GetMapping("/location/{countrycode}/{city}")
    public Mono<ResponseEntity<WeatherModel>> fetchNextWeekDefaultDaily(@PathVariable("countrycode") String countryCode,
                                                                        @PathVariable("city") String city,
                                                                        @RequestParam(value = "state", required = false) String state) {
        return weatherService.fetchNextWeekDefaultDaily(countryCode, city, state)
                .map(ResponseEntity::ok)
                .onErrorResume(e -> {
                    if (e instanceof InvalidParameterException) {
                        return Mono.just(ResponseEntity.badRequest().build());
                    } else {
                        return Mono.just(ResponseEntity.notFound().build());
                    }
                });
    }
    
    
    @GetMapping("/location/{countrycode}/{city}/forecast/date/{start_date}/{end_date}")
    public Mono<ResponseEntity<WeatherModel>> fetchPastDayWeather(@PathVariable("countrycode") String countryCode,
                                                                  @PathVariable("city") String city,
                                                                  @RequestParam(value = "state", required = false) String state,
                                                                  @PathVariable("start_date") String startDate,
                                                                  @PathVariable("end_date") String endDate) {
        
        return weatherService.fetchSpecificDatesWeather(countryCode, city, state, startDate, endDate)
                .map(ResponseEntity::ok)
                .onErrorResume(e -> {
                    if (e instanceof InvalidParameterException) {
                        return Mono.just(ResponseEntity.badRequest().build());
                    } else {
                        return Mono.just(ResponseEntity.notFound().build());
                    }
                });
        
    }
    
    
    @GetMapping("location/user_saved/{id}")
    public Mono<ResponseEntity<List<WeatherModel>>> fetchUserFavoriteWeather(@PathVariable("id") String id) {
        List<Mono<WeatherModel>> monoWeatherList = weatherService.fetchWeatherForFavoriteLocations(id);
        return Flux.merge(monoWeatherList)
                .collectList()
                .map(ResponseEntity::ok)
                .onErrorResume(e -> {
                    if (e instanceof InvalidParameterException) {
                        return Mono.just(ResponseEntity.badRequest().build());
                    } else {
                        return Mono.just(ResponseEntity.notFound().build());
                    }
                });
        
    }
    
    
}
