package ru.iportnyagin.logistic.v2;

import lombok.Data;
import ru.iportnyagin.logistic.v2.dto.RideDto;

import java.util.ArrayList;
import java.util.List;

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
    public String toString() {
        return rides.toString() + " in " + getPathDuration() + " hours";
    }

}