package ru.iportnyagin.logistic.v2;

import lombok.Data;
import ru.iportnyagin.logistic.v2.dto.BranchDto;
import ru.iportnyagin.logistic.v2.dto.RideDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Path.
 */
@Data
public class Path {

    private List<RideDto> rides;
    // дата-время, с которого начинается построение пути
    private DateTime fromDateTime;

    public Path(DateTime fromDateTime) {
        this.rides = new ArrayList<>();
        this.fromDateTime = fromDateTime;
    }

    public Path(Path source) {
        this.rides = new ArrayList<>();
        this.rides.addAll(source.getRides());
        this.fromDateTime = source.getFromDateTime();
    }

    public int getPathDuration() {
        final DateTime endDateTime = rides.get(rides.size() - 1).getArrivingAt();
        return endDateTime.hoursBetween(fromDateTime);
    }

    @Override
    public String toString() { // todo сделать нормальный понятный вывод
        StringBuilder sb = new StringBuilder();

        sb.append("start:").append(fromDateTime.toString());

        BranchDto startBranch = rides.get(0).getFrom();

        sb.append(" in branch:").append(startBranch.getId()).append(" ");

        for (int i = 0; i < rides.size() - 1; i++) {
            RideDto ride = rides.get(i);

            sb.append(ride.toString());

            BranchDto arrived = ride.getTo();
            Optional<ScheduleItem> arrivedScheduleItem = arrived.findNextScheduleItem(ride.getArrivingAt());

            sb.append("  in branch:")
              .append(arrived.getId())
              .append(" open:")
              .append(arrivedScheduleItem.get().getDateTime() + " process:" + arrived.getProcessingDelay())
              .append("h  ");
        }

        RideDto endRide = rides.get(rides.size() - 1);
        sb.append(endRide.toString());

        sb.append(" end:").append(endRide.getArrivingAt());

        BranchDto end = endRide.getTo();
        Optional<ScheduleItem> endScheduleItem = end.findNextScheduleItem(endRide.getArrivingAt());

        sb.append(" in branch:")
          .append(end.getId())
          .append(" open:")
          .append(endScheduleItem.get().getDateTime())
          .append("h ");

        sb.append(" total:").append(getPathDuration()).append("h");
        return sb.toString();
    }

}