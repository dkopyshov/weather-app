package com.pot.weather.app.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pot.weather.app.response.parameters.Clouds;
import com.pot.weather.app.response.parameters.Coordinate;
import com.pot.weather.app.response.parameters.InternalParameter;
import com.pot.weather.app.response.parameters.MainParameters;
import com.pot.weather.app.response.parameters.Rain;
import com.pot.weather.app.response.parameters.Weather;
import com.pot.weather.app.response.parameters.Wind;

import java.time.Instant;
import java.util.List;

public record WeatherApiResponse(
        @JsonProperty("coord")
        Coordinate coordinate,
        List<Weather> weather,
        @JsonProperty("base")
        String base,
        @JsonProperty("main")
        MainParameters main,
        @JsonProperty("visibility")
        String visibility,
        @JsonProperty("wind")
        Wind wind,
        @JsonProperty("rain")
        Rain rain,
        @JsonProperty("clouds")
        Clouds clouds,
        @JsonProperty("dt")
        Instant dateTime,
        @JsonProperty("sys")
        InternalParameter sys,
        @JsonProperty("timezone")
        int shiftTimeZoneInSeconds,
        @JsonProperty("id")
        int id,
        @JsonProperty("name")
        String name,
        @JsonProperty("cod")
        int code
) {
}
