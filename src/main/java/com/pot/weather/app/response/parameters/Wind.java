package com.pot.weather.app.response.parameters;

public record Wind(
        double speed,
        double deg,
        double gust
) {
}
