package ru.iportnyagin.logistic;

import lombok.RequiredArgsConstructor;
import ru.iportnyagin.logistic.entity.Branch;
import ru.iportnyagin.logistic.entity.Cargo;
import ru.iportnyagin.logistic.entity.Path;
import ru.iportnyagin.logistic.entity.Processing;
import ru.iportnyagin.logistic.entity.Route;
import ru.iportnyagin.logistic.entity.Shipping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * FastPathSearch.
 */
@RequiredArgsConstructor
public class FastPathSearch implements PathSearch {

    private final Config config;

    /**
     * @param cargo           груз
     * @param currentLocation текущее местоположение
     * @param fromDate        начальная дата старта маршрутов
     * @return
     */
    @Override
    public Optional<Path> find(Cargo cargo, Branch currentLocation, DateTime fromDate) {

        System.out.println(String.format("will search path from %s to %s begin at %s",
                                         currentLocation,
                                         cargo.getDestination(),
                                         fromDate));

        Optional<Path> basePath = findOnePath(new Path(),
                                              currentLocation,
                                              cargo.getDestination(),
                                              fromDate,
                                              new ArrayList<>());

        System.out.println("found base path:" + basePath.map(Path::toString).orElse("no result"));

        int searchDuration = basePath.map(Path::getPathDuration).orElse(0);

        List<Path> result = new ArrayList<>();

        findAllPaths(result,
                     new Path(),
                     currentLocation,
                     cargo.getDestination(),
                     new ArrayList<>(),
                     fromDate,
                     new DateTime(fromDate, searchDuration));

        result.forEach(System.out::println);
        if (result.isEmpty()) {
            System.out.println(String.format("no paths between %s and %s",
                                             currentLocation.getId(),
                                             cargo.getDestination().getId()));
            return Optional.empty();
        }

        return result.stream()
                     .min((a, b) -> {
                         int aDuration = a.getPathDuration();
                         int bDuration = b.getPathDuration();
                         if (aDuration == bDuration) {
                             return 0;
                         } else {
                             return aDuration > bDuration ? 1 : -1;
                         }
                     });
    }

    /**
     * ищет все пути из текущего отделения в целевое
     *
     * @param result        результат - список путей
     * @param path          "текущий" путь, для накопления этапов обработки, в рекурсию передается клон
     * @param currentBranch текущее отделение
     * @param targetBranch  целевое отделение
     * @param visited       список посещенных отделений, в рекурсию передается клон
     * @param startAt       текущая дата
     * @param toDate        максимальная дата, в течении которой нужно добраться до целевеого пункта
     */
    private void findAllPaths(List<Path> result,
                              Path path,
                              Branch currentBranch,
                              Branch targetBranch,
                              List<Branch> visited,
                              DateTime startAt,
                              DateTime toDate) {

        Optional<Processing> curProcessing = currentBranch.processInBranch(startAt);

        if (curProcessing.isPresent()) {
            path.getStages().add(curProcessing.get());
            visited.add(currentBranch);

            if (currentBranch.equals(targetBranch)) { // достигнут нужный пункт, конец рекурсии/поиска
                result.add(path);
                return;
            }

            List<Shipping> outgoingShippings = findOutgoingShippingsFromToDate(currentBranch,
                                                                               visited,
                                                                               curProcessing.get().endAt(),
                                                                               toDate);
            if (outgoingShippings.isEmpty()) {
                return;
            }

            for (Shipping outShipping : outgoingShippings) {
                path.getStages().add(outShipping);
                findAllPaths(result,
                             new Path(path),
                             outShipping.getToBranch(),
                             targetBranch,
                             new ArrayList<>(visited),
                             outShipping.endAt(),
                             toDate);
            }
        }
    }

