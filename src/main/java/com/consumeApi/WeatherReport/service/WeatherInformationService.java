package com.consumeApi.WeatherReport.service;

import com.consumeApi.WeatherReport.entity.WeatherLog;

import java.util.List;

public interface WeatherInformationService {

    WeatherLog getCityWeatherInformation(String location);

    List<WeatherLog> getWeatherLogs();
}
