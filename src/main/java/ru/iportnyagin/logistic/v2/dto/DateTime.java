package ru.iportnyagin.logistic.v2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Calendar;

/**
 * DateTime.
 */
@Getter
@AllArgsConstructor
public class DateTime {

    private int day;
    private int hour;

    public static DateTime from(DateTime source) {
        return new DateTime(source.day, source.hour);
    }

    public static DateTime getCurrentDateTime() {
        Calendar calendar = Calendar.getInstance();
        return new DateTime(calendar.get(Calendar.DAY_OF_YEAR), calendar.get(Calendar.HOUR_OF_DAY));
    }

    public static DateTime getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        return new DateTime(calendar.get(Calendar.DAY_OF_YEAR), 0);
    }

    public int hoursBetween(DateTime dateTime) {
        return (this.day - dateTime.day) * 24 + (this.hour - dateTime.hour);
    }

    public DateTime addHour(int hour) {
        return fromGlobalHour(this.getGlobalHour() + hour);
    }

    public int getGlobalHour() {
        return this.day * 24 + this.hour;
    }

    public boolean after(DateTime dateTime) {
        return this.getGlobalHour() >= dateTime.getGlobalHour();
    }

    public boolean before(DateTime dateTime) {
        return this.getGlobalHour() < dateTime.getGlobalHour();
    }

    private DateTime fromGlobalHour(int hour) {
        this.day = hour / 24;
        this.hour = hour % 24;
        return this;
    }

}
