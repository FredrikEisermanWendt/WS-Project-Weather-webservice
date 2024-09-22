package com.fredrik_eiserman_wendt.ws_project_weather_web_service.model;

public enum WeatherUriParam {
    
    
    LATITUDE("latitude"),
    LONGITUDE("longitude"),
    DAILY("daily"),
    HOURLY("hourly"),
    HOURLY_TEMPERATURE_2M("temperature_2m"),
    HOURLY_RELATIVE_HUMIDITY("relative_humidity_2m"),
    HOURLY_APPARENT_TEMPERATURE("apparent_temperature"),
    HOURLY_PRECIPITATION("precipitation"),
    HOURLY_WIND_SPEED("wind_speed_10m"),
    HOURLY_WIND_DIRECTION("wind_direction_10m"),
    DAILY_TEMPERATURE_2M_MAX("temperature_2m_max"),
    DAILY_TEMPERATURE_2M_MIN("temperature_2m_min"),
    DAILY_DAYLIGT_DURATION("daylight_duration"),
    DAILY_PRECEPITATION_SUM("precipitation_sum"),
    DAILY_PRECIPITATION_HOURS("precipitation_hours"),
    DAILY_WIND_SPEED_10M_MAX("wind_speed_10m_max"),
    DAILY_WIND_DIRECTION_10M_DOMINANT("wind_direction_10m_dominant");
    
    public final String uriParam;
    
    
    WeatherUriParam(String uriParam) {
        this.uriParam = uriParam;
    }
    
    
}
