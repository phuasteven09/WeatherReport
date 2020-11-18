package com.consumeApi.WeatherReport;

import com.consumeApi.WeatherReport.converter.DateToTimeStampConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.Arrays;

@Configuration
public class MongoConverter {

    @Bean
    public MongoCustomConversions mongoCustomConversions() {

        return new MongoCustomConversions(
                Arrays.asList(
                        new DateToTimeStampConverter()
                )
        );
    }
}
