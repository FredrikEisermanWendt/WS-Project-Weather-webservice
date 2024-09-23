package com.fredrik_eiserman_wendt.ws_project_weather_web_service.controller;

import com.fredrik_eiserman_wendt.ws_project_weather_web_service.model.User;
import com.fredrik_eiserman_wendt.ws_project_weather_web_service.model.UserFavoriteLocation;
import com.fredrik_eiserman_wendt.ws_project_weather_web_service.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    
    UserService userService;
    
    
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        return userService.findById(id)
                .map(user -> ResponseEntity.ok(user))
                .orElse(ResponseEntity.notFound().build());
    }
    
    
    @PostMapping()
    public ResponseEntity<User> postUser(@RequestBody User user) {
        return ResponseEntity.status(201).body(userService.saveUser(user));
    }
    
    
    @PutMapping("/{id}")
    public ResponseEntity<User> putUserFavoriteLocation(@PathVariable("id") Long id, @RequestBody(required = true) UserFavoriteLocation location) {
//        return userService.addFavoriteLocation(id, location)
//                .map(user -> ResponseEntity.ok(user))
//                .orElse(ResponseEntity.notFound().build());
        
        return ResponseEntity.ok(userService.addFavoriteLocation(id, location));
    }
    
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
        return userService.deleteUser(id)
                .map(user -> ResponseEntity.ok(user))
                .orElse(ResponseEntity.notFound().build());
        
    }
    
    
}
