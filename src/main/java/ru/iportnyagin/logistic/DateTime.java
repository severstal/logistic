package ru.iportnyagin.logistic;

import lombok.Getter;

import java.util.Calendar;

/**
 * DateTime.
 */
@Getter
public class DateTime {

    private final int day;
    private final int hour;

    public DateTime(int sDay, int sHour) {
        int gHour = sDay * 24 + sHour;
        day = gHour / 24;
        hour = gHour % 24;
    }

    public DateTime(DateTime dateTime, int addHour) {
        this(dateTime.day, dateTime.hour + addHour);
    }

    private int toGlobalHour() {
        return day * 24 + hour;
    }

    public int hoursBetween(DateTime dateTime) {
        return toGlobalHour() - dateTime.toGlobalHour();
    }

    public boolean after(DateTime dateTime) {
        return toGlobalHour() >= dateTime.toGlobalHour();
    }

    public boolean before(DateTime dateTime) {
        return toGlobalHour() < dateTime.toGlobalHour();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DateTime dateTime = (DateTime) o;

        if (day != dateTime.day) return false;
        return hour == dateTime.hour;
    }

    @Override
    public int hashCode() {
        int result = day;
        result = 31 * result + hour;
        return result;
    }

/*
    public static class DateTimeComparator implements Comparator<DateTime> {

        @Override
        public int compare(DateTime o1, DateTime o2) {
            if (o1.equals(o2)) {
                return 0;
            } else {
                return o1.after(o2) ? 1 : -1;
            }
        }
    }
*/

}
