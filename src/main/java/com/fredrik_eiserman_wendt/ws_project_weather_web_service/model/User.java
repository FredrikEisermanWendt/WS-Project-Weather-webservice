package com.fredrik_eiserman_wendt.ws_project_weather_web_service.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<UserFavoriteLocation> favoriteLocations;
    
    
    public User() {
    }
    
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    
    public Long getId() {
        return id;
    }
    
    
    public String getUsername() {
        return username;
    }
    
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    
    public String getPassword() {
        return password;
    }
    
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    
    public List<UserFavoriteLocation> getFavoriteLocations() {
        return favoriteLocations;
    }
    
    
    public void setFavoriteLocations(List<UserFavoriteLocation> favoriteLocations) {
        this.favoriteLocations = favoriteLocations;
    }
    
    
    // TODO: 2024-09-23 felhantering
    public boolean addLocationToList(UserFavoriteLocation location){
        favoriteLocations.add(location);
        return true;
    }
    
    
}
