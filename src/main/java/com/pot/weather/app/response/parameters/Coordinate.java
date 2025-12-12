package com.pot.weather.app.response.parameters;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Coordinate(
        @JsonProperty("lon")
        double longitude,
        @JsonProperty("lat")
        double latitude) {
}
