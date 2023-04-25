package com.example.weatherforecast;

import com.example.weatherforecast.service.ForcastService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WeatherforecastApplication implements CommandLineRunner {

    private final ForcastService forecastService;

    public WeatherforecastApplication(ForcastService forecastService) {
        this.forecastService = forecastService;
    }

    public static void main(String[] args) {
        SpringApplication.run(WeatherforecastApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        forecastService.fetchCurrentForecast("53.2734","-7.77832031").forEach(f->System.out.println(f));
    }
}
