package ru.iportnyagin.logistic.v2;

import lombok.Getter;
import ru.iportnyagin.logistic.v2.dto.BranchDto;
import ru.iportnyagin.logistic.v2.dto.DateTime;
import ru.iportnyagin.logistic.v2.dto.RouteDto;
import ru.iportnyagin.logistic.v2.dto.RouteBuilder;

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

        final BranchDto branchA = new BranchDto("A", 0, 24, 3);
        final BranchDto branchB = new BranchDto("B", 8, 18, 2);
        final BranchDto branchC = new BranchDto("C", 9, 17, 5);
        final BranchDto branchD = new BranchDto("D", 8, 20, 2);
        final BranchDto branchE = new BranchDto("E", 10, 20, 5);
        final BranchDto branchF = new BranchDto("F", 10, 18, 4);
        final BranchDto branchG = new BranchDto("G", 8, 18, 3);
        final BranchDto branchH = new BranchDto("H", 10, 20, 2);

        branches = Arrays.asList(
                branchA, branchB, branchC, branchD, branchE, branchF, branchG, branchH
        );

        routes.addAll(RouteBuilder.instance()
                                  .name("ab")
                                  .from(branchA)
                                  .to(branchB)
                                  .startingAt(DateTime.getCurrentDate().addHour(7))
                                  .duration(1)
                                  .repeatPeriodInHour(24)
                                  .repeatCount(5)
                                  .build());

        routes.addAll(RouteBuilder.instance()
                                  .name("ba")
                                  .from(branchB)
                                  .to(branchA)
                                  .startingAt(DateTime.getCurrentDate().addHour(10))
                                  .duration(1)
                                  .repeatPeriodInHour(24)
                                  .repeatCount(5)
                                  .build());

        routes.addAll(RouteBuilder.instance()
                                  .name("ac")
                                  .from(branchA)
                                  .to(branchC)
                                  .startingAt(DateTime.getCurrentDate().addHour(8))
                                  .duration(2)
                                  .repeatPeriodInHour(24)
                                  .repeatCount(5)
                                  .build());

        routes.addAll(RouteBuilder.instance()
                                  .name("ca")
                                  .from(branchC)
                                  .to(branchA)
                                  .startingAt(DateTime.getCurrentDate().addHour(12))
                                  .duration(2)
                                  .repeatPeriodInHour(24)
                                  .repeatCount(5)
                                  .build());

        routes.addAll(RouteBuilder.instance()
                                  .name("af")
                                  .from(branchA)
                                  .to(branchF)
                                  .startingAt(DateTime.getCurrentDate().addHour(10))
                                  .duration(3)
                                  .repeatPeriodInHour(24)
                                  .repeatCount(5)
                                  .build());

        routes.addAll(RouteBuilder.instance()
                                  .name("fa")
                                  .from(branchF)
                                  .to(branchA)
                                  .startingAt(DateTime.getCurrentDate().addHour(15))
                                  .duration(3)
                                  .repeatPeriodInHour(24)
                                  .repeatCount(5)
                                  .build());

        routes.addAll(RouteBuilder.instance()
                                  .name("bc")
                                  .from(branchB)
                                  .to(branchC)
                                  .startingAt(DateTime.getCurrentDate().addHour(12))
                                  .duration(1)
                                  .repeatPeriodInHour(24)
                                  .repeatCount(5)
                                  .build());

        routes.addAll(RouteBuilder.instance()
                                  .name("cb")
                                  .from(branchC)
                                  .to(branchB)
                                  .startingAt(DateTime.getCurrentDate().addHour(16))
                                  .duration(1)
                                  .repeatPeriodInHour(24)
                                  .repeatCount(5)
                                  .build());


        routes.addAll(RouteBuilder.instance()
                                  .name("cd")
                                  .from(branchC)
                                  .to(branchD)
                                  .startingAt(DateTime.getCurrentDate().addHour(11))
                                  .duration(3)
                                  .repeatPeriodInHour(24)
                                  .repeatCount(5)
                                  .build());

        routes.addAll(RouteBuilder.instance()
                                  .name("dc")
                                  .from(branchD)
                                  .to(branchC)
                                  .startingAt(DateTime.getCurrentDate().addHour(10))
                                  .duration(3)
                                  .repeatPeriodInHour(24)
                                  .repeatCount(5)
                                  .build());

        routes.addAll(RouteBuilder.instance()
                                  .name("bd")
                                  .from(branchB)
                                  .to(branchD)
                                  .startingAt(DateTime.getCurrentDate().addHour(8))
                                  .duration(2)
                                  .repeatPeriodInHour(48)
                                  .repeatCount(2)
                                  .build());

        routes.addAll(RouteBuilder.instance()
                                  .name("be")
                                  .from(branchB)
                                  .to(branchE)
                                  .startingAt(DateTime.getCurrentDate().addHour(8))
                                  .duration(5)
                                  .repeatPeriodInHour(24)
                                  .repeatCount(5)
                                  .build());

        routes.addAll(RouteBuilder.instance()
                                  .name("eb")
                                  .from(branchE)
                                  .to(branchB)
                                  .startingAt(DateTime.getCurrentDate().addHour(11))
                                  .duration(5)
                                  .repeatPeriodInHour(24)
                                  .repeatCount(5)
                                  .build());

        routes.addAll(RouteBuilder.instance()
                                  .name("de")
                                  .from(branchD)
                                  .to(branchE)
                                  .startingAt(DateTime.getCurrentDate().addHour(16))
                                  .duration(2)
                                  .repeatPeriodInHour(24)
                                  .repeatCount(5)
                                  .build());

        routes.addAll(RouteBuilder.instance()
                                  .name("eh")
                                  .from(branchE)
                                  .to(branchH)
                                  .startingAt(DateTime.getCurrentDate().addHour(11))
                                  .duration(2)
                                  .repeatPeriodInHour(24)
                                  .repeatCount(5)
                                  .build());

        routes.addAll(RouteBuilder.instance()
                                  .name("he")
                                  .from(branchH)
                                  .to(branchE)
                                  .startingAt(DateTime.getCurrentDate().addHour(15))
                                  .duration(2)
                                  .repeatPeriodInHour(24)
                                  .repeatCount(5)
                                  .build());

        routes.addAll(RouteBuilder.instance()
                                  .name("hf")
                                  .from(branchH)
                                  .to(branchF)
                                  .startingAt(DateTime.getCurrentDate().addHour(12))
                                  .duration(2)
                                  .repeatPeriodInHour(24)
                                  .repeatCount(5)
                                  .build());

        routes.addAll(RouteBuilder.instance()
                                  .name("fh")
                                  .from(branchF)
                                  .to(branchH)
                                  .startingAt(DateTime.getCurrentDate().addHour(12))
                                  .duration(2)
                                  .repeatPeriodInHour(24)
                                  .repeatCount(5)
                                  .build());

        routes.addAll(RouteBuilder.instance()
                                  .name("fg")
                                  .from(branchF)
                                  .to(branchG)
                                  .startingAt(DateTime.getCurrentDate().addHour(11))
                                  .duration(2)
                                  .repeatPeriodInHour(24)
                                  .repeatCount(5)
                                  .build());

        routes.addAll(RouteBuilder.instance()
                                  .name("gf")
                                  .from(branchG)
                                  .to(branchF)
                                  .startingAt(DateTime.getCurrentDate().addHour(15))
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
