package com.consumeApi.WeatherReport.service;

import com.consumeApi.WeatherReport.entity.WeatherLog;
import com.consumeApi.WeatherReport.repository.WeatherInformationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.net.URI;
import java.sql.Timestamp;
import java.util.*;

@Service
public class WeatherInformationServiceImpl implements WeatherInformationService{

    private static final String API_KEY = "1a8c68acb4210af7b98439153404de79";
    private static final String CITY_WEATHER_URL =
            "http://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}";

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    private WeatherInformationRepository weatherInformationRepository;


    public WeatherInformationServiceImpl(RestTemplateBuilder restTemplateBuilder, ObjectMapper objectMapper) {
        this.restTemplate = restTemplateBuilder.build();
        this.objectMapper = objectMapper;
    }

    public WeatherLog getCityWeatherInformation(String location) {
        URI url = new UriTemplate(CITY_WEATHER_URL).expand(location, API_KEY);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        WeatherLog weatherLog = convert(responseEntity);

        System.out.println(responseEntity);

        if (Objects.nonNull(weatherLog))
            weatherInformationRepository.save(weatherLog);

        return weatherLog;
    }

    public List<WeatherLog> getWeatherLogs() {

        List<WeatherLog> weatherLogs = weatherInformationRepository.findAll();
        Collections.reverse(weatherLogs);
        List<WeatherLog> distinctWeatherLogs = new ArrayList<>();

        for (WeatherLog weatherLog: weatherLogs) {
            if (distinctWeatherLogs.size() <= 5 && !distinctWeatherLogs.contains(weatherLog)) {
                distinctWeatherLogs.add(weatherLog);
            }
        }

        return distinctWeatherLogs;
    }

    private WeatherLog convert(ResponseEntity<String> responseEntity) {
        try {
            JsonNode root = objectMapper.readTree(responseEntity.getBody());
            return new WeatherLog(
                    UUID.randomUUID().toString(),
                    root.path("name").asText(),
                    root.path("weather").get(0).path("description").asText(),
                    convertKelvinToCelcius(root.path("main").path("temp").asText()),
                    new Timestamp(System.currentTimeMillis())
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error parsing JSON", e);
        }
    }

    private String convertKelvinToCelcius(String kelvin) {
        return String.format("%.1f", Float.valueOf(kelvin) - 273.15F);
    }
}
