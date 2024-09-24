package com.fredrik_eiserman_wendt.ws_project_weather_web_service.controller;

import com.fredrik_eiserman_wendt.ws_project_weather_web_service.model.LocationModel;
import com.fredrik_eiserman_wendt.ws_project_weather_web_service.service.GeoCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/v1/geocode")
public class GeocodeController {
    
    
    GeoCodeService geoCodeService;
    
    @Autowired
    public GeocodeController(GeoCodeService geoCodeService) {
        this.geoCodeService = geoCodeService;
    }
    
    
    @GetMapping("/locations/{countrycode}/{city}")
    public Mono<ResponseEntity<List<LocationModel>>> fetchLocations(@PathVariable("countrycode") String countryCode,
                                                                    @PathVariable("city") String city,
                                                                    @RequestParam(value = "state", required = false) String state) {
        return geoCodeService.getLocation(countryCode, city, state)
                .map(ResponseEntity::ok)
                .onErrorResume(e -> {
                    // Log the error if needed
                    return Mono.just(ResponseEntity.notFound().build());
                });
    }
    
    
}
