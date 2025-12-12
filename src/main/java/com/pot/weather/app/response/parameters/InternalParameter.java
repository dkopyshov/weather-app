package com.pot.weather.app.response.parameters;

import java.time.Instant;

public record InternalParameter(
        int type,
        int id,
        String message,
        String country,
        Instant sunrise,
        Instant sunset
) {
}
