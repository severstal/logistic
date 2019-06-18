package ru.iportnyagin.logistic;

import ru.iportnyagin.logistic.entity.Branch;
import ru.iportnyagin.logistic.entity.Cargo;
import ru.iportnyagin.logistic.entity.Path;

import java.util.Optional;

/**
 * PathSearch.
 */
public interface PathSearch {

    /**
     * @param cargo           груз
     * @param currentLocation текущее местоположение
     * @param startAt        начальная дата поиска маршрутов
     * @return
     */
    Optional<Path> find(Cargo cargo,
                        Branch currentLocation,
                        DateTime startAt);
}
