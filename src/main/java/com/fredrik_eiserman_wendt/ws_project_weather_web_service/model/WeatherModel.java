package com.fredrik_eiserman_wendt.ws_project_weather_web_service.model;

import java.util.List;

public class WeatherModel {
    
    public double latitude;
    public double longitude;
    public String generationtime_ms;
    public String utc_offset_seconds;
    public String timezone;
    public String timezone_abbreviation;
    public String elevation;
    public HourlyUnits hourly_units;
    public Hourly hourly;
    public DailyUnits daily_units;
    public Daily daily;
    
    
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
        return hourly_units;
    }
    
    
    public void setHourlyUnits(HourlyUnits hourlyUnits) {
        this.hourly_units = hourlyUnits;
    }
    
    
    public Hourly getHourly() {
        return hourly;
    }
    
    
    public void setHourly(Hourly hourly) {
        this.hourly = hourly;
    }
    
    
    public DailyUnits getDailyUnits() {
        return daily_units;
    }
    
    
    public void setDailyUnits(DailyUnits dailyUnits) {
        this.daily_units = dailyUnits;
    }
    
    
    public Daily getDaily() {
        return daily;
    }
    
    
    public void setDaily(Daily daily) {
        this.daily = daily;
    }
    
    
    public class HourlyUnits {
        
        public String time;
        public String temperature_2m;
        public String relative_humidity_2m;
        public String apparent_temperature;
        public String precipitation;
        public String wind_speed_10m;
        public String wind_direction_10m;
        
        
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
        
        
        public String getRelative_humidity_2m() {
            return relative_humidity_2m;
        }
        
        
        public void setRelative_humidity_2m(String relative_humidity_2m) {
            this.relative_humidity_2m = relative_humidity_2m;
        }
        
        
        public String getApparent_temperature() {
            return apparent_temperature;
        }
        
        
        public void setApparent_temperature(String apparent_temperature) {
            this.apparent_temperature = apparent_temperature;
        }
        
        
        public String getPrecipitation() {
            return precipitation;
        }
        
        
        public void setPrecipitation(String precipitation) {
            this.precipitation = precipitation;
        }
        
        
        public String getWind_speed_10m() {
            return wind_speed_10m;
        }
        
        
        public void setWind_speed_10m(String wind_speed_10m) {
            this.wind_speed_10m = wind_speed_10m;
        }
        
        
        public String getWind_direction_10m() {
            return wind_direction_10m;
        }
        
        
        public void setWind_direction_10m(String wind_direction_10m) {
            this.wind_direction_10m = wind_direction_10m;
        }
        
        
    }
    
    
    public class Hourly {
        public List<String> time;
        public List<Double> temperature_2m;
        public List<Double> relative_humidity_2m;
        public List<Double> apparent_temperature;
        public List<Double> precipitation;
        public List<Double> wind_speed_10m;
        public List<Double> wind_direction_10m;
        
        
        public List<Double> getTemperature_2m() {
            return temperature_2m;
        }
        
        
        public void setTemperature_2m(List<Double> temperature_2m) {
            this.temperature_2m = temperature_2m;
        }
        
        
        public List<Double> getRelative_humidity_2m() {
            return relative_humidity_2m;
        }
        
        
        public void setRelative_humidity_2m(List<Double> relative_humidity_2m) {
            this.relative_humidity_2m = relative_humidity_2m;
        }
        
        
        public List<Double> getApparent_temperature() {
            return apparent_temperature;
        }
        
        
        public void setApparent_temperature(List<Double> apparent_temperature) {
            this.apparent_temperature = apparent_temperature;
        }
        
        
        public List<Double> getPrecipitation() {
            return precipitation;
        }
        
        
        public void setPrecipitation(List<Double> precipitation) {
            this.precipitation = precipitation;
        }
        
        
        public List<Double> getWind_speed_10m() {
            return wind_speed_10m;
        }
        
        
        public void setWind_speed_10m(List<Double> wind_speed_10m) {
            this.wind_speed_10m = wind_speed_10m;
        }
        
        
        public List<Double> getWind_direction_10m() {
            return wind_direction_10m;
        }
        
        
        public void setWind_direction_10m(List<Double> wind_direction_10m) {
            this.wind_direction_10m = wind_direction_10m;
        }
        
        
        public List<String> getTime() {
            return time;
        }
        
        
        public void setTime(List<String> time) {
            this.time = time;
        }
        
        
    }
    
    
    public class Daily {
        
        public List<String> time;
        public List<Double> temperature_2m_max;
        public List<Double> temperature_2m_min;
        public List<Double> daylight_duration;
        public List<Integer> precipitation_sum;
        public List<Integer> precipitation_hours;
        public List<Double> wind_speed_10m_max;
        public List<Integer> wind_direction_10m_dominant;
        
        
        public List<String> getTime() {
            return time;
        }
        
        
        public void setTime(List<String> time) {
            this.time = time;
        }
        
        
        public List<Double> getTemperature_2m_max() {
            return temperature_2m_max;
        }
        
        
        public void setTemperature_2m_max(List<Double> temperature_2m_max) {
            this.temperature_2m_max = temperature_2m_max;
        }
        
        
        public List<Double> getTemperature_2m_min() {
            return temperature_2m_min;
        }
        
        
        public void setTemperature_2m_min(List<Double> temperature_2m_min) {
            this.temperature_2m_min = temperature_2m_min;
        }
        
        
        public List<Double> getDaylight_duration() {
            return daylight_duration;
        }
        
        
        public void setDaylight_duration(List<Double> daylight_duration) {
            this.daylight_duration = daylight_duration;
        }
        
        
        public List<Integer> getPrecipitation_sum() {
            return precipitation_sum;
        }
        
        
        public void setPrecipitation_sum(List<Integer> precipitation_sum) {
            this.precipitation_sum = precipitation_sum;
        }
        
        
        public List<Integer> getPrecipitation_hours() {
            return precipitation_hours;
        }
        
        
        public void setPrecipitation_hours(List<Integer> precipitation_hours) {
            this.precipitation_hours = precipitation_hours;
        }
        
        
        public List<Double> getWind_speed_10m_max() {
            return wind_speed_10m_max;
        }
        
        
        public void setWind_speed_10m_max(List<Double> wind_speed_10m_max) {
            this.wind_speed_10m_max = wind_speed_10m_max;
        }
        
        
        public List<Integer> getWind_direction_10m_dominant() {
            return wind_direction_10m_dominant;
        }
        
        
        public void setWind_direction_10m_dominant(List<Integer> wind_direction_10m_dominant) {
            this.wind_direction_10m_dominant = wind_direction_10m_dominant;
        }
        
        
    }
    
    
    public class DailyUnits {
        
        public String time;
        public String temperature_2m_max;
        public String temperature_2m_min;
        public String daylight_duration;
        public String precipitation_sum;
        public String precipitation_hours;
        public String wind_speed_10m_max;
        public String wind_direction_10m_dominant;
        
        
        public String getTime() {
            return time;
        }
        
        
        public void setTime(String time) {
            this.time = time;
        }
        
        
        public String getTemperature_2m_max() {
            return temperature_2m_max;
        }
        
        
        public void setTemperature_2m_max(String temperature_2m_max) {
            this.temperature_2m_max = temperature_2m_max;
        }
        
        
        public String getTemperature_2m_min() {
            return temperature_2m_min;
        }
        
        
        public void setTemperature_2m_min(String temperature_2m_min) {
            this.temperature_2m_min = temperature_2m_min;
        }
        
        
        public String getDaylight_duration() {
            return daylight_duration;
        }
        
        
        public void setDaylight_duration(String daylight_duration) {
            this.daylight_duration = daylight_duration;
        }
        
        
        public String getPrecipitation_sum() {
            return precipitation_sum;
        }
        
        
        public void setPrecipitation_sum(String precipitation_sum) {
            this.precipitation_sum = precipitation_sum;
        }
        
        
        public String getPrecipitation_hours() {
            return precipitation_hours;
        }
        
        
        public void setPrecipitation_hours(String precipitation_hours) {
            this.precipitation_hours = precipitation_hours;
        }
        
        
        public String getWind_speed_10m_max() {
            return wind_speed_10m_max;
        }
        
        
        public void setWind_speed_10m_max(String wind_speed_10m_max) {
            this.wind_speed_10m_max = wind_speed_10m_max;
        }
        
        
        public String getWind_direction_10m_dominant() {
            return wind_direction_10m_dominant;
        }
        
        
        public void setWind_direction_10m_dominant(String wind_direction_10m_dominant) {
            this.wind_direction_10m_dominant = wind_direction_10m_dominant;
        }
        
        
    }
    
    
}
