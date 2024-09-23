package com.fredrik_eiserman_wendt.ws_project_weather_web_service.repository;

import com.fredrik_eiserman_wendt.ws_project_weather_web_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
