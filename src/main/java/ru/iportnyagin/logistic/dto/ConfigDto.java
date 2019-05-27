package ru.iportnyagin.logistic.dto;

import java.util.List;

/**
 * ConfigDto.
 */
public class ConfigDto {

    private List<BranchDto> branches;
    private List<RouteDto> routes;

    public ConfigDto() {
    }

    public List<BranchDto> getBranches() {
        return branches;
    }

    public void setBranches(List<BranchDto> branches) {
        this.branches = branches;
    }

    public List<RouteDto> getRoutes() {
        return routes;
    }

    public void setRoutes(List<RouteDto> routes) {
        this.routes = routes;
    }

}
