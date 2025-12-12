package com.pot.weather.app.exception;

public class OpenWeatherClientErrorUnauthorized extends RuntimeException {
    public OpenWeatherClientErrorUnauthorized() {
        super();
    }

    public OpenWeatherClientErrorUnauthorized(String message) {
        super(message);
    }
}
