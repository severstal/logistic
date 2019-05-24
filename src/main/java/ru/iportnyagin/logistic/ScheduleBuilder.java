package ru.iportnyagin.logistic;

import java.util.ArrayList;
import java.util.List;

/**
 * ScheduleBuilder.
 */
public class ScheduleBuilder {

    private DateTime dateTime;
    private int hour;
    private int intValue;
    private int repeatPeriodInHour;
    private int repeatCount;

    private ScheduleBuilder() {
    }

    public static ScheduleBuilder builder() {
        return new ScheduleBuilder();
    }

    public ScheduleBuilder dateTime(DateTime dt) {
        dateTime = dt;
        return this;
    }

    public ScheduleBuilder hour(int h) {
        hour = h;
        return this;
    }

    public ScheduleBuilder intValue(int v) {
        intValue = v;
        return this;
    }

    public ScheduleBuilder repeatPeriodInHour(int r) {
        repeatPeriodInHour = r;
        return this;
    }

    public ScheduleBuilder repeatCount(int r) {
        repeatCount = r;
        return this;
    }

    public List<ScheduleItem> build() {
        List<ScheduleItem> result = new ArrayList<>();

        for (int i = 0; i < repeatCount; i++) {
            result.add(new ScheduleItem(
                    new DateTime(dateTime, i * repeatPeriodInHour), intValue));
        }

        return result;
    }

    /**
     * создаст расписание пнд-птн с 0-го дня по 365. 0 день - пнд. високосности нет.
     */
    public List<ScheduleItem> buildWorkDaysForYear() {
        DateTime dateTime = new DateTime(0, hour);
        repeatPeriodInHour = 24;

        List<ScheduleItem> result = new ArrayList<>();

        for (int i = 0; i < 365; i++) {

            if (i % 6 == 0 || i % 7 == 0) {
                continue;
            }

            result.add(new ScheduleItem(new DateTime(dateTime, i * repeatPeriodInHour), intValue));
        }

        return result;
    }

}
