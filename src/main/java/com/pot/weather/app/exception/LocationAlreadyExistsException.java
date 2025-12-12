package com.pot.weather.app.exception;

import com.pot.weather.app.entity.UserLocationData;
import org.springframework.dao.DuplicateKeyException;

public class LocationAlreadyExistsException extends RuntimeException {
    public LocationAlreadyExistsException(UserLocationData userLocationData, DuplicateKeyException ex) {
    }
}
