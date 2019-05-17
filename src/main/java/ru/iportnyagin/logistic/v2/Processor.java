package ru.iportnyagin.logistic.v2;

import ru.iportnyagin.logistic.v2.dto.BranchDto;
import ru.iportnyagin.logistic.v2.dto.CargoDto;
import ru.iportnyagin.logistic.v2.dto.DateTime;
import ru.iportnyagin.logistic.v2.dto.RouteDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Processor.
 */
public class Processor {

    private Config config;

    public Processor(Config config) {
        this.config = config;
    }

    /**
     *
     * @param cargo груз
     * @param currentLocation текущее местоположение
     * @param fromDate начальная дата старта маршрутов
     * @param toDate конечная дата старта маршрутов
     * @return
     */
    public Optional<Path> findBestPath(CargoDto cargo, BranchDto currentLocation, DateTime fromDate, DateTime toDate) {

        List<Path> paths = findAllPathsBetween(currentLocation, cargo.getDestination(), fromDate, toDate);

        paths.forEach(p -> System.out.println(p.getRoutes()));

        if (paths.isEmpty()) {
            System.out.println(String.format("no routes between %s and %s",
                                             currentLocation.getId(),
                                             cargo.getDestination().getId()));
            return Optional.empty();
        }

        List<Integer> durations = new ArrayList<>(paths.size());
        for (Path path : paths) {
            Integer duration = calculateDuration(path.getRoutes());
            if (duration.intValue() <= 0) {
                System.out.println();
            }
            durations.add(duration);
        }

        int curValue = durations.get(0);
        int indexMinValue = 0;

        for (int i = 1; i < durations.size(); i++) {
            if (durations.get(i) < curValue) {
                curValue = durations.get(i);
                indexMinValue = i;
            }
        }

        System.out.println(durations);
        System.out.println(curValue);

        return Optional.of(paths.get(indexMinValue));
    }

    private List<Path> findAllPathsBetween(BranchDto source, BranchDto target, DateTime fromDate, DateTime toDate) {

        List<Path> result = new ArrayList<>();
        List<RouteDto> outgoingRoutes = findOutgoingRoutes(source, new ArrayList<>(), fromDate, toDate);
        if (outgoingRoutes.isEmpty()) {
            return Collections.emptyList();
        }

        for (RouteDto route : outgoingRoutes) {
            goByRoute(result, new Path(), route, target, new ArrayList<>(), toDate);
        }

        return result;
    }

    private void goByRoute(List<Path> result, Path path, RouteDto route, BranchDto target, List<BranchDto> visited, DateTime toDate) {

        BranchDto arrived = route.getTo();
        path.getRoutes().add(route);
        visited.add(route.getFrom());

        if (arrived == target) {
            result.add(path);
            return;
        }

        DateTime endOfProcessing = processInBranch(arrived, DateTime.from(route.getArrivingAt()));

        List<RouteDto> outgoingRoutes = findOutgoingRoutes(arrived, visited, endOfProcessing, toDate);

        if (outgoingRoutes.isEmpty()) {
            return;
        }

        for (RouteDto routeDto : outgoingRoutes) {
            goByRoute(result, new Path(path), routeDto, target, new ArrayList<>(visited), toDate);
        }
    }

    private int calculateDuration(List<RouteDto> routes) {

        final DateTime startDateTime = routes.get(0).getStartingAt();
        final DateTime endDateTime = routes.get(routes.size() - 1).getArrivingAt();
        int hoursBetween = endDateTime.hoursBetween(startDateTime);
        return hoursBetween;
    }

    private DateTime processInBranch(BranchDto branch, DateTime dateTime) {

        if (dateTime.getHour() < branch.getOpeningAt()) {
            dateTime.addHour(branch.getOpeningAt() - dateTime.getHour());
        }

        dateTime.addHour(branch.getProcessingDelay());

        if (dateTime.getHour() >= branch.getClosingAt()) {
            dateTime.addHour(24 - dateTime.getHour() + branch.getOpeningAt());
            processInBranch(branch, dateTime);
        }
        return dateTime;
    }

    private List<RouteDto> findOutgoingRoutes(BranchDto fromBranch, List<BranchDto> visited, DateTime fromDate, DateTime toDate) {
        return config.getOutgoingRoutes(fromBranch, fromDate, toDate).stream()
                     .filter(r -> !visited.contains(r.getTo()))
                     .collect(Collectors.toList());
    }

}
