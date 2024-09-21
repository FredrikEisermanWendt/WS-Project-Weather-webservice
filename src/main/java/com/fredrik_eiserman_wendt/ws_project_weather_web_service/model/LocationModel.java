package com.fredrik_eiserman_wendt.ws_project_weather_web_service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LocationModel {
    
    @JsonProperty("place_id")
    private long placeId;
    
    private String licence;
    
    @JsonProperty("osm_type")
    private String osmType;
    
    @JsonProperty("osm_id")
    private long osmId;
    
    @JsonProperty("boundingbox")
    private String[] boundingBox;
    
    private String lat;
    private String lon;
    
    @JsonProperty("display_name")
    private String displayName;
    
    @JsonProperty("class")
    private String classType;  // 'class' is a reserved keyword in Java
    
    private String type;
    private double importance;
    
    // Getters and setters
    public long getPlaceId() {
        return placeId;
    }
    
    public void setPlaceId(long placeId) {
        this.placeId = placeId;
    }
    
    public String getLicence() {
        return licence;
    }
    
    public void setLicence(String licence) {
        this.licence = licence;
    }
    
    public String getOsmType() {
        return osmType;
    }
    
    public void setOsmType(String osmType) {
        this.osmType = osmType;
    }
    
    public long getOsmId() {
        return osmId;
    }
    
    public void setOsmId(long osmId) {
        this.osmId = osmId;
    }
    
    public String[] getBoundingBox() {
        return boundingBox;
    }
    
    public void setBoundingBox(String[] boundingBox) {
        this.boundingBox = boundingBox;
    }
    
    public String getLat() {
        return lat;
    }
    
    public void setLat(String lat) {
        this.lat = lat;
    }
    
    public String getLon() {
        return lon;
    }
    
    public void setLon(String lon) {
        this.lon = lon;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    
    public String getClassType() {
        return classType;
    }
    
    public void setClassType(String classType) {
        this.classType = classType;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public double getImportance() {
        return importance;
    }
    
    public void setImportance(double importance) {
        this.importance = importance;
    }
    
}
