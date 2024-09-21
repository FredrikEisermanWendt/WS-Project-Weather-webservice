package com.fredrik_eiserman_wendt.ws_project_weather_web_service.model;

public class Locator {
    private String description;
    private String lon;
    private String lat;
    
    
    public Locator(String description, String  lon, String lat) {
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
    
    
    public String getLon() {
        return lon;
    }
    
    
    public void setLon(String lon) {
        this.lon = lon;
    }
    
    
    public String getLat() {
        return lat;
    }
    
    
    public void setLat(String lat) {
        this.lat = lat;
    }
    
    
}
