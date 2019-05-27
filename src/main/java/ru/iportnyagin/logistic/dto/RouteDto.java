package ru.iportnyagin.logistic.dto;

import lombok.NoArgsConstructor;
import ru.iportnyagin.logistic.entity.Route;

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
@NoArgsConstructor
public class RouteDto {

    private String description;
    private String fromBranch;
    private String toBranch;
    private List<ScheduleItemDto> schedule;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFromBranch() {
        return fromBranch;
    }

    public void setFromBranch(String fromBranch) {
        this.fromBranch = fromBranch;
    }

    public String getToBranch() {
        return toBranch;
    }

    public void setToBranch(String toBranch) {
        this.toBranch = toBranch;
    }

    public List<ScheduleItemDto> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<ScheduleItemDto> schedule) {
        this.schedule = schedule;
    }

    public Route toRoute() {
        return new Route(description,
                         fromBranch,
                         toBranch,
                         schedule.stream()
                                 .map(s -> s.toScheduleItem())
                                 .collect(Collectors.toList()));
    }

}
