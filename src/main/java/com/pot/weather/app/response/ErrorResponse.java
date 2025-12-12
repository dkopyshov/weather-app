package com.pot.weather.app.response;

public record ErrorResponse(
    String cod,
    String message
) {
}
