package ru.iportnyagin.logistic.v2;

import ru.iportnyagin.logistic.v2.dto.BranchDto;
import ru.iportnyagin.logistic.v2.dto.RouteDto;

import java.util.List;

/**
 * Config.
 */
public interface Config {

    List<BranchDto> getBranches();

    List<RouteDto> getRoutes();

    List<RouteDto> getOutgoingRoutes(BranchDto branchDto, DateTime fromDate, DateTime toDate);

}
