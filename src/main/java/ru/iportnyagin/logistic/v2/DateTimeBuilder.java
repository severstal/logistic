package ru.iportnyagin.logistic.v2;

import java.util.Calendar;

/**
 * DateTimeBuilder.
 */
public class DateTimeBuilder {

    private int day;
    private int hour;

    private DateTimeBuilder() {
    }

    public static DateTimeBuilder builder() {
        return new DateTimeBuilder();
    }

    public DateTimeBuilder setDay(int day) {
        this.day = day;
        return this;
    }

    public DateTimeBuilder setHour(int hour) {
        this.hour = hour;
        return this;
    }

    public DateTimeBuilder setDateTime(DateTime dateTime) {
        this.day = dateTime.getDay();
        this.hour = dateTime.getHour();
        return this;
    }

    public DateTimeBuilder addHour(int hour) {
        this.hour += hour;
        return this;
    }

    public DateTime build() {
        return new DateTime(this.day, this.hour);
    }

    public static DateTime buildCurrentDateTime() {
        Calendar calendar = Calendar.getInstance();
        return new DateTime(calendar.get(Calendar.DAY_OF_YEAR), calendar.get(Calendar.HOUR_OF_DAY));
    }

    public static DateTime buildCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        return new DateTime(calendar.get(Calendar.DAY_OF_YEAR), 0);
    }

}
