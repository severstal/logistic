package ru.iportnyagin.logistic.v1;

import ru.iportnyagin.logistic.v1.dto.BranchDto;
import ru.iportnyagin.logistic.v1.dto.RouteDto;

import java.util.Arrays;
import java.util.List;

/**
 * ConfigImpl.
 */
public class ConfigImpl implements Config {

    private List<BranchDto> branches;
    private List<RouteDto> routes;

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

        final RouteDto routeAB = new RouteDto("ab", branchA, branchB, 7, 8, 1);
        final RouteDto routeBA = new RouteDto("ba", branchB, branchA, 10, 11, 1);

        final RouteDto routeAC = new RouteDto("ac", branchA, branchC, 8, 10, 20);
        final RouteDto routeCA = new RouteDto("ca", branchC, branchA, 12, 14, 2);

        final RouteDto routeAF = new RouteDto("af", branchA, branchF, 10, 13, 3);
        final RouteDto routeFA = new RouteDto("fa", branchF, branchA, 15, 18, 3);

        final RouteDto routeBC = new RouteDto("bc", branchB, branchC, 12, 13, 5);
        final RouteDto routeCB = new RouteDto("cb", branchC, branchB, 16, 17, 1);

        final RouteDto routeCD = new RouteDto("cd", branchC, branchD, 11, 16, 3);
        final RouteDto routeDC = new RouteDto("dc", branchD, branchC, 10, 13, 3);

        final RouteDto routeBD = new RouteDto("bd", branchB, branchD, 8, 11, 33);

        final RouteDto routeBE = new RouteDto("be", branchB, branchE, 8, 13, 15);
        final RouteDto routeEB = new RouteDto("eb", branchE, branchB, 11, 16, 5);

        final RouteDto routeDE = new RouteDto("de", branchD, branchE, 16, 18, 2);

        final RouteDto routeEH = new RouteDto("eh", branchE, branchH, 11, 13, 2);
        final RouteDto routeHE = new RouteDto("he", branchH, branchE, 15, 17, 2);

        final RouteDto routeHF = new RouteDto("hf", branchH, branchF, 12, 14, 2);
        final RouteDto routeFH = new RouteDto("fh", branchF, branchH, 12, 14, 12);

        final RouteDto routeFG = new RouteDto("fg", branchF, branchG, 11, 13, 2);
        final RouteDto routeGF = new RouteDto("gf", branchG, branchF, 15, 17, 2);

        routes = Arrays.asList(
                routeAB, routeBA, routeAC, routeCA, routeAF, routeFA, routeBC, routeCB, routeCD, routeDC, routeBD,
                routeBE, routeEB, routeDE, routeEH, routeHE, routeHF, routeFH, routeFG, routeGF
        );
    }

    @Override
    public List<BranchDto> getBranches() {
        return branches;
    }

    @Override
    public List<RouteDto> getRoutes() {
        return routes;
    }
}
