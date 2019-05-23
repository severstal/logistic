package ru.iportnyagin.logistic.v1;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.iportnyagin.logistic.v1.dto.BranchDto;
import ru.iportnyagin.logistic.v1.dto.CargoDto;
import ru.iportnyagin.logistic.v1.dto.RouteDto;

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

    Optional<Path> findBestPath(CargoDto cargo, BranchDto currentLocation) {
        List<Path> paths = findAllPathsBetween(currentLocation, cargo.getDestination());

        paths.forEach(p -> System.out.println(p.getRoutes()));

        if (paths.isEmpty()) {
            System.out.println(String.format("no rides between {} and {}",
                                             currentLocation.getId(),
                                             cargo.getDestination().getId()));
            return Optional.empty();
        }

        List<Integer> durations = new ArrayList<>(paths.size());
        for (Path path : paths) {
            Integer duration = calculateDuration(path.getRoutes());
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

    List<Path> findAllPathsBetween(BranchDto source, BranchDto target) {

        List<Path> result = new ArrayList<>();
        List<RouteDto> outgoingRoutes = findOutgoingRoutes(source, Collections.emptyList());
        if (outgoingRoutes.isEmpty()) {
            return Collections.emptyList();
        }

        for (RouteDto route : outgoingRoutes) {
            goByRoute(result, new Path(), route, target);
        }

        return result;
    }

    void goByRoute(List<Path> result, Path path, RouteDto route, BranchDto target) {

        BranchDto arrived = route.getTo();
        path.getRoutes().add(route);
        path.getVisited().add(route.getFrom());

        if (arrived == target) {
            result.add(path);
            return;
        }

        List<RouteDto> outgoingRoutes = findOutgoingRoutes(arrived, path.getVisited());

        if (outgoingRoutes.isEmpty()) {
            return;
        }

        for (RouteDto r : outgoingRoutes) {
            goByRoute(result, new Path(path), r, target);
        }
    }

    int calculateDuration(List<RouteDto> routes) {

        ProcessingContext processingContext = new ProcessingContext(0, 0, 0);

        for (RouteDto route : routes) {
            processInBranch(route.getFrom(), processingContext);
            sendToRoute(route, processingContext);
        }

        return processingContext.day * 24 + processingContext.time;
    }

    void processInBranch(BranchDto branch, ProcessingContext processingContext) {

        if (processingContext.time < branch.getOpeningAt()) {
            processingContext.time = branch.getOpeningAt();
        }

        processingContext.time += processingContext.processingDelay;

        if (processingContext.time >= branch.getClosingAt()) {
            processingContext.processingDelay = processingContext.time - branch.getClosingAt();
            processingContext.day++;
            processingContext.time = 0;
            processInBranch(branch, processingContext);
        }
    }

    void sendToRoute(RouteDto route, ProcessingContext processingContext) {

        if (processingContext.time > route.getStartingAt()) {
            processingContext.day++;
            processingContext.time = route.getStartingAt();
        }

        processingContext.time += route.getDuration();
    }


    List<RouteDto> findOutgoingRoutes(BranchDto from, List<BranchDto> visited) {
        return config.getRoutes().stream()
                     .filter(route -> from == route.getFrom() && from != route.getTo() && !visited.contains(route.getTo()))
                     .collect(Collectors.toList());
    }

    List<RouteDto> findRoutesBetween(BranchDto from, BranchDto to) {
        return config.getRoutes().stream()
                     .filter(route -> from == route.getFrom() && to == route.getTo())
                     .collect(Collectors.toList());
    }

    @Data
    @AllArgsConstructor
    private static class ProcessingContext {
        private int day;
        private int time;
        private int processingDelay;
    }

}
