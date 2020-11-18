package com.consumeApi.WeatherReport.controller;

import com.consumeApi.WeatherReport.entity.WeatherLog;
import com.consumeApi.WeatherReport.service.WeatherInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class WeatherInformationController {

    @Autowired
    private WeatherInformationService weatherInformationService;

    @RequestMapping(value = "/weather/{location}", method = RequestMethod.GET)
    public ResponseEntity<WeatherLog> getCityWeatherInformation(@PathVariable String location) {
        WeatherLog weatherLog = weatherInformationService.getCityWeatherInformation(location);

        return new ResponseEntity(weatherLog, HttpStatus.OK);
    }

    @RequestMapping(value = "/weatherLogs", method = RequestMethod.GET)
    public ResponseEntity<List<WeatherLog>> getWeatherLogs() {
        System.out.println("HELLO");
        List<WeatherLog> weatherLogs = weatherInformationService.getWeatherLogs();

        return new ResponseEntity(weatherLogs, HttpStatus.OK);
    }
}
