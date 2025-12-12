package com.pot.weather.app.response.parameters;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Rain(
        @JsonProperty("1h")
        double mmInHour
) {
}
