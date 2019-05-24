package ru.iportnyagin.logistic;

import ru.iportnyagin.logistic.entity.Branch;
import ru.iportnyagin.logistic.entity.Cargo;
import ru.iportnyagin.logistic.entity.Path;

import java.util.Optional;

/**
 * PathSearch.
 */
public interface PathSearch {

    Optional<Path> find(Cargo cargo,
                        Branch currentLocation,
                        DateTime fromDate,
                        int searchDuration);
}
