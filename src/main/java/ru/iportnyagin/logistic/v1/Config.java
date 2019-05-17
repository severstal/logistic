package ru.iportnyagin.logistic.v1;

import ru.iportnyagin.logistic.v1.dto.BranchDto;
import ru.iportnyagin.logistic.v1.dto.RouteDto;

import java.util.List;

/**
 * Config.
 */
public interface Config {

    List<BranchDto> getBranches();

    List<RouteDto> getRoutes();

}
