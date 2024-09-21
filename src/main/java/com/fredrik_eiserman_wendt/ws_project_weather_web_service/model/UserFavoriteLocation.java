package com.fredrik_eiserman_wendt.ws_project_weather_web_service.model;


import jakarta.persistence.*;

@Entity
@Table(name = "users_favorite_location")
public class UserFavoriteLocation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String locationName;
    private double longitude;
    private double latitude;
    
    @ManyToOne()
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    private User user;
    
    
    public UserFavoriteLocation() {
    }
    
    
    public UserFavoriteLocation(String locationName, double longitude, double latitude) {
        this.locationName = locationName;
        this.longitude = longitude;
        this.latitude = latitude;
    }
    
    
    public Long getId() {
        return id;
    }
    
    
    public String getLocationName() {
        return locationName;
    }
    
    
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
    
    
    public double getLongitude() {
        return longitude;
    }
    
    
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    
    
    public double getLatitude() {
        return latitude;
    }
    
    
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    
    
    public User getUser() {
        return user;
    }
    
    
}
