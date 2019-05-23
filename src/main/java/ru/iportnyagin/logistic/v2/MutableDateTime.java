package ru.iportnyagin.logistic.v2;

/**
 * MutableDateTime.
 */
public class MutableDateTime {

    private DateTime dateTime;

    public MutableDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    public DateTime getDateTime() {
        return this.dateTime;
    }

    public void addHour(int hour) {
        this.dateTime = new DateTime(this.dateTime.getDay(), this.dateTime.getHour() + hour);
    }

    public int getDay() {
        return this.dateTime.getDay();
    }

    public int getHour() {
        return this.dateTime.getHour();
    }

}
