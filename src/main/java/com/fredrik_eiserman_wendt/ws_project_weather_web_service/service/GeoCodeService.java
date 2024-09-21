package com.fredrik_eiserman_wendt.ws_project_weather_web_service.service;

import com.fredrik_eiserman_wendt.ws_project_weather_web_service.controller.GeocodeController;
import com.fredrik_eiserman_wendt.ws_project_weather_web_service.model.Locator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeoCodeService {
    
    GeocodeController geocodeController;
    
    @Autowired
    public GeoCodeService(GeocodeController geocodeController) {
        this.geocodeController = geocodeController;
    }
    
    public Locator getLocator(){
    
    }
    
    
}
