package ru.iportnyagin.logistic.entity;

import lombok.Data;
import ru.iportnyagin.logistic.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Path.
 */
@Data
public class Path {

    private final List<Stage> stages;

    public Path() {
        stages = new ArrayList<>();
    }

    public Path(Stage first) {
        this();
        stages.add(first);
    }

    public Path(Path source) {
        this();
        stages.addAll(source.getStages());
    }

    public int getPathDuration() {
        DateTime fromDateTime = stages.get(0).startAt();
        DateTime endDateTime = stages.get(stages.size() - 1).endAt();
        return endDateTime.hoursBetween(fromDateTime);
    }

    @Override
    public String toString() {
        return stages.stream()
                     .map(Stage::details)
                     .collect(Collectors.toList())
                     .toString() + " total:" + getPathDuration() + "h";
    }

}