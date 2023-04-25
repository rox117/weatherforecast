package com.example.weatherforecast.service;

import com.example.weatherforecast.model.Forecast;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface ForcastService {
    List<Forecast> fetchCurrentForecast(String latitude, String longitude) throws JsonProcessingException;
}
