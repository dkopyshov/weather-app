package com.pot.weather.app.service;

import com.pot.weather.app.dto.GeoLocationView;
import com.pot.weather.app.dto.WeatherView;
import com.pot.weather.app.entity.UserLocationData;
import com.pot.weather.app.response.GeoLocation;
import com.pot.weather.app.response.WeatherApiResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherService {

    private final UserLocationService userLocationService;
    private final WeatherApiClientService weatherApiClientService;

    public WeatherService(UserLocationService userLocationService,
                          WeatherApiClientService weatherApiClientService) {
        this.userLocationService = userLocationService;
        this.weatherApiClientService = weatherApiClientService;
    }

    public List<WeatherView> getUserWeatherData(int userId, String language) {
        ArrayList<WeatherView> weatherViews = new ArrayList<>();
        for (UserLocationData userLocationData : userLocationService.getUserLocations(userId)) {
            WeatherApiResponse weatherApiResponse = weatherApiClientService.getCurrentWeatherData(userLocationData.getLatitude(), userLocationData.getLongitude(), language);
            WeatherView weatherView = new WeatherView(
                    userLocationData.getId(),
                    userLocationData.getName(),
                    userLocationData.getLatitude(),
                    userLocationData.getLongitude(),
                    weatherApiResponse.main().temp(),
                    weatherApiResponse.main().feelsLike(),
                    weatherApiResponse.clouds().all(),
                    weatherApiResponse.weather().get(0).main(),
                    weatherApiResponse.weather().get(0).icon()
            );
            weatherViews.add(weatherView);
        }
        return weatherViews;
    }

    public List<GeoLocationView> findGeoLocations(String locationName, String language) {
        ArrayList<GeoLocationView> geoLocationViews = new ArrayList<>();
        List<GeoLocation> locationData = weatherApiClientService.getGeoLocationData(locationName);
        for (GeoLocation geoLocation : locationData) {
            GeoLocationView geoLocationView = new GeoLocationView(geoLocation.localNames().get(language),
                    geoLocation.latitude(),
                    geoLocation.longitude(),
                    geoLocation.country(),
                    geoLocation.state());
            geoLocationViews.add(geoLocationView);
        }
        return geoLocationViews;
    }

    public void addGeoLocation(int userId, GeoLocationView weatherView) {
        UserLocationData userLocationData = new UserLocationData(
                weatherView.localName(),
                userId,
                weatherView.latitude(),
                weatherView.longitude());
        userLocationService.addUserLocation(userLocationData);
    }

    public void deleteGeoLocation(int userId, int userLocationId) {
        userLocationService.removeUserLocation(userLocationId);
    }
}
