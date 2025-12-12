package com.pot.weather.app.response.parameters;

public record Weather(
        int id,
        String main,
        String description,
        String icon
) {
}
