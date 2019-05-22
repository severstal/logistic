package ru.iportnyagin.logistic.v2;

import lombok.Data;
import ru.iportnyagin.logistic.v2.dto.RouteDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Path.
 */
@Data
public class Path {
    private List<RouteDto> routes;
    private DateTime fromDateTime;

    public Path(DateTime fromDateTime) {
        this.routes = new ArrayList<>();
        this.fromDateTime = fromDateTime;
    }

    public Path(Path source) {
        this.routes = new ArrayList<>();
        this.routes.addAll(source.getRoutes());
        this.fromDateTime = source.getFromDateTime();
    }

    public int getPathDuration() {
        final DateTime endDateTime = routes.get(routes.size() - 1).getArrivingAt();
        return endDateTime.hoursBetween(fromDateTime);
    }

    public DateTime getPathStartingAt() {
        return routes.get(0).getStartingAt();
    }

    @Override
    public String toString() {
        return routes.toString() + " in " + getPathDuration() + " hours";
    }

}