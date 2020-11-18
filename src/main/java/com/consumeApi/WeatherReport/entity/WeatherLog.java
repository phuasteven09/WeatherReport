package com.consumeApi.WeatherReport.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.util.Objects;

@Data
@Document
public class WeatherLog {

    private ObjectId id;
    private String responseId;
    private String location;
    private String actualWeather;
    private String temperature;
    private Timestamp dateTimeInserted;

    public WeatherLog(String responseId, String  location, String actualWeather, String temperature, Timestamp dateTimeInserted) {
        this.responseId = responseId;
        this.location = location;
        this.actualWeather = actualWeather;
        this.temperature = temperature;
        this.dateTimeInserted = dateTimeInserted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherLog that = (WeatherLog) o;
        return Objects.equals(location, that.location) &&
                Objects.equals(actualWeather, that.actualWeather) &&
                Objects.equals(temperature, that.temperature);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, actualWeather, temperature);
    }
}
