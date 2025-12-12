package com.pot.weather.app.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public record GeoLocation(
        String name,
        @JsonProperty("local_names")
        Map<String, String> localNames,
        @JsonProperty("lat")
        double latitude,
        @JsonProperty("lon")
        double longitude,
        String country,
        String state
)
{
}
