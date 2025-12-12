package com.pot.weather.app.controller;

import com.pot.weather.app.exception.LocationAlreadyExistsException;
import com.pot.weather.app.exception.OpenWeatherClientErrorNotFound;
import com.pot.weather.app.exception.OpenWeatherClientErrorTooManyRequests;
import com.pot.weather.app.exception.OpenWeatherClientErrorUnauthorized;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

@ControllerAdvice
public class WeatherErrorHandler {

    private final MessageSource messageSource;

    public WeatherErrorHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(LocationAlreadyExistsException.class)
    public String handleOpenWeatherClientErrorUnauthorized(LocationAlreadyExistsException exception) {
        return "redirect:/weather";
    }

    @ExceptionHandler(OpenWeatherClientErrorUnauthorized.class)
    public String handleOpenWeatherClientErrorUnauthorized(OpenWeatherClientErrorUnauthorized exception, Model model, Locale locale) {
        model.addAttribute("errors", messageSource.getMessage("client.errors.unauthorized", new Object[0], locale));
        return "index";
    }

    @ExceptionHandler(OpenWeatherClientErrorNotFound.class)
    public String handleOpenWeatherClientErrorNotFound(OpenWeatherClientErrorNotFound exception, Model model, Locale locale) {
        model.addAttribute("errors", messageSource.getMessage("client.errors.not_found", new Object[0], locale));
        return "index";
    }

    @ExceptionHandler(OpenWeatherClientErrorTooManyRequests.class)
    public String handleOpenWeatherClientErrorTooManyRequests(OpenWeatherClientErrorTooManyRequests exception, Model model, Locale locale) {
        model.addAttribute("errors", messageSource.getMessage("client.errors.too_many_requests", new Object[0], locale));
        return "index";
    }
}
