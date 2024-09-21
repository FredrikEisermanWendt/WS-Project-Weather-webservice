package com.fredrik_eiserman_wendt.ws_project_weather_web_service.model;

public class Locator {
    private String description;
    private double lon;
    private double lat;
    
    
    public Locator(String description, double lon, double lat) {
        this.description = description;
        this.lon = lon;
        this.lat = lat;
    }
    
    
    public String getDescription() {
        return description;
    }
    
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    
    public double getLon() {
        return lon;
    }
    
    
    public void setLon(double lon) {
        this.lon = lon;
    }
    
    
    public double getLat() {
        return lat;
    }
    
    
    public void setLat(double lat) {
        this.lat = lat;
    }
    
    
}
