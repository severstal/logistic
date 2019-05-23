package ru.iportnyagin.logistic.v2;

import ru.iportnyagin.logistic.v2.dto.BranchDto;
import ru.iportnyagin.logistic.v2.dto.RouteDto;

/**
 * RouteBuilder.
 */
public class RouteBuilder {

    private String name;
    private BranchDto from;
    private BranchDto to;
    private DateTime startingAt;
    private int duration;
    private int repeatPeriodInHour;
    private int repeatCount;

    private RouteBuilder() {
    }

    public static RouteBuilder instance() {
        return new RouteBuilder();
    }

    public RouteBuilder name(String name) {
        this.name = name;
        return this;
    }

    public RouteBuilder from(BranchDto from) {
        this.from = from;
        return this;
    }

    public RouteBuilder to(BranchDto to) {
        this.to = to;
        return this;
    }

    public RouteBuilder startingAt(DateTime startingAt) {
        this.startingAt = startingAt;
        return this;
    }

    public RouteBuilder duration(int duration) {
        this.duration = duration;
        return this;
    }

    public RouteBuilder repeatPeriodInHour(int repeatPeriodInHour) {
        this.repeatPeriodInHour = repeatPeriodInHour;
        return this;
    }

    public RouteBuilder repeatCount(int repeatCount) {
        this.repeatCount = repeatCount;
        return this;
    }

    public RouteDto build() {
        return new RouteDto(name,
                            from,
                            to,
                            ScheduleBuilder.builder()
                                           .dateTime(startingAt)
                                           .intValue(duration)
                                           .repeatPeriodInHour(repeatPeriodInHour)
                                           .repeatCount(repeatCount)
                                           .build());
    }

}
