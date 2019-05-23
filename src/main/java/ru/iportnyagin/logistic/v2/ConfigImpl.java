package ru.iportnyagin.logistic.v2;

import lombok.Getter;
import ru.iportnyagin.logistic.v2.dto.BranchDto;
import ru.iportnyagin.logistic.v2.dto.RouteDto;

import java.util.Arrays;
import java.util.List;

/**
 * ConfigImpl.
 */
@Getter
public class ConfigImpl implements Config {

    private List<BranchDto> branches;
    private List<RouteDto> routes;

    public ConfigImpl() {

        final BranchDto branchA = new BranchDto("A",
                                                ScheduleBuilder.builder() // круглосуточно без перерывов и выходных
                                                               .dateTime(new DateTime(0, 0))
                                                               .intValue(24)
                                                               .repeatPeriodInHour(24)
                                                               .repeatCount(365)
                                                               .build(),
                                                3);

        final BranchDto branchB = new BranchDto("B",
                                                ScheduleBuilder.builder()
                                                               .hour(8)
                                                               .intValue(10)
                                                               .buildWorkDaysForYear(),
                                                2);

        final BranchDto branchC = new BranchDto("C",
                                                ScheduleBuilder.builder()
                                                               .hour(9)
                                                               .intValue(8)
                                                               .buildWorkDaysForYear(),
                                                5);

        final BranchDto branchD = new BranchDto("D",
                                                ScheduleBuilder.builder()
                                                               .hour(8)
                                                               .intValue(14)
                                                               .buildWorkDaysForYear(),
                                                2);

        final BranchDto branchE = new BranchDto("E",
                                                ScheduleBuilder.builder()
                                                               .hour(10)
                                                               .intValue(12)
                                                               .buildWorkDaysForYear(),
                                                5);

        final BranchDto branchF = new BranchDto("F",
                                                ScheduleBuilder.builder()
                                                               .hour(10)
                                                               .intValue(8)
                                                               .buildWorkDaysForYear(),
                                                4);

        final BranchDto branchG = new BranchDto("G",
                                                ScheduleBuilder.builder()
                                                               .hour(8)
                                                               .intValue(10)
                                                               .buildWorkDaysForYear(),
                                                3);

        final BranchDto branchH = new BranchDto("H",
                                                ScheduleBuilder.builder()
                                                               .hour(10)
                                                               .intValue(10)
                                                               .buildWorkDaysForYear(),
                                                2);

        branches = Arrays.asList(
                branchA, branchB, branchC, branchD, branchE, branchF, branchG, branchH
        );

        routes = Arrays.asList(new RouteDto("ab",
                                            branchA,
                                            branchB,
                                            ScheduleBuilder.builder()
                                                           .dateTime(new DateTime(0, 7))
                                                           .intValue(1)
                                                           .repeatPeriodInHour(24)
                                                           .repeatCount(365)
                                                           .build()),

                               new RouteDto("ba",
                                            branchB,
                                            branchA,
                                            ScheduleBuilder.builder()
                                                           .dateTime(new DateTime(0, 10))
                                                           .intValue(1)
                                                           .repeatPeriodInHour(24)
                                                           .repeatCount(365)
                                                           .build()),

                               new RouteDto("ac",
                                            branchA,
                                            branchC,
                                            ScheduleBuilder.builder()
                                                           .dateTime(new DateTime(0, 8))
                                                           .intValue(2)
                                                           .repeatPeriodInHour(24)
                                                           .repeatCount(365)
                                                           .build()),

                               new RouteDto("ca",
                                            branchC,
                                            branchA,
                                            ScheduleBuilder.builder()
                                                           .dateTime(new DateTime(0, 12))
                                                           .intValue(2)
                                                           .repeatPeriodInHour(24)
                                                           .repeatCount(365)
                                                           .build()),

                               new RouteDto("af",
                                            branchA,
                                            branchF,
                                            ScheduleBuilder.builder()
                                                           .dateTime(new DateTime(0, 10))
                                                           .intValue(3)
                                                           .repeatPeriodInHour(24)
                                                           .repeatCount(365)
                                                           .build()),

                               new RouteDto("fa",
                                            branchF,
                                            branchA,
                                            ScheduleBuilder.builder()
                                                           .dateTime(new DateTime(0, 15))
                                                           .intValue(3)
                                                           .repeatPeriodInHour(24)
                                                           .repeatCount(365)
                                                           .build()),

                               new RouteDto("bc",
                                            branchB,
                                            branchC,
                                            ScheduleBuilder.builder()
                                                           .dateTime(new DateTime(0, 12))
                                                           .intValue(1)
                                                           .repeatPeriodInHour(24)
                                                           .repeatCount(365)
                                                           .build()),

                               new RouteDto("cb",
                                            branchC,
                                            branchB,
                                            ScheduleBuilder.builder()
                                                           .dateTime(new DateTime(0, 16))
                                                           .intValue(1)
                                                           .repeatPeriodInHour(24)
                                                           .repeatCount(365)
                                                           .build()),

                               new RouteDto("cd",
                                            branchC,
                                            branchD,
                                            ScheduleBuilder.builder()
                                                           .dateTime(new DateTime(0, 11))
                                                           .intValue(3)
                                                           .repeatPeriodInHour(24)
                                                           .repeatCount(365)
                                                           .build()),

                               new RouteDto("dc",
                                            branchD,
                                            branchC,
                                            ScheduleBuilder.builder()
                                                           .dateTime(new DateTime(0, 10))
                                                           .intValue(3)
                                                           .repeatPeriodInHour(24)
                                                           .repeatCount(365)
                                                           .build()),

                               new RouteDto("bd",
                                            branchB,
                                            branchD,
                                            ScheduleBuilder.builder()
                                                           .dateTime(new DateTime(0, 8))
                                                           .intValue(2)
                                                           .repeatPeriodInHour(48)
                                                           .repeatCount(365)
                                                           .build()),

                               new RouteDto("be",
                                            branchB,
                                            branchE,
                                            ScheduleBuilder.builder()
                                                           .dateTime(new DateTime(0, 8))
                                                           .intValue(5)
                                                           .repeatPeriodInHour(24)
                                                           .repeatCount(365)
                                                           .build()),

                               new RouteDto("eb",
                                            branchE,
                                            branchB,
                                            ScheduleBuilder.builder()
                                                           .dateTime(new DateTime(0, 11))
                                                           .intValue(5)
                                                           .repeatPeriodInHour(24)
                                                           .repeatCount(365)
                                                           .build()),

                               new RouteDto("de",
                                            branchD,
                                            branchE,
                                            ScheduleBuilder.builder()
                                                           .hour(16)
                                                           .intValue(2)
                                                           .buildWorkDaysForYear()),

                               new RouteDto("eh",
                                            branchE,
                                            branchH,
                                            ScheduleBuilder.builder()
                                                           .hour(11)
                                                           .intValue(2)
                                                           .buildWorkDaysForYear()),

                               new RouteDto("he",
                                            branchH,
                                            branchE,
                                            ScheduleBuilder.builder()
                                                           .hour(15)
                                                           .intValue(2)
                                                           .buildWorkDaysForYear()),

                               new RouteDto("hf",
                                            branchH,
                                            branchF,
                                            ScheduleBuilder.builder()
                                                           .hour(12)
                                                           .intValue(2)
                                                           .buildWorkDaysForYear()),

                               new RouteDto("fh",
                                            branchF,
                                            branchH,
                                            ScheduleBuilder.builder()
                                                           .hour(12)
                                                           .intValue(2)
                                                           .buildWorkDaysForYear()),

                               new RouteDto("fg",
                                            branchF,
                                            branchG,
                                            ScheduleBuilder.builder()
                                                           .hour(11)
                                                           .intValue(2)
                                                           .buildWorkDaysForYear()),

                               new RouteDto("gf",
                                            branchG,
                                            branchF,
                                            ScheduleBuilder.builder()
                                                           .hour(15)
                                                           .intValue(2)
                                                           .buildWorkDaysForYear())
        );

    }

}
