package ru.iportnyagin.logistic;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import ru.iportnyagin.logistic.dto.BranchDto;
import ru.iportnyagin.logistic.dto.ConfigDto;
import ru.iportnyagin.logistic.entity.Branch;
import ru.iportnyagin.logistic.entity.Route;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * JsonConfig.
 */
public class JsonConfig implements Config {

    private final String config;

    private List<Branch> branches;
    private List<Route> routes;

    public JsonConfig(String config) {
        this.config = config;
    }

    @Override
    @NotNull
    public List<Branch> getBranches() {
        if (Objects.isNull(branches)) {
            init();
        }
        return branches;
    }

    @Override
    @NotNull
    public List<Route> getRoutes() {
        if (Objects.isNull(routes)) {
            init();
        }
        return routes;
    }

    @Override
    public String toString() {
        return "branches:" + getBranches().toString() + "\n" + "routes:" + getRoutes().toString();
    }

    private void init() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            ConfigDto configDto = mapper.readValue(config, ConfigDto.class);
            this.branches = configDto.getBranches().stream()
                                     .map(BranchDto::toBranch)
                                     .collect(Collectors.toList());
            this.routes = configDto.getRoutes().stream()
                                   .map(r -> r.toRoute(branches))
                                   .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
