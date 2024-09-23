package com.fredrik_eiserman_wendt.ws_project_weather_web_service.service;

import com.fredrik_eiserman_wendt.ws_project_weather_web_service.model.User;
import com.fredrik_eiserman_wendt.ws_project_weather_web_service.model.UserFavoriteLocation;
import com.fredrik_eiserman_wendt.ws_project_weather_web_service.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    
    private UserRepository userRepository;
    
    
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
    public User addFavoriteLocation(Long id, UserFavoriteLocation location) {
//        return userRepository.findById(id)
//                .map(user -> {
//                    user.addLocationToList(location);
//                    userRepository.save(user);
//                    return user;
//                });
        
        Optional<User> optionalUser = findById(id);
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            location.setUser(user);
            user.addLocationToList(location);
            System.out.println(user.getFavoriteLocations());
            return userRepository.save(user);
        }
        return null;
    }
    
    
    public Optional<User> deleteUser(Long id) {
        
        return userRepository.findById(id)
                .map(user -> {
                    userRepository.delete(user);
                    return user;
                });
        
    }
    

    
}
