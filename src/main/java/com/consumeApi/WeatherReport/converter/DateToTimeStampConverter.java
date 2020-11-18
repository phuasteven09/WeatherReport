package com.consumeApi.WeatherReport.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.util.Date;
import java.sql.Timestamp;

@ReadingConverter
public class DateToTimeStampConverter implements Converter<Date, Timestamp> {

    @Override
    public Timestamp convert(Date date) {
        return new Timestamp(date.getTime());
    }
}
