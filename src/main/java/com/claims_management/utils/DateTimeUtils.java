package com.claims_management.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class DateTimeUtils {

    //TODO: GET CURRENT DATE LOCAL
    public LocalDateTime getCurrentDate(){

        ZonedDateTime nowLocal = ZonedDateTime.now(ZoneId.of("America/Argentina/Buenos_Aires"));
        LocalDateTime data = nowLocal.toLocalDateTime();
        return LocalDateTime.of(data.getYear(),
                data.getMonth(), data.getDayOfMonth(), data.getHour(), data.getMinute(),data.getSecond());
    }
}
