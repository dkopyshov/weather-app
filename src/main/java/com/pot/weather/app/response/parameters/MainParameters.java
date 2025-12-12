package com.pot.weather.app.response.parameters;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MainParameters(
        double temp,
        @JsonProperty("feels_like")
        double feelsLike,
        @JsonProperty("temp_min")
        double tempMin,
        @JsonProperty("temp_max")
        double tempMax,
        double pressure,
        double humidity,
        @JsonProperty("sea_level")
        double seaLevel,
        @JsonProperty("grnd_level")
        double grndLevel
) {
}
