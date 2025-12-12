package com.pot.weather.app.exception;

public class OpenWeatherClientErrorTooManyRequests extends RuntimeException {
    public OpenWeatherClientErrorTooManyRequests() {
        super();
    }

    public OpenWeatherClientErrorTooManyRequests(String message) {
        super(message);
    }
}
