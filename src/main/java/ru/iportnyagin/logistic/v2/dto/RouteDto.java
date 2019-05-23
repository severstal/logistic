package ru.iportnyagin.logistic.v2.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.iportnyagin.logistic.v2.ScheduleItem;

import java.util.List;
import java.util.stream.Collectors;

/**
 * RouteDto - маршрут, откуда, куда, расписание
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
    private final List<ScheduleItem> schedule;

    @Override
    public String toString() {
        return description + " " + schedule.stream()
                                           .limit(5)
                                           .map(item -> item.getDateTime())
                                           .collect(Collectors.toList());
    }

}
