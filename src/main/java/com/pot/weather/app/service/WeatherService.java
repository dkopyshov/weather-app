package com.pot.weather.app.service;

import com.pot.weather.app.dto.GeoLocationView;
import com.pot.weather.app.dto.WeatherView;
import com.pot.weather.app.entity.UserLocationData;
import com.pot.weather.app.response.GeoLocation;
import com.pot.weather.app.response.WeatherApiResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        List<GeoLocation> locationData = weatherApiClientService.getGeoLocationData(locationName);
        return locationData.stream()
                .map(g -> new GeoLocationView(
                        Optional.ofNullable(g.localNames())
                                .map(m -> m.get(language))
                                .orElse(g.name()),
                        g.latitude(),
                        g.longitude(),
                        g.country(),
                        g.state()
                ))
                .toList();
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
