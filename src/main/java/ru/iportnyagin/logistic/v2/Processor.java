package ru.iportnyagin.logistic.v2;

import ru.iportnyagin.logistic.v2.dto.BranchDto;
import ru.iportnyagin.logistic.v2.dto.CargoDto;

import java.util.Optional;

/**
 * Processor.
 */
public interface Processor {

    Optional<Path> findBestPath(CargoDto cargo,
                                BranchDto currentLocation,
                                DateTime fromDate,
                                int searchDuration);
}
