package ru.iportnyagin.logistic.v2;

import lombok.Getter;
import ru.iportnyagin.logistic.v2.dto.BranchDto;
import ru.iportnyagin.logistic.v2.dto.RouteDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ConfigImpl.
 */
@Getter
public class ConfigImpl implements Config {

    private List<BranchDto> branches;
    private List<RouteDto> routes = new ArrayList<>();

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
                                                               .dateTime(new DateTime(0, 8))
                                                               .intValue(10)
                                                               .buildWorkDaysForYear(),
                                                2);

        final BranchDto branchC = new BranchDto("C",
                                                ScheduleBuilder.builder()
                                                               .dateTime(new DateTime(0, 9))
                                                               .intValue(8)
                                                               .buildWorkDaysForYear(),
                                                5);

        final BranchDto branchD = new BranchDto("D",
                                                ScheduleBuilder.builder()
                                                               .dateTime(new DateTime(0, 8))
                                                               .intValue(14)
                                                               .buildWorkDaysForYear(),
                                                2);

        final BranchDto branchE = new BranchDto("E",
                                                ScheduleBuilder.builder()
                                                               .dateTime(new DateTime(0, 10))
                                                               .intValue(12)
                                                               .buildWorkDaysForYear(),
                                                5);

        final BranchDto branchF = new BranchDto("F",
                                                ScheduleBuilder.builder()
                                                               .dateTime(new DateTime(0, 10))
                                                               .intValue(8)
                                                               .buildWorkDaysForYear(),
                                                4);

        final BranchDto branchG = new BranchDto("G",
                                                ScheduleBuilder.builder()
                                                               .dateTime(new DateTime(0, 8))
                                                               .intValue(10)
                                                               .buildWorkDaysForYear(),
                                                3);

        final BranchDto branchH = new BranchDto("H",
                                                ScheduleBuilder.builder()
                                                               .dateTime(new DateTime(0, 10))
                                                               .intValue(10)
                                                               .buildWorkDaysForYear(),
                                                2);

        branches = Arrays.asList(
                branchA, branchB, branchC, branchD, branchE, branchF, branchG, branchH
        );

        routes.addAll(RouteBuilder.instance()
                                  .name("ab")
                                  .from(branchA)
                                  .to(branchB)
                                  .startingAt(new DateTime(0, 7))
                                  .duration(1)
                                  .repeatPeriodInHour(24)
                                  .repeatCount(5)
                                  .build());

        routes.addAll(RouteBuilder.instance()
                                  .name("ba")
                                  .from(branchB)
                                  .to(branchA)
                                  .startingAt(new DateTime(0, 10))
                                  .duration(1)
                                  .repeatPeriodInHour(24)
                                  .repeatCount(5)
                                  .build());

        routes.addAll(RouteBuilder.instance()
                                  .name("ac")
                                  .from(branchA)
                                  .to(branchC)
                                  .startingAt(new DateTime(0, 8))
                                  .duration(2)
                                  .repeatPeriodInHour(24)
                                  .repeatCount(5)
                                  .build());

        routes.addAll(RouteBuilder.instance()
                                  .name("ca")
                                  .from(branchC)
                                  .to(branchA)
                                  .startingAt(new DateTime(0, 12))
                                  .duration(2)
                                  .repeatPeriodInHour(24)
                                  .repeatCount(5)
                                  .build());

        routes.addAll(RouteBuilder.instance()
                                  .name("af")
                                  .from(branchA)
                                  .to(branchF)
                                  .startingAt(new DateTime(0, 10))
                                  .duration(3)
                                  .repeatPeriodInHour(24)
                                  .repeatCount(5)
                                  .build());

        routes.addAll(RouteBuilder.instance()
                                  .name("fa")
                                  .from(branchF)
                                  .to(branchA)
                                  .startingAt(new DateTime(0, 15))
                                  .duration(3)
                                  .repeatPeriodInHour(24)
                                  .repeatCount(5)
                                  .build());

        routes.addAll(RouteBuilder.instance()
                                  .name("bc")
                                  .from(branchB)
                                  .to(branchC)
                                  .startingAt(new DateTime(0, 12))
                                  .duration(1)
                                  .repeatPeriodInHour(24)
                                  .repeatCount(5)
                                  .build());

        routes.addAll(RouteBuilder.instance()
                                  .name("cb")
                                  .from(branchC)
                                  .to(branchB)
                                  .startingAt(new DateTime(0, 16))
                                  .duration(1)
                                  .repeatPeriodInHour(24)
                                  .repeatCount(5)
                                  .build());


        routes.addAll(RouteBuilder.instance()
                                  .name("cd")
                                  .from(branchC)
                                  .to(branchD)
                                  .startingAt(new DateTime(0, 11))
                                  .duration(3)
                                  .repeatPeriodInHour(24)
                                  .repeatCount(5)
                                  .build());

        routes.addAll(RouteBuilder.instance()
                                  .name("dc")
                                  .from(branchD)
                                  .to(branchC)
                                  .startingAt(new DateTime(0, 10))
                                  .duration(3)
                                  .repeatPeriodInHour(24)
                                  .repeatCount(5)
                                  .build());

        routes.addAll(RouteBuilder.instance()
                                  .name("bd")
                                  .from(branchB)
                                  .to(branchD)
                                  .startingAt(new DateTime(0, 8))
                                  .duration(2)
                                  .repeatPeriodInHour(48)
                                  .repeatCount(2)
                                  .build());

        routes.addAll(RouteBuilder.instance()
                                  .name("be")
                                  .from(branchB)
                                  .to(branchE)
                                  .startingAt(new DateTime(0, 8))
                                  .duration(5)
                                  .repeatPeriodInHour(24)
                                  .repeatCount(5)
                                  .build());

        routes.addAll(RouteBuilder.instance()
                                  .name("eb")
                                  .from(branchE)
                                  .to(branchB)
                                  .startingAt(new DateTime(0, 11))
                                  .duration(5)
                                  .repeatPeriodInHour(24)
                                  .repeatCount(5)
                                  .build());

        routes.addAll(RouteBuilder.instance()
                                  .name("de")
                                  .from(branchD)
                                  .to(branchE)
                                  .startingAt(new DateTime(0, 16))
                                  .duration(2)
                                  .repeatPeriodInHour(24)
                                  .repeatCount(5)
                                  .build());

        routes.addAll(RouteBuilder.instance()
                                  .name("eh")
                                  .from(branchE)
                                  .to(branchH)
                                  .startingAt(new DateTime(0, 11))
                                  .duration(2)
                                  .repeatPeriodInHour(24)
                                  .repeatCount(5)
                                  .build());

        routes.addAll(RouteBuilder.instance()
                                  .name("he")
                                  .from(branchH)
                                  .to(branchE)
                                  .startingAt(new DateTime(0, 15))
                                  .duration(2)
                                  .repeatPeriodInHour(24)
                                  .repeatCount(5)
                                  .build());

        routes.addAll(RouteBuilder.instance()
                                  .name("hf")
                                  .from(branchH)
                                  .to(branchF)
                                  .startingAt(new DateTime(0, 12))
                                  .duration(2)
                                  .repeatPeriodInHour(24)
                                  .repeatCount(5)
                                  .build());

        routes.addAll(RouteBuilder.instance()
                                  .name("fh")
                                  .from(branchF)
                                  .to(branchH)
                                  .startingAt(new DateTime(0, 12))
                                  .duration(2)
                                  .repeatPeriodInHour(24)
                                  .repeatCount(5)
                                  .build());

        routes.addAll(RouteBuilder.instance()
                                  .name("fg")
                                  .from(branchF)
                                  .to(branchG)
                                  .startingAt(new DateTime(0, 11))
                                  .duration(2)
                                  .repeatPeriodInHour(24)
                                  .repeatCount(5)
                                  .build());

        routes.addAll(RouteBuilder.instance()
                                  .name("gf")
                                  .from(branchG)
                                  .to(branchF)
                                  .startingAt(new DateTime(0, 15))
                                  .duration(2)
                                  .repeatPeriodInHour(24)
                                  .repeatCount(5)
                                  .build());

    }

    public List<RouteDto> getOutgoingRoutes(BranchDto branchDto, DateTime fromDate, DateTime toDate) {
        return routes.stream()
                     .filter(r -> r.getFrom().equals(branchDto)
                             && r.getStartingAt().after(fromDate)
                             && r.getStartingAt().before(toDate))
                     .sorted((a, b) -> a.getStartingAt().after(b.getStartingAt()) ? 1 : -1)
                     .collect(Collectors.toList());
    }

}
