package ru.iportnyagin.logistic;

import lombok.Data;
import ru.iportnyagin.logistic.dto.BranchDto;
import ru.iportnyagin.logistic.dto.RouteDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Path.
 */
@Data
public class Path {
    private List<RouteDto> routes;
    private List<BranchDto> visited;

    public Path() {
        routes = new ArrayList<>();
        visited = new ArrayList<>();
    }

    public Path(Path source) {
        routes = new ArrayList<>();
        visited = new ArrayList<>();
        routes.addAll(source.routes);
        visited.addAll(source.visited);
    }
}
