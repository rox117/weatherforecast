package com.example.weatherforecast.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Forecast {
    private LocalDateTime date;
    private String airPressureAtSeaLevel;
    private String airTemperature;
    private String cloudAreaFraction;
    private String relativeHumidity;
    private String windFromDirection;
    private String windSpeed;
    private String precipitationAmount;
}
