package com.fredrik_eiserman_wendt.ws_project_weather_web_service.service;

import com.fredrik_eiserman_wendt.ws_project_weather_web_service.model.User;
import com.fredrik_eiserman_wendt.ws_project_weather_web_service.model.UserFavoriteLocation;
import com.fredrik_eiserman_wendt.ws_project_weather_web_service.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    
    private UserRepository userRepository;
    
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    
    // TODO: 2024-09-22 optional
    public User findById(Long id) {
        return findById(id);
    }
    
    
    // TODO: 2024-09-22 optional
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    
    
    public User addFavoriteLocation(Long id, UserFavoriteLocation location) {
        Optional<User> optionalUser = userRepository.findById(id);
        User user;
        
        if (optionalUser.isEmpty()) {
            return user = null;
        }
        
        user = optionalUser.get();
        user.addLocationToList(location);
        return userRepository.save(user);
    }
    
    
    public User deleteUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        User user;
        
        if (optionalUser.isEmpty()) {
            return user = null;
        }
        
        userRepository.deleteById(optionalUser.get().getId());
        
        return optionalUser.get();
    }
    
    
}
