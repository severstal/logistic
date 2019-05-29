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
                                         branchA,
                                         branchB,
                                         ScheduleBuilder.builder()
                                                           .dateTime(new DateTime(0, 7))
                                                           .intValue(1)
                                                           .repeatPeriodInHour(24)
                                                           .repeatCount(365)
                                                           .build()),

                               new Route("ba",
                                         branchB,
                                         branchA,
                                         ScheduleBuilder.builder()
                                                           .dateTime(new DateTime(0, 10))
                                                           .intValue(1)
                                                           .repeatPeriodInHour(24)
                                                           .repeatCount(365)
                                                           .build()),

                               new Route("ac",
                                         branchA,
                                         branchC,
                                         ScheduleBuilder.builder()
                                                           .dateTime(new DateTime(0, 8))
                                                           .intValue(2)
                                                           .repeatPeriodInHour(24)
                                                           .repeatCount(365)
                                                           .build()),

                               new Route("ca",
                                         branchC,
                                         branchA,
                                         ScheduleBuilder.builder()
                                                           .dateTime(new DateTime(0, 12))
                                                           .intValue(2)
                                                           .repeatPeriodInHour(24)
                                                           .repeatCount(365)
                                                           .build()),

                               new Route("af",
                                         branchA,
                                         branchF,
                                         ScheduleBuilder.builder()
                                                           .dateTime(new DateTime(0, 10))
                                                           .intValue(3)
                                                           .repeatPeriodInHour(24)
                                                           .repeatCount(365)
                                                           .build()),

                               new Route("fa",
                                         branchF,
                                         branchA,
                                         ScheduleBuilder.builder()
                                                           .dateTime(new DateTime(0, 15))
                                                           .intValue(3)
                                                           .repeatPeriodInHour(24)
                                                           .repeatCount(365)
                                                           .build()),

                               new Route("bc",
                                         branchB,
                                         branchC,
                                         ScheduleBuilder.builder()
                                                           .dateTime(new DateTime(0, 12))
                                                           .intValue(1)
                                                           .repeatPeriodInHour(24)
                                                           .repeatCount(365)
                                                           .build()),

                               new Route("cb",
                                         branchC,
                                         branchB,
                                         ScheduleBuilder.builder()
                                                           .dateTime(new DateTime(0, 16))
                                                           .intValue(1)
                                                           .repeatPeriodInHour(24)
                                                           .repeatCount(365)
                                                           .build()),

                               new Route("cd",
                                         branchC,
                                         branchD,
                                         ScheduleBuilder.builder()
                                                           .dateTime(new DateTime(0, 11))
                                                           .intValue(3)
                                                           .repeatPeriodInHour(24)
                                                           .repeatCount(365)
                                                           .build()),

                               new Route("dc",
                                         branchD,
                                         branchC,
                                         ScheduleBuilder.builder()
                                                           .dateTime(new DateTime(0, 10))
                                                           .intValue(3)
                                                           .repeatPeriodInHour(24)
                                                           .repeatCount(365)
                                                           .build()),

                               new Route("bd",
                                         branchB,
                                         branchD,
                                         ScheduleBuilder.builder()
                                                           .dateTime(new DateTime(0, 8))
                                                           .intValue(2)
                                                           .repeatPeriodInHour(48)
                                                           .repeatCount(365)
                                                           .build()),

                               new Route("be",
                                         branchB,
                                         branchE,
                                         ScheduleBuilder.builder()
                                                           .dateTime(new DateTime(0, 8))
                                                           .intValue(5)
                                                           .repeatPeriodInHour(24)
                                                           .repeatCount(365)
                                                           .build()),

                               new Route("eb",
                                         branchE,
                                         branchB,
                                         ScheduleBuilder.builder()
                                                           .dateTime(new DateTime(0, 11))
                                                           .intValue(5)
                                                           .repeatPeriodInHour(24)
                                                           .repeatCount(365)
                                                           .build()),

                               new Route("de",
                                         branchD,
                                         branchE,
                                         ScheduleBuilder.builder()
                                                           .hour(16)
                                                           .intValue(2)
                                                           .buildWorkDaysForYear()),

                               new Route("eh",
                                         branchE,
                                         branchH,
                                         ScheduleBuilder.builder()
                                                           .hour(11)
                                                           .intValue(2)
                                                           .buildWorkDaysForYear()),

                               new Route("he",
                                         branchH,
                                         branchE,
                                         ScheduleBuilder.builder()
                                                           .hour(15)
                                                           .intValue(2)
                                                           .buildWorkDaysForYear()),

                               new Route("hf",
                                         branchH,
                                         branchF,
                                         ScheduleBuilder.builder()
                                                           .hour(12)
                                                           .intValue(2)
                                                           .buildWorkDaysForYear()),

                               new Route("fh",
                                         branchF,
                                         branchH,
                                         ScheduleBuilder.builder()
                                                           .hour(12)
                                                           .intValue(2)
                                                           .buildWorkDaysForYear()),

                               new Route("fg",
                                         branchF,
                                         branchG,
                                         ScheduleBuilder.builder()
                                                           .hour(11)
                                                           .intValue(2)
                                                           .buildWorkDaysForYear()),

                               new Route("gf",
                                         branchG,
                                         branchF,
                                         ScheduleBuilder.builder()
                                                           .hour(15)
                                                           .intValue(2)
                                                           .buildWorkDaysForYear())
        );

    }

}
