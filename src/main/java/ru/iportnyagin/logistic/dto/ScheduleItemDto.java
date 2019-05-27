package ru.iportnyagin.logistic.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;
import ru.iportnyagin.logistic.ScheduleItem;

/**
 * ScheduleItem - элемент расписания
 * startAt - начальный момент времени
 * duration - продолжительность для расчета конечного момента
 */
@NoArgsConstructor
public class ScheduleItemDto {

    private DateTimeDto startAt;
    @JsonIgnore
    private DateTimeDto endAt;
    private int duration;

    public ScheduleItem toScheduleItem() {
        return new ScheduleItem(startAt.toDateTime(), duration);
    }

    public DateTimeDto getStartAt() {
        return startAt;
    }

    public void setStartAt(DateTimeDto startAt) {
        this.startAt = startAt;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public DateTimeDto getEndAt() {
        return endAt;
    }

    public void setEndAt(DateTimeDto endAt) {
        this.endAt = endAt;
    }
}





