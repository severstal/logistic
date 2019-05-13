package ru.iportnyagin.logistic;

import ru.iportnyagin.logistic.dto.BranchDto;
import ru.iportnyagin.logistic.dto.RouteDto;

import java.util.List;

/**
 * Config.
 */
public interface Config {

    List<BranchDto> getBranches();

    List<RouteDto> getRoutes();

}
