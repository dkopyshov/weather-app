package com.pot.weather.app.controller;

import com.pot.weather.app.dto.GeoLocationView;
import com.pot.weather.app.dto.WeatherView;
import com.pot.weather.app.service.WeatherService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Locale;

@Controller
public class WeatherController {

    private static final int DEFAULT_USER_ID = 1;

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("weather")
    public String index(Locale locale, Model model) {
        List<WeatherView> userWeatherData = weatherService.getUserWeatherData(DEFAULT_USER_ID, locale.getLanguage());
        model.addAttribute("userWeatherData", userWeatherData);
        return "index";
    }

    @GetMapping("search")
    @Transactional
    public String searchWeatherViewLocation(@RequestParam String locationName, Model model, Locale locale) {
        List<GeoLocationView> foundGeoData = weatherService.findGeoLocations(locationName, locale.getLanguage());
        model.addAttribute("locationName", locationName);
        model.addAttribute("foundGeoData", foundGeoData);
        return "search";
    }

    @PostMapping("add")
    public String addWeatherViewLocation(@ModelAttribute GeoLocationView weatherView) {
        weatherService.addGeoLocation(DEFAULT_USER_ID, weatherView);
        return "redirect:/weather";
    }

    @PostMapping("delete")
    public String deleteWeatherViewLocation(@RequestParam int userLocationDataId) {
        weatherService.deleteGeoLocation(DEFAULT_USER_ID, userLocationDataId);
        return "redirect:/weather";
    }
}
