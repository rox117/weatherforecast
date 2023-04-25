package com.example.weatherforecast.deserialization;

import com.example.weatherforecast.dto.forecast.ForecastDto;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface ForecastDeserializer {
    List<ForecastDto> deserializeList(String forecast) throws JsonProcessingException;
}
