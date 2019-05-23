package ru.iportnyagin.logistic.v2.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.iportnyagin.logistic.v2.DateTime;
import ru.iportnyagin.logistic.v2.ScheduleItem;

/**
 * RideDto - одна поездка по расписанию из маршрута
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
public class RideDto {

    private final String description;
    private final BranchDto from;
    private final BranchDto to;
    private final ScheduleItem schedule;

    public DateTime getArrivingAt() {
        return new DateTime(schedule.getDateTime(), schedule.getIntValue());
    }

    @Override
    public String toString() {
        return description + " at " + schedule.getDateTime() + " to " + getArrivingAt();
    }

}
