package com.fredrik_eiserman_wendt.ws_project_weather_web_service.service;

import com.fredrik_eiserman_wendt.ws_project_weather_web_service.model.User;
import com.fredrik_eiserman_wendt.ws_project_weather_web_service.model.UserFavoriteLocation;
import com.fredrik_eiserman_wendt.ws_project_weather_web_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    
    private UserRepository userRepository;
    
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
        
    }
    
    
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    
    
    // TODO: 2024-09-23 test cleaner code
    @Transactional
    public Optional<User> addFavoriteLocation(Long id, UserFavoriteLocation location) {
        return findById(id).map(user -> {
            location.setUser(user);
            user.addLocationToList(location);
            userRepository.save(user);
            return user;
        });
    }
    
    
    public Optional<User> deleteUser(Long id) {
        
        return userRepository.findById(id)
                .map(user -> {
                    userRepository.delete(user);
                    return user;
                });
        
    }
    

    
}
