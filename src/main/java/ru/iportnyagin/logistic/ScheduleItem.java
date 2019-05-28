package ru.iportnyagin.logistic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;

/**
 * ScheduleItem - элемент расписания
 * startAt - начальный момент времени
 * duration - продолжительность для расчета конечного момента
 */
@Getter
@RequiredArgsConstructor
public class ScheduleItem {

    private final DateTime startAt;
    private final int duration;

    @JsonIgnore
    public DateTime getEndAt() {
        return new DateTime(startAt, duration);
    }

    public static class StartAtComparator implements Comparator<ScheduleItem> {

        @Override
        public int compare(ScheduleItem o1, ScheduleItem o2) {
            if (o1.getStartAt().equals(o2.getStartAt())) {
                return 0;
            } else {
                return o1.getStartAt().after(o2.getStartAt()) ? 1 : -1;
            }
        }
    }

}





