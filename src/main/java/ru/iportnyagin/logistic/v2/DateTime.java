package ru.iportnyagin.logistic.v2;

import lombok.Getter;

import java.util.Calendar;

/**
 * DateTime.
 */
@Getter
public class DateTime {

    private final int day;
    private final int hour;

    public DateTime(int day, int hour) {
        int gHour = day * 24 + hour;
        this.day = gHour / 24;
        this.hour = gHour % 24;
    }

    public DateTime(DateTime dateTime, int addHour) {
        this(dateTime.day, dateTime.hour + addHour);
    }

    private int toGlobalHour() {
        return this.day * 24 + this.hour;
    }

    public int hoursBetween(DateTime dateTime) {
        return this.toGlobalHour() - dateTime.toGlobalHour();
    }

    public boolean after(DateTime dateTime) {
        return this.toGlobalHour() >= dateTime.toGlobalHour();
    }

    public boolean before(DateTime dateTime) {
        return this.toGlobalHour() < dateTime.toGlobalHour();
    }

    public static DateTime getCurrentDateTime() {
        Calendar calendar = Calendar.getInstance();
        return new DateTime(calendar.get(Calendar.DAY_OF_YEAR), calendar.get(Calendar.HOUR_OF_DAY));
    }

    public static DateTime getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        return new DateTime(calendar.get(Calendar.DAY_OF_YEAR), 0);
    }

    @Override
    public String toString() {
        return day + "." + hour;
    }

}
