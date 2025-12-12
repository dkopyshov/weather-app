package com.pot.weather.app.exception;

public class OpenWeatherClientErrorNotFound extends RuntimeException {
    public OpenWeatherClientErrorNotFound() {
        super();
    }

    public OpenWeatherClientErrorNotFound(String message) {
        super(message);
    }
}
