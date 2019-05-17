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

    public Path() {
        routes = new ArrayList<>();
    }

    public Path(Path source) {
        routes = new ArrayList<>();
        routes.addAll(source.routes);
    }

}
