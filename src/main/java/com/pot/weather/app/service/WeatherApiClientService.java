package com.pot.weather.app.service;

import com.pot.weather.app.exception.OpenWeatherClientErrorNotFound;
import com.pot.weather.app.exception.OpenWeatherClientErrorTooManyRequests;
import com.pot.weather.app.exception.OpenWeatherClientErrorUnauthorized;
import com.pot.weather.app.response.ErrorResponse;
import com.pot.weather.app.response.GeoLocation;
import com.pot.weather.app.response.WeatherApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

import java.util.List;

@Slf4j
@Service
public class WeatherApiClientService {

    private static final String DATA_WEATHER_URL = "/data/2.5/weather";
    private static final String GEOCODING_URL = "/geo/1.0/direct";
    private static final String LANG_QUERY_PARAMETER_NAME = "lang";
    private static final String CITY_QUERY_PARAMETER_NAME = "q";
    private static final String LIMIT_QUERY_PARAMETER_NAME = "limit";
    private static final String LAT_QUERY_PARAMETER_NAME = "lat";
    private static final String LON_QUERY_PARAMETER_NAME = "lon";
    private static final String UNITS_QUERY_PARAMETER_NAME = "units";
    private static final String API_QUERY_PARAMETER_NAME = "appid";
    private static final ParameterizedTypeReference<List<GeoLocation>> GEO_LOCATION_TYPE_REFERENCE =
            new ParameterizedTypeReference<>() {
            };

    @Value("${weather.api.server.key}")
    private String apiKey;

    @Qualifier("openWeather")
    private final RestClient restClient;

    public WeatherApiClientService(RestClient restClient) {
        this.restClient = restClient;
    }

    public WeatherApiResponse getCurrentWeatherData(double latitude, double longitude, String language) {
        try {
            return restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .pathSegment(DATA_WEATHER_URL)
                            .queryParam(LAT_QUERY_PARAMETER_NAME, latitude)
                            .queryParam(LON_QUERY_PARAMETER_NAME, longitude)
                            .queryParam(LANG_QUERY_PARAMETER_NAME, language)
                            .queryParam(UNITS_QUERY_PARAMETER_NAME, "metric")
                            .queryParam(API_QUERY_PARAMETER_NAME, apiKey)
                            .build())
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .body(WeatherApiResponse.class);
        } catch (HttpClientErrorException.Unauthorized exception) {
            logError(exception);
            throw new OpenWeatherClientErrorUnauthorized();
        } catch (HttpClientErrorException.NotFound exception) {
            logError(exception);
            throw new OpenWeatherClientErrorNotFound();
        } catch (HttpClientErrorException.TooManyRequests exception) {
            logError(exception);
            throw new OpenWeatherClientErrorTooManyRequests();
        }
    }

    public List<GeoLocation> getGeoLocationData(String locationName) {
        try {
            return restClient.get()
                    .uri(uriBuilder -> uriBuilder
                                .pathSegment(GEOCODING_URL)
                                .queryParam(CITY_QUERY_PARAMETER_NAME, locationName)
                                .queryParam(LIMIT_QUERY_PARAMETER_NAME, 3)
                                .queryParam(API_QUERY_PARAMETER_NAME, apiKey)
                                .build())
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .body(GEO_LOCATION_TYPE_REFERENCE);
        } catch (HttpClientErrorException.Unauthorized exception) {
            logError(exception);
            throw new OpenWeatherClientErrorUnauthorized();
        } catch (HttpClientErrorException.NotFound exception) {
            logError(exception);
            throw new OpenWeatherClientErrorNotFound();
        } catch (HttpClientErrorException.TooManyRequests exception) {
            logError(exception);
            throw new OpenWeatherClientErrorTooManyRequests();
        }
    }

    private void logError(RestClientResponseException exception) {
        ErrorResponse clientErrorResponse = exception.getResponseBodyAs(ErrorResponse.class);
        assert clientErrorResponse != null;
        log.error(clientErrorResponse.message());
    }
}
