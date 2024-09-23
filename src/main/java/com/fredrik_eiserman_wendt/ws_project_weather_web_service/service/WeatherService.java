package com.fredrik_eiserman_wendt.ws_project_weather_web_service.service;

import com.fredrik_eiserman_wendt.ws_project_weather_web_service.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WeatherService {
    
    private GeoCodeService geoCodeService;
    private UserService userService;
    private WebClient webClient;
    private static final String WEATHER_API_BASE_URL = "https://api.open-meteo.com/v1/forecast";
    
    
    public WeatherService(GeoCodeService geoCodeService, WebClient.Builder webClientBuilder, UserService userService) {
        this.geoCodeService = geoCodeService;
        this.webClient = webClientBuilder
                .baseUrl(WEATHER_API_BASE_URL)
                .build();
        this.userService = userService;
    }
    
    
    public LocationModel fetchLocation(String countryCode, String city, String state) {
        return geoCodeService.getCoordinateLocator(countryCode, city, state);
    }
    
    
    public Mono<WeatherModel> fetchNextWeekDefaultDaily(String countryCode, String city, String state) {
        
        LocationModel locationModel = fetchLocation(countryCode, city, state);
        
        if (locationModel == null) {
            return Mono.error(new InvalidParameterException());
        }
        
        return webClient.get()
                .uri(forecast -> forecast
                        .queryParam(WeatherUriParam.LATITUDE.uriParam, locationModel.getLat())
                        .queryParam(WeatherUriParam.LONGITUDE.uriParam, locationModel.getLon())
                        .queryParam(WeatherUriParam.DAILY.uriParam, WeatherUriParam.DAILY_DEFAULT_WEATHER.uriParam)
                        .build()
                )
                .retrieve()
                .bodyToMono(WeatherModel.class);
    }
    
    
    public Mono<WeatherModel> fetchSpecificDatesWeather(String countryCode, String city, String state, String startDate, String endDate) {
        
        
        LocationModel locationModel = fetchLocation(countryCode, city, state);
        
        if (locationModel == null) {
            return Mono.error(new InvalidParameterException());
        }
        
        return webClient.get()
                .uri(forecast -> forecast
                        .queryParam(WeatherUriParam.LATITUDE.uriParam, locationModel.getLat())
                        .queryParam(WeatherUriParam.LONGITUDE.uriParam, locationModel.getLon())
                        .queryParam(WeatherUriParam.HOURLY.uriParam, WeatherUriParam.HOURLY_DEFAULT_WEATHER.uriParam)
                        .queryParam(WeatherUriParam.DAILY.uriParam, WeatherUriParam.DAILY_DEFAULT_WEATHER.uriParam)
                        .queryParam("start_date", startDate)
                        .queryParam("end_date", endDate)
                        .build()
                )
                .retrieve()
                .bodyToMono(WeatherModel.class);
    }
    
    
    public List<Mono<WeatherModel>> fetchWeatherForFavoriteLocations(String id) {
        List<Mono<WeatherModel>> monoList = new ArrayList<>();
        User user = userService.findById(Long.parseLong(id)).orElseThrow(() -> new InvalidParameterException());
        
        if (user.getFavoriteLocations().isEmpty()) {
            return monoList;
        }
        
        for (UserFavoriteLocation location : user.getFavoriteLocations()) {
            monoList.add(
                    webClient.get()
                            .uri(forecast -> forecast
                                    .queryParam(WeatherUriParam.LATITUDE.uriParam, location.getLatitude())
                                    .queryParam(WeatherUriParam.LONGITUDE.uriParam, location.getLongitude())
                                    .queryParam(WeatherUriParam.DAILY.uriParam, WeatherUriParam.DAILY_DEFAULT_WEATHER.uriParam)
                                    .build()
                            )
                            .retrieve()
                            .bodyToMono(WeatherModel.class)
            );
        }
        
        return monoList;
        
    }
    
    
}
