package ru.iportnyagin.logistic.dto;

import lombok.Getter;
import ru.iportnyagin.logistic.DateTime;

/**
 * DateTime.
 */
@Getter
public class DateTimeDto {

    private int day;
    private int hour;

    public DateTimeDto() {
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public DateTime toDateTime() {
        return new DateTime(day, hour);
    }
}
