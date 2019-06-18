package ru.iportnyagin.logistic;

import org.jetbrains.annotations.NotNull;
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
     * @param startAt         начальная дата поиска маршрутов
     * @return
     */
    @NotNull
    Optional<Path> find(@NotNull Cargo cargo,
                        @NotNull Branch currentLocation,
                        @NotNull DateTime startAt);
}
