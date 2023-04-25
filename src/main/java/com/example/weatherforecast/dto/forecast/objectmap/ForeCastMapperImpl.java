package com.example.weatherforecast.dto.forecast.objectmap;

import com.example.weatherforecast.deserialization.ForecastDeserializer;
import com.example.weatherforecast.dto.forecast.ForecastDto;
import com.example.weatherforecast.model.Forecast;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class ForeCastMapperImpl implements ForecastMapper {

    private final ForecastDeserializer forecastDeserializer;

    public ForeCastMapperImpl(ForecastDeserializer forecastDeserializer){
        this.forecastDeserializer = forecastDeserializer;
    }

    @Override
    public List<Forecast> map(String forecast) throws JsonProcessingException {
        List<ForecastDto> forecastDtos = deserialize(forecast);
        List<Forecast> forecasts = forecastDtos.stream().map(f -> map(f)).toList();
        return forecasts;
    }

    @Override
    public Forecast map(ForecastDto forecastDto){
        Forecast forecast = new Forecast();
        if (forecastDto == null) {
            return null;
        }
        forecast.setDate(convertTimeStamp(forecastDto.getTime()));
        forecast.setAirPressureAtSeaLevel(forecastDto.getData().getInstant().getDetails().getAirPressureAtSeaLevel());
        forecast.setAirTemperature(forecastDto.getData().getInstant().getDetails().getAirTemperature());
        forecast.setCloudAreaFraction(forecastDto.getData().getInstant().getDetails().getCloudAreaFraction());
        forecast.setRelativeHumidity(forecastDto.getData().getInstant().getDetails().getRelativeHumidity());
        forecast.setWindFromDirection(forecastDto.getData().getInstant().getDetails().getWindFromDirection());
        forecast.setWindSpeed(forecastDto.getData().getInstant().getDetails().getWindSpeed());
        forecast.setPrecipitationAmount(forecastDto.getData().getInstant().getDetails().getPrecipitationAmount());
        return forecast;
    }

    private List<ForecastDto> deserialize(String forecast) throws JsonProcessingException {
        return forecastDeserializer.deserializeList(forecast);
    }

    private LocalDateTime convertTimeStamp(String timestamp){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").withZone(ZoneId.of("UTC"));
        LocalDateTime date = LocalDateTime.parse(timestamp, formatter);
        return date;
    }


}
