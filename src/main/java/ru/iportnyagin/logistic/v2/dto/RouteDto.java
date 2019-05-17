package ru.iportnyagin.logistic.v2.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

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
    private final String name;
    private final BranchDto from;
    private final BranchDto to;
    private final DateTime startingAt;
    private final int duration;

    public DateTime getArrivingAt() {
        return DateTime.from(startingAt).addHour(duration);
    }

    @Override
    public String toString() {
        return name;
    }
}
