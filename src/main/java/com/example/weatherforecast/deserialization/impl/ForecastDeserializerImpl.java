package com.example.weatherforecast.deserialization.impl;

import com.example.weatherforecast.deserialization.ForecastDeserializer;
import com.example.weatherforecast.dto.forecast.ForecastDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ForecastDeserializerImpl implements ForecastDeserializer {

    private final ObjectMapper objectMapper;
    private String rootObject;
    private String forecastSeries;

    public ForecastDeserializerImpl(@Value("${com.example.weatherforecast.root}") String rootObject, @Value("${com.example.weatherforecast.series}") String forecastSeries){
        this.rootObject = rootObject;
        this.forecastSeries = forecastSeries;
        this.objectMapper = Jackson2ObjectMapperBuilder.json().propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE).build();
    }

    @Override
    public List<ForecastDto> deserializeList(String forecast) throws JsonProcessingException {
        JsonNode root = objectMapper.readTree(forecast).get(rootObject).get(forecastSeries);
        List<ForecastDto> forecastDtos = Arrays.asList(objectMapper.convertValue(root, ForecastDto[].class));
        return forecastDtos;
    }
}
