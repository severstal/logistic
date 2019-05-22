package ru.iportnyagin.logistic.v2;

import ru.iportnyagin.logistic.v2.dto.BranchDto;
import ru.iportnyagin.logistic.v2.dto.CargoDto;
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
     * @param cargo           груз
     * @param currentLocation текущее местоположение
     * @param fromDate        начальная дата старта маршрутов
     * @param searchDuration  конечная дата старта маршрутов
     * @return
     */
    public Optional<Path> findBestPath(CargoDto cargo,
                                       BranchDto currentLocation,
                                       DateTime fromDate,
                                       int searchDuration) {

        List<Path> paths = findAllPathsBetween(currentLocation,
                                               cargo.getDestination(),
                                               fromDate,
                                               DateTimeBuilder.builder()
                                                              .setDateTime(fromDate)
                                                              .addHour(searchDuration)
                                                              .build());
        System.out.println("will search from date: " + fromDate);

        paths.forEach(System.out::println);

        if (paths.isEmpty()) {
            System.out.println(String.format("no routes between %s and %s",
                                             currentLocation.getId(),
                                             cargo.getDestination().getId()));
            return Optional.empty();
        }

        return paths.stream()
                    .min((a, b) -> a.getPathDuration() > b.getPathDuration() ? 1 : -1);
    }

    private List<Path> findAllPathsBetween(BranchDto source, BranchDto target, DateTime fromDate, DateTime toDate) {

        List<Path> result = new ArrayList<>();
        List<RouteDto> outgoingRoutes = findOutgoingRoutes(source, new ArrayList<>(), fromDate, toDate);
        if (outgoingRoutes.isEmpty()) {
            return Collections.emptyList();
        }

        for (RouteDto route : outgoingRoutes) {
            goByRoute(result, new Path(fromDate), route, target, new ArrayList<>(), toDate);
        }

        return result;
    }

    private void goByRoute(List<Path> result,
                           Path path,
                           RouteDto route,
                           BranchDto target,
                           List<BranchDto> visited,
                           DateTime toDate) {

        BranchDto arrived = route.getTo();
        path.getRoutes().add(route);
        visited.add(route.getFrom());

        if (arrived == target) {
            result.add(path);
            return;
        }

        Optional<DateTime> endOfProcessing = processInBranch(arrived, route.getArrivingAt());

        if (!endOfProcessing.isPresent()) {
            return;
        }

        List<RouteDto> outgoingRoutes = findOutgoingRoutes(arrived, visited, endOfProcessing.get(), toDate);

        if (outgoingRoutes.isEmpty()) {
            return;
        }

        for (RouteDto routeDto : outgoingRoutes) {
            goByRoute(result, new Path(path), routeDto, target, new ArrayList<>(visited), toDate);
        }
    }

    private Optional<DateTime> processInBranch(BranchDto branch, final DateTime dateTime) {

        Optional<ScheduleItem> scheduleItem = branch.findNearestScheduleItem(dateTime);
        if (!scheduleItem.isPresent()) {
            return Optional.empty();
        }

        DateTime openedAt = scheduleItem.get().getDateTime();
        int worksDuration = scheduleItem.get().getIntValue();
        int processingDuration = branch.getProcessingDelay();

        DateTimeBuilder resultBuilder = DateTimeBuilder.builder().setDateTime(openedAt);
        if (processingDuration > worksDuration) {

            while (processingDuration > worksDuration) {
                resultBuilder.addHour(worksDuration);
                processingDuration = processingDuration - worksDuration;

                Optional<ScheduleItem> nextWorkDay = branch.findNearestScheduleItem(resultBuilder.build());
                if (!nextWorkDay.isPresent()) {
                    return Optional.empty();
                }
                resultBuilder.setDateTime(nextWorkDay.get().getDateTime());
            }
        }

        resultBuilder.addHour(processingDuration);

        return Optional.of(resultBuilder.build());
    }

    private List<RouteDto> findOutgoingRoutes(BranchDto fromBranch,
                                              List<BranchDto> visited,
                                              DateTime fromDate,
                                              DateTime toDate) {
        return config.getOutgoingRoutes(fromBranch, fromDate, toDate).stream()
                     .filter(r -> !visited.contains(r.getTo()))
                     .collect(Collectors.toList());
    }

}