    /**
     * поиск исходящих поездок
     *
     * @param fromBranch из этого отделения
     * @param visited    список посещенных отделений
     * @param fromDate   "текущая" дата
     * @param toDate     дата окончания поездки
     * @return
     */
    private List<Shipping> findOutgoingShippingsFromToDate(Branch fromBranch,
                                                           List<Branch> visited,
                                                           DateTime fromDate,
                                                           DateTime toDate) {

        List<Route> routes = config.getRoutes().stream()
                                   .filter(r -> r.getFromBranch().equals(fromBranch)
                                           && !visited.contains(r.getToBranch()))
                                   .collect(Collectors.toList());

        List<Shipping> result = new ArrayList<>();

        for (Route r : routes) {
            List<ScheduleItem> scheduleItems = r.getSchedule()
                                                .stream()
                                                .filter(i -> i.getStartAt().after(fromDate)
                                                        && i.getEndAt().before(toDate))
                                                .sorted(new ScheduleItem.StartAtComparator())
                                                .collect(Collectors.toList());

            for (ScheduleItem i : scheduleItems) {
                result.add(new Shipping(r.getDescription(), r.getFromBranch(), r.getToBranch(), i));
            }
        }

        return result;
    }

    /**
     * ищет один путь
     * обработать в текущем отделении, найти исходящие поездки, пойти в рекурсию в них
     *
     * @param path          объект для сборки этапов, в рекурсивный вызов нужно передавать клон
     * @param currentBranch текущее отделение
     * @param targetBranch  целевое отделение - до которого ищется маршрут
     * @param startAt       "текущая" дата
     * @param visited       список для сборки посещенных отделений, в рекурсивный вызов нужно передавать клон
     * @return
     */
    private Optional<Path> findOnePath(Path path,
                                       Branch currentBranch,
                                       Branch targetBranch,
                                       DateTime startAt,
                                       List<Branch> visited) {

        Optional<Processing> curProcessing = currentBranch.processInBranch(startAt);

        if (curProcessing.isPresent()) {
            path.getStages().add(curProcessing.get());
            visited.add(currentBranch);

            if (currentBranch.equals(targetBranch)) { // достигнут нужный пункт, конец рекурсии/поиска
                return Optional.of(path);
            }

            List<Shipping> outgoingShippings = findFirstOutgoingShippings(currentBranch,
                                                                          visited,
                                                                          curProcessing.get().endAt());

            if (!outgoingShippings.isEmpty()) {

                for (Shipping outShipping : outgoingShippings) {

                    path.getStages().add(outShipping);

                    Optional<Path> basePath = findOnePath(new Path(path),
                                                          outShipping.getToBranch(),
                                                          targetBranch,
                                                          outShipping.endAt(),
                                                          new ArrayList<>(visited));
                    if (basePath.isPresent()) { // достигнут нужный пункт, конец рекурсии/поиска
                        return basePath;
                    }
                }
            }
        }
        return Optional.empty();
    }

    /**
     * поиск исходящих поездок
     *
     * @param fromBranch из этого отделения
     * @param visited    список посещенных отделений
     * @param fromDate   "текущая" дата
     * @return
     */
    private List<Shipping> findFirstOutgoingShippings(Branch fromBranch,
                                                      List<Branch> visited,
                                                      DateTime fromDate) {

        List<Route> routes = config.getRoutes().stream()
                                   .filter(r -> r.getFromBranch().equals(fromBranch)
                                           && !visited.contains(r.getToBranch()))
                                   .collect(Collectors.toList());

        List<Shipping> result = new ArrayList<>();

        for (Route r : routes) {
            Optional<ScheduleItem> scheduleItem = r.getSchedule()
                                                   .stream()
                                                   .filter(i -> i.getStartAt().after(fromDate))
                                                   .min(new ScheduleItem.StartAtComparator());

            scheduleItem.ifPresent(si -> result.add(new Shipping(r.getDescription(),
                                                                 r.getFromBranch(),
                                                                 r.getToBranch(),
                                                                 si)));
        }
        return result;
    }

}
