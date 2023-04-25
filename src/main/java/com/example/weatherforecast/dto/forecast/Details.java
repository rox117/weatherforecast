package com.example.weatherforecast.dto.forecast;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Details{
    private String airPressureAtSeaLevel;
    private String airTemperature;
    private String cloudAreaFraction;
    private String relativeHumidity;
    private String windFromDirection;
    private String windSpeed;
    private String precipitationAmount;
}
