package com.consumeApi.WeatherReport.repository;

import com.consumeApi.WeatherReport.entity.WeatherLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherInformationRepository extends MongoRepository<WeatherLog, Long> {

}
