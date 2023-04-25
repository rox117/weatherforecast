package com.example.weatherforecast.service.impl;

import com.example.weatherforecast.dto.forecast.objectmap.ForecastMapper;
import com.example.weatherforecast.model.Forecast;
import com.example.weatherforecast.service.ForcastService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ForecastServiceImpl implements ForcastService {

    private final RestTemplate restTemplate;
    private final ForecastMapper forecastMapper;
    private String apiUrl;
    private String defaultUserAgent;

    public ForecastServiceImpl(RestTemplateBuilder restTemplateBuilder, @Value("${com.example.weatherforecast.url}") String apiUrl, ForecastMapper forecastMapper, @Value("${com.example.weatherforecast.agent}") String defaultUserAgent) {
        this.apiUrl = apiUrl;
        this.restTemplate = restTemplateBuilder.defaultHeader("User-Agent", defaultUserAgent).build();
        this.forecastMapper = forecastMapper;
        this.defaultUserAgent = defaultUserAgent;
    }

    @Override
    public List<Forecast> fetchCurrentForecast(String latitude, String longitude) throws JsonProcessingException {
        String forecastJson = restTemplate.getForEntity(buildRequestWithCoordinates(latitude, longitude), String.class).getBody();
        List<Forecast> forecast = forecastMapper.map(forecastJson);
        return forecast;
    }

    public String buildRequestWithCoordinates(String latitude, String longitude) {
        return String.format(apiUrl + "lat=%s&lon=%s", latitude, longitude);
    }
}
