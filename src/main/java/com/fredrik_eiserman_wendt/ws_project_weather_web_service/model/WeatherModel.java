package com.fredrik_eiserman_wendt.ws_project_weather_web_service.model;

import java.util.List;

public class WeatherModel {
    
    private double latitude;
    private double longitude;
    private String generationtime_ms;
    private String utc_offset_seconds;
    private String timezone;
    private String timezone_abbreviation;
    private String elevation;
    private HourlyUnits hourlyUnits;
    private Hourly hourly;
    
    
    public double getLatitude() {
        return latitude;
    }
    
    
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    
    
    public double getLongitude() {
        return longitude;
    }
    
    
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    
    
    public String getGenerationtime_ms() {
        return generationtime_ms;
    }
    
    
    public void setGenerationtime_ms(String generationtime_ms) {
        this.generationtime_ms = generationtime_ms;
    }
    
    
    public String getUtc_offset_seconds() {
        return utc_offset_seconds;
    }
    
    
    public void setUtc_offset_seconds(String utc_offset_seconds) {
        this.utc_offset_seconds = utc_offset_seconds;
    }
    
    
    public String getTimezone() {
        return timezone;
    }
    
    
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
    
    
    public String getTimezone_abbreviation() {
        return timezone_abbreviation;
    }
    
    
    public void setTimezone_abbreviation(String timezone_abbreviation) {
        this.timezone_abbreviation = timezone_abbreviation;
    }
    
    
    public String getElevation() {
        return elevation;
    }
    
    
    public void setElevation(String elevation) {
        this.elevation = elevation;
    }
    
    
    public HourlyUnits getHourlyUnits() {
        return hourlyUnits;
    }
    
    
    public void setHourlyUnits(HourlyUnits hourlyUnits) {
        this.hourlyUnits = hourlyUnits;
    }
    
    
    public Hourly getHourly() {
        return hourly;
    }
    
    
    public void setHourly(Hourly hourly) {
        this.hourly = hourly;
    }
    
    
    // TODO: 2024-09-21 add more units
    public class HourlyUnits {
        private String time;
        private String temperature_2m;
        
        
        public String getTime() {
            return time;
        }
        
        
        public void setTime(String time) {
            this.time = time;
        }
        
        
        public String getTemperature_2m() {
            return temperature_2m;
        }
        
        
        public void setTemperature_2m(String temperature_2m) {
            this.temperature_2m = temperature_2m;
        }
        
        
    }
    
    
    public class Hourly {
        private List<String> time;
        private List<String> temperature_2m;
        
        
        public List<String> getTime() {
            return time;
        }
        
        
        public void setTime(List<String> time) {
            this.time = time;
        }
        
        
        public List<String> getTemperature_2m() {
            return temperature_2m;
        }
        
        
        public void setTemperature_2m(List<String> temperature_2m) {
            this.temperature_2m = temperature_2m;
        }
        
        
    }
    
    
}
