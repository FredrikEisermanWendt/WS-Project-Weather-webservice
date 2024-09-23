package com.fredrik_eiserman_wendt.ws_project_weather_web_service.controller;

import com.fredrik_eiserman_wendt.ws_project_weather_web_service.model.User;
import com.fredrik_eiserman_wendt.ws_project_weather_web_service.model.UserFavoriteLocation;
import com.fredrik_eiserman_wendt.ws_project_weather_web_service.repository.UserRepository;
import com.fredrik_eiserman_wendt.ws_project_weather_web_service.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    
    UserService userService;
    
    
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    
    @GetMapping
    public User getUserById(Long id) {
        return userService.findById(id);
    }
    
    
    @PostMapping
    public ResponseEntity<User> postUser(@RequestBody User user) {
        return ResponseEntity.status(201).body(userService.saveUser(user));
    }
    
    
    @PutMapping("/{id}")
    public ResponseEntity<User> putUserFavoriteLocation(@PathVariable("id") Long id, @RequestBody UserFavoriteLocation location) {
        User user = userService.addFavoriteLocation(id, location);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
    
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id){
        User user = userService.deleteUser(id);
        
        if (user == null){
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(user);
        
    }
    
    
    
    
    
    
    
}
