package com.example.weatherforecast.dto.forecast;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class ForecastDto{
    private String time;
    private Data data;
}




