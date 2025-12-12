package com.pot.weather.app.dto;

/**
 * Для передачи в UI
 */
public record WeatherView(
        int userLocationDataId,
        String localName,
        double latitude,
        double longitude,
        double temperature,
        double feelsLike,
        double cloudiness,
        String weatherMain,
        String weatherIcon
) {
}
