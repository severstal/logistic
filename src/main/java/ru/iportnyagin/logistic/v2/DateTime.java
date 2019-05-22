package ru.iportnyagin.logistic.v2;

import lombok.Getter;

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

    @Override
    public String toString() {
        return day + "." + hour;
    }

}
