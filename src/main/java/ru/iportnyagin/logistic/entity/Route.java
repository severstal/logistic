package ru.iportnyagin.logistic.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.iportnyagin.logistic.ScheduleItem;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Route - маршрут, откуда, куда, расписание
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
public class Route {

    private final String description;
    private final String fromBranch;
    private final String toBranch;
    private final List<ScheduleItem> schedule;

    @Override
    public String toString() {
        return description + " " + schedule.stream()
                                           .limit(5)
                                           .map(ScheduleItem::getStartAt)
                                           .collect(Collectors.toList());
    }

}
