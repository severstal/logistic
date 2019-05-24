package ru.iportnyagin.logistic;

import ru.iportnyagin.logistic.entity.Branch;
import ru.iportnyagin.logistic.entity.Route;

import java.util.List;

/**
 * Config.
 */
public interface Config {

    List<Branch> getBranches();

    List<Route> getRoutes();

}
