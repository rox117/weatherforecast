package com.example.weatherforecast.dto.forecast.objectmap;

import com.example.weatherforecast.dto.forecast.ForecastDto;
import com.example.weatherforecast.model.Forecast;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface ForecastMapper {

    List<Forecast> map(String forecastDto) throws JsonProcessingException;
    Forecast map(ForecastDto forecastDto);

}
