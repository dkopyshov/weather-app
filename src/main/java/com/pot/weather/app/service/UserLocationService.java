package com.pot.weather.app.service;

import com.pot.weather.app.entity.UserLocationData;
import com.pot.weather.app.exception.LocationAlreadyExistsException;
import com.pot.weather.app.repository.LocationRepository;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLocationService {

    private final LocationRepository repository;

    public UserLocationService(LocationRepository repository) {
        this.repository = repository;
    }

    //TODO обработка ошибок?
    public void addUserLocation(UserLocationData userLocationData) {
        try {
            repository.save(userLocationData);
        } catch (DuplicateKeyException ex) {
            throw new LocationAlreadyExistsException(userLocationData, ex);
        }
    }

    public List<UserLocationData> getUserLocations(int userId) {
        return repository.findByUserId(userId);
    }

    public void removeUserLocation(int userLocationId) {
        repository.deleteUserLocation(userLocationId);
    }
}
