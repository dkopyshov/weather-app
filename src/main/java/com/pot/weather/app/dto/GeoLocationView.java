package com.pot.weather.app.dto;

/**
 * Для передачи в UI
 */
public record GeoLocationView(
        String localName,
        double latitude,
        double longitude,
        String country,
        String state
) {
}
