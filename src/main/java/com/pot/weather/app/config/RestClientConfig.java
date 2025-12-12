package com.pot.weather.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Value("${weather.api.server.url}")
    private String openWeatherApiBaseUrl;

    /**
     * Возвращает RestClient настроенный для <a href="https://openweathermap.org/">OpenWeatherMap.org</a>
     * @return
     */
    @Bean("openWeather")
    public RestClient openWeatherRestClient() {
        return RestClient.builder()
                .baseUrl(openWeatherApiBaseUrl)
//                .requestInterceptor() нужен ли здесь ClientHttpRequestInterceptor чтобы в каждый запрос добавлять API_KEY
                .build();
    }
}
