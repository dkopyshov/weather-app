package com.pot.weather.app.response.parameters;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Clouds(
        @JsonProperty("all")
        int all
) {
}
