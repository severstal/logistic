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
     * @param searchDuration  продолжительность в часах => конечная дата старта маршрутов
     * @return
     */
    @Override
    public Optional<Path> find(Cargo cargo,
                               Branch currentLocation,
                               DateTime fromDate,
                               int searchDuration) {

        System.out.println(String.format("will search path from %s to %s begin at %s",
                                         currentLocation,
                                         cargo.getDestination(),
                                         fromDate));

        Optional<Processing> firstProcessing = currentLocation.processInBranch(fromDate);
        if (!firstProcessing.isPresent()) {
            return Optional.empty();
        }

        DateTime toDate = new DateTime(fromDate, searchDuration);

        List<Shipping> outgoingShippings = findOutgoingShippings(currentLocation,
                                                                 new ArrayList<>(),
                                                                 firstProcessing.get().endAt(),
                                                                 toDate);
        if (outgoingShippings.isEmpty()) {
            return Optional.empty();
        }

        List<Path> paths = new ArrayList<>();
        for (Shipping shipping : outgoingShippings) {
            goByRoute(paths, new Path(firstProcessing.get()), shipping, cargo.getDestination(), new ArrayList<>(), toDate);
        }

        paths.forEach(System.out::println);
        if (paths.isEmpty()) {
            System.out.println(String.format("no paths between %s and %s",
                                             currentLocation.getId(),
                                             cargo.getDestination().getId()));
            return Optional.empty();
        }

        return paths.stream()
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

    private void goByRoute(List<Path> result,
                           Path path,
                           Shipping shipping,
                           Branch target,
                           List<String> visited,
                           DateTime toDate) {

        Branch arrived = config.getBranches()
                                         .stream()
                                         .filter(b -> b.getId().equals(shipping.getToBranch()))
                                         .findFirst().orElseThrow(RuntimeException::new);
        path.getStages().add(shipping);
        visited.add(shipping.getFromBranch());

        Optional<Processing> processing = arrived.processInBranch(shipping.getArrivingAt());
        if (!processing.isPresent()) {
            return;
        }

        path.getStages().add(processing.get());

        if (arrived.getId().equals(target.getId())) {
            result.add(path);
            return;
        }

        List<Shipping> outgoingShippings = findOutgoingShippings(arrived, visited, processing.get().endAt(), toDate);
        if (outgoingShippings.isEmpty()) {
            return;
        }

        for (Shipping outShipping : outgoingShippings) {
            goByRoute(result, new Path(path), outShipping, target, new ArrayList<>(visited), toDate);
        }
    }


    private List<Shipping> findOutgoingShippings(Branch fromBranch,
                                                 List<String> visited,
                                                 DateTime fromDate,
                                                 DateTime toDate) {

        List<Route> routes = config.getRoutes().stream()
                                   .filter(r -> r.getFromBranch().equals(fromBranch.getId())
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

}
