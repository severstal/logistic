package ru.iportnyagin.logistic.v2;

import ru.iportnyagin.logistic.v2.dto.BranchDto;
import ru.iportnyagin.logistic.v2.dto.CargoDto;
import ru.iportnyagin.logistic.v2.dto.RideDto;
import ru.iportnyagin.logistic.v2.dto.RouteDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * ProcessorImpl.
 */
public class ProcessorImpl implements Processor {

    private Config config;

    public ProcessorImpl(Config config) {
        this.config = config;
    }

    /**
     * @param cargo           груз
     * @param currentLocation текущее местоположение
     * @param fromDate        начальная дата старта маршрутов
     * @param searchDuration  продолжительность в часах => конечная дата старта маршрутов
     * @return
     */
    @Override
    public Optional<Path> findBestPath(CargoDto cargo,
                                       BranchDto currentLocation,
                                       DateTime fromDate,
                                       int searchDuration) {

        System.out.println(String.format("will search path from %s to %s begin at %s",
                                         currentLocation,
                                         cargo.getDestination(),
                                         fromDate));

        List<Path> paths = new ArrayList<>();

        DateTime toDate = new DateTime(fromDate, searchDuration);

        List<RideDto> outgoingRides = findOutgoingRides(currentLocation, new ArrayList<>(), fromDate, toDate);

        if (outgoingRides.isEmpty()) {
            return Optional.empty();
        }

        for (RideDto ride : outgoingRides) {
            goByRoute(paths, new Path(fromDate), ride, cargo.getDestination(), new ArrayList<>(), toDate);
        }

        paths.forEach(System.out::println);

        if (paths.isEmpty()) {
            System.out.println(String.format("no rides between %s and %s",
                                             currentLocation.getId(),
                                             cargo.getDestination().getId()));
            return Optional.empty();
        }

        return paths.stream()
                    .min((a, b) -> a.getPathDuration() > b.getPathDuration() ? 1 : -1);
    }

    private void goByRoute(List<Path> result,
                           Path path,
                           RideDto ride,
                           BranchDto target,
                           List<BranchDto> visited,
                           DateTime toDate) {

        BranchDto arrived = ride.getTo();
        path.getRides().add(ride);
        visited.add(ride.getFrom());

        if (arrived == target) {
            result.add(path);
            return;
        }

        Optional<DateTime> endOfProcessing = processInBranch(arrived, ride.getArrivingAt());

        if (!endOfProcessing.isPresent()) {
            return;
        }

        List<RideDto> outgoingRoutes = findOutgoingRides(arrived, visited, endOfProcessing.get(), toDate);

        if (outgoingRoutes.isEmpty()) {
            return;
        }

        for (RideDto ride2 : outgoingRoutes) {
            goByRoute(result, new Path(path), ride2, target, new ArrayList<>(visited), toDate);
        }
    }

    private Optional<DateTime> processInBranch(BranchDto branch, final DateTime dateTime) {

        Optional<ScheduleItem> scheduleItem = branch.findNextScheduleItem(dateTime);
        if (!scheduleItem.isPresent()) {
            return Optional.empty();
        }

        DateTime openedAt = scheduleItem.get().getDateTime();
        int worksDuration = scheduleItem.get().getIntValue();
        int processingDuration = branch.getProcessingDelay();

        MutableDateTime duration = new MutableDateTime(openedAt);

        if (processingDuration > worksDuration) {

            while (processingDuration > worksDuration) {
                duration.addHour(worksDuration);
                processingDuration = processingDuration - worksDuration;

                Optional<ScheduleItem> nextWorkDay = branch.findNextScheduleItem(duration.getDateTime());
                if (!nextWorkDay.isPresent()) {
                    return Optional.empty();
                }
                duration.setDateTime(nextWorkDay.get().getDateTime());
            }
        }

        duration.addHour(processingDuration);

        return Optional.of(duration.getDateTime());
    }

    private List<RideDto> findOutgoingRides(BranchDto fromBranch,
                                            List<BranchDto> visited,
                                            DateTime fromDate,
                                            DateTime toDate) {

        List<RouteDto> routes = config.getRoutes().stream()
                                      .filter(r -> r.getFrom().equals(fromBranch)
                                              && !visited.contains(r.getTo()))
                                      .collect(Collectors.toList());

        List<RideDto> result = new ArrayList<>();

        for (RouteDto r : routes) {
            List<ScheduleItem> scheduleItems = r.getSchedule()
                                                .stream()
                                                .filter(i -> i.getDateTime().after(fromDate) && i.getDateTime()
                                                                                                 .before(toDate))
                                                .sorted((a, b) -> a.getDateTime().after(b.getDateTime()) ? 1 : -1)
                                                .collect(Collectors.toList());
            for (ScheduleItem i : scheduleItems) {
                result.add(new RideDto(r.getDescription(), r.getFrom(), r.getTo(), i));
            }
        }

        return result;
    }

}
