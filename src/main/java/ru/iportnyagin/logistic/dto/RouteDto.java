package ru.iportnyagin.logistic.dto;

import lombok.Data;
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
@Data
@RequiredArgsConstructor
public class RouteDto {
    private final  String name;
    private final BranchDto from;
    private final  BranchDto to;
    private final  int startingAt;
    private final  int arrivingAt;
    private final  int duration;

    @Override
    public String toString() {
        return name;
    }
}
