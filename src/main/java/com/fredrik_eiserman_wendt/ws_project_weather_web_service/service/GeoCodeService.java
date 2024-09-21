package com.fredrik_eiserman_wendt.ws_project_weather_web_service.service;

import com.fredrik_eiserman_wendt.ws_project_weather_web_service.controller.GeocodeController;
import com.fredrik_eiserman_wendt.ws_project_weather_web_service.model.LocationModel;
import com.fredrik_eiserman_wendt.ws_project_weather_web_service.model.Locator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
public class GeoCodeService {
    
    GeocodeController geocodeController;
    
    @Autowired
    public GeoCodeService(GeocodeController geocodeController) {
        this.geocodeController = geocodeController;
    }
    
    public LocationModel getLocator(String countryCode, String city, String state){
        LocationModel location;
        List<LocationModel> locations= geocodeController.fetchLocation(countryCode, city, state).block();
        
        if (locations.isEmpty()){
            return location = null;
        }
        
        return locations.getFirst();
    }
    
    
}
