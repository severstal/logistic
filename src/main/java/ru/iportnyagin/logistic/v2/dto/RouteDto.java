package ru.iportnyagin.logistic.v2.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.iportnyagin.logistic.v2.DateTime;

/**
 * RouteDto
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
public class RouteDto {

    private final String description;
    private final BranchDto from;
    private final BranchDto to;
    private final DateTime startingAt; // todo replace with ScheduleItem
    private final int duration;

    public DateTime getArrivingAt() {
        return new DateTime(startingAt, duration);
    }

    @Override
    public String toString() {
        return description + " " + startingAt;
    }

}
