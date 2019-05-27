package ru.iportnyagin.logistic;

import lombok.Getter;
import ru.iportnyagin.logistic.entity.Branch;
import ru.iportnyagin.logistic.entity.Route;

import java.util.Arrays;
import java.util.List;

/**
 * DefaultConfig.
 */
@Getter
public class DefaultConfig implements Config {

    private List<Branch> branches;
    private List<Route> routes;

    public DefaultConfig() {

        final Branch branchA = new Branch("A",
                                          ScheduleBuilder.builder() // круглосуточно без перерывов и выходных
                                                               .dateTime(new DateTime(0, 0))
                                                               .intValue(24)
                                                               .repeatPeriodInHour(24)
                                                               .repeatCount(365)
                                                               .build(),
                                          3);

        final Branch branchB = new Branch("B",
                                          ScheduleBuilder.builder()
                                                               .hour(8)
                                                               .intValue(10)
                                                               .buildWorkDaysForYear(),
                                          2);

        final Branch branchC = new Branch("C",
                                          ScheduleBuilder.builder()
                                                               .hour(9)
                                                               .intValue(8)
                                                               .buildWorkDaysForYear(),
                                          5);

        final Branch branchD = new Branch("D",
                                          ScheduleBuilder.builder()
                                                               .hour(8)
                                                               .intValue(14)
                                                               .buildWorkDaysForYear(),
                                          2);

        final Branch branchE = new Branch("E",
                                          ScheduleBuilder.builder()
                                                               .hour(10)
                                                               .intValue(12)
                                                               .buildWorkDaysForYear(),
                                          5);

        final Branch branchF = new Branch("F",
                                          ScheduleBuilder.builder()
                                                               .hour(10)
                                                               .intValue(8)
                                                               .buildWorkDaysForYear(),
                                          4);

        final Branch branchG = new Branch("G",
                                          ScheduleBuilder.builder()
                                                               .hour(8)
                                                               .intValue(10)
                                                               .buildWorkDaysForYear(),
                                          3);

        final Branch branchH = new Branch("H",
                                          ScheduleBuilder.builder()
                                                               .hour(10)
                                                               .intValue(10)
                                                               .buildWorkDaysForYear(),
                                          2);

        branches = Arrays.asList(
                branchA, branchB, branchC, branchD, branchE, branchF, branchG, branchH
        );

        routes = Arrays.asList(new Route("ab",
                                         "A",
                                         "B",
                                         ScheduleBuilder.builder()
                                                           .dateTime(new DateTime(0, 7))
                                                           .intValue(1)
                                                           .repeatPeriodInHour(24)
                                                           .repeatCount(365)
                                                           .build()),

                               new Route("ba",
                                         "B",
                                         "A",
                                         ScheduleBuilder.builder()
                                                           .dateTime(new DateTime(0, 10))
                                                           .intValue(1)
                                                           .repeatPeriodInHour(24)
                                                           .repeatCount(365)
                                                           .build()),

                               new Route("ac",
                                         "A",
                                         "C",
                                         ScheduleBuilder.builder()
                                                           .dateTime(new DateTime(0, 8))
                                                           .intValue(2)
                                                           .repeatPeriodInHour(24)
                                                           .repeatCount(365)
                                                           .build()),

                               new Route("ca",
                                         "C",
                                         "A",
                                         ScheduleBuilder.builder()
                                                           .dateTime(new DateTime(0, 12))
                                                           .intValue(2)
                                                           .repeatPeriodInHour(24)
                                                           .repeatCount(365)
                                                           .build()),

                               new Route("af",
                                         "A",
                                         "F",
                                         ScheduleBuilder.builder()
                                                           .dateTime(new DateTime(0, 10))
                                                           .intValue(3)
                                                           .repeatPeriodInHour(24)
                                                           .repeatCount(365)
                                                           .build()),

                               new Route("fa",
                                         "F",
                                         "A",
                                         ScheduleBuilder.builder()
                                                           .dateTime(new DateTime(0, 15))
                                                           .intValue(3)
                                                           .repeatPeriodInHour(24)
                                                           .repeatCount(365)
                                                           .build()),

                               new Route("bc",
                                         "B",
                                         "C",
                                         ScheduleBuilder.builder()
                                                           .dateTime(new DateTime(0, 12))
                                                           .intValue(1)
                                                           .repeatPeriodInHour(24)
                                                           .repeatCount(365)
                                                           .build()),

                               new Route("cb",
                                         "C",
                                         "B",
                                         ScheduleBuilder.builder()
                                                           .dateTime(new DateTime(0, 16))
                                                           .intValue(1)
                                                           .repeatPeriodInHour(24)
                                                           .repeatCount(365)
                                                           .build()),

                               new Route("cd",
                                         "C",
                                         "D",
                                         ScheduleBuilder.builder()
                                                           .dateTime(new DateTime(0, 11))
                                                           .intValue(3)
                                                           .repeatPeriodInHour(24)
                                                           .repeatCount(365)
                                                           .build()),

                               new Route("dc",
                                         "D",
                                         "C",
                                         ScheduleBuilder.builder()
                                                           .dateTime(new DateTime(0, 10))
                                                           .intValue(3)
                                                           .repeatPeriodInHour(24)
                                                           .repeatCount(365)
                                                           .build()),

                               new Route("bd",
                                         "B",
                                         "D",
                                         ScheduleBuilder.builder()
                                                           .dateTime(new DateTime(0, 8))
                                                           .intValue(2)
                                                           .repeatPeriodInHour(48)
                                                           .repeatCount(365)
                                                           .build()),

                               new Route("be",
                                         "B",
                                         "E",
                                         ScheduleBuilder.builder()
                                                           .dateTime(new DateTime(0, 8))
                                                           .intValue(5)
                                                           .repeatPeriodInHour(24)
                                                           .repeatCount(365)
                                                           .build()),

                               new Route("eb",
                                         "E",
                                         "B",
                                         ScheduleBuilder.builder()
                                                           .dateTime(new DateTime(0, 11))
                                                           .intValue(5)
                                                           .repeatPeriodInHour(24)
                                                           .repeatCount(365)
                                                           .build()),

                               new Route("de",
                                         "D",
                                         "E",
                                         ScheduleBuilder.builder()
                                                           .hour(16)
                                                           .intValue(2)
                                                           .buildWorkDaysForYear()),

                               new Route("eh",
                                         "E",
                                         "H",
                                         ScheduleBuilder.builder()
                                                           .hour(11)
                                                           .intValue(2)
                                                           .buildWorkDaysForYear()),

                               new Route("he",
                                         "H",
                                         "E",
                                         ScheduleBuilder.builder()
                                                           .hour(15)
                                                           .intValue(2)
                                                           .buildWorkDaysForYear()),

                               new Route("hf",
                                         "H",
                                         "F",
                                         ScheduleBuilder.builder()
                                                           .hour(12)
                                                           .intValue(2)
                                                           .buildWorkDaysForYear()),

                               new Route("fh",
                                         "F",
                                         "H",
                                         ScheduleBuilder.builder()
                                                           .hour(12)
                                                           .intValue(2)
                                                           .buildWorkDaysForYear()),

                               new Route("fg",
                                         "F",
                                         "G",
                                         ScheduleBuilder.builder()
                                                           .hour(11)
                                                           .intValue(2)
                                                           .buildWorkDaysForYear()),

                               new Route("gf",
                                         "G",
                                         "F",
                                         ScheduleBuilder.builder()
                                                           .hour(15)
                                                           .intValue(2)
                                                           .buildWorkDaysForYear())
        );

    }

}
