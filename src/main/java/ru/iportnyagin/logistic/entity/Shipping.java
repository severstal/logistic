package ru.iportnyagin.logistic.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.iportnyagin.logistic.DateTime;
import ru.iportnyagin.logistic.ScheduleItem;

/**
 * Shipping - перевозка по маршруту
 * from
 * to
 * startingAt
 * arrivingAt
 * duration
 * volume (*)
 * weight (*)
 * routeLength (*)
 */
@Getter
@RequiredArgsConstructor
public class Shipping implements Stage {

    private final String description;
    private final Branch fromBranch;
    private final Branch toBranch;
    private final ScheduleItem schedule;

    // todo оставить getEndAt из Stage ?
    public DateTime getArrivingAt() {
        return schedule.getEndAt();
    }

    @Override
    public String toString() {
        return fromBranch.getId() + ":" + schedule.getStartAt() + "-" + toBranch.getId() + ":" + getArrivingAt() + "(" + schedule
                .getDuration() + "h)";
    }

    @Override
    public String details() {
        return "shipping:" + description + String.format("[%s %s]", startAt(), endAt());
    }

    @Override
    public DateTime startAt() {
        return schedule.getStartAt();
    }

    @Override
    public DateTime endAt() {
        return getArrivingAt();
    }
}
