package ru.iportnyagin.logistic.v2;

import java.util.ArrayList;
import java.util.List;

/**
 * ScheduleBuilder.
 */
public class ScheduleBuilder {

    private DateTime dateTime;
    private int hour;
    private int intValue;
    private String stringValue;
    private int repeatPeriodInHour;
    private int repeatCount;

    private ScheduleBuilder() {
    }

    public static ScheduleBuilder builder() {
        return new ScheduleBuilder();
    }

    public ScheduleBuilder dateTime(DateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public ScheduleBuilder hour(int hour) {
        this.hour = hour;
        return this;
    }

    public ScheduleBuilder intValue(int intValue) {
        this.intValue = intValue;
        return this;
    }

    public ScheduleBuilder repeatPeriodInHour(int repeatPeriodInHour) {
        this.repeatPeriodInHour = repeatPeriodInHour;
        return this;
    }

    public ScheduleBuilder repeatCount(int repeatCount) {
        this.repeatCount = repeatCount;
        return this;
    }

    public ScheduleBuilder stringValue(String stringValue) {
        this.stringValue = stringValue;
        return this;
    }

    public List<ScheduleItem> build() {
        List<ScheduleItem> result = new ArrayList<>();

        for (int i = 0; i < repeatCount; i++) {
            result.add(new ScheduleItem(
                    new DateTime(dateTime, i * repeatPeriodInHour),
                    intValue,
                    stringValue));
        }

        return result;
    }

    /**
     * создаст расписание пнд-птн с 0-го дня по 365. 0 день - пнд. високосности нет.
     */
    public List<ScheduleItem> buildWorkDaysForYear() {
        DateTime dateTime = new DateTime(0, this.hour);
        this.repeatPeriodInHour = 24;

        List<ScheduleItem> result = new ArrayList<>();

        for (int i = 0; i < 365; i++) {

            if (i % 6 == 0 || i % 7 == 0) {
                continue;
            }

            result.add(new ScheduleItem(new DateTime(dateTime, i * repeatPeriodInHour),
                                        intValue,
                                        stringValue));
        }

        return result;
    }

}
