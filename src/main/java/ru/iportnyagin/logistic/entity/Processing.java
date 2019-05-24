package ru.iportnyagin.logistic.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.iportnyagin.logistic.DateTime;

/**
 * Processing - обработка в отделении
 */
@Getter
@RequiredArgsConstructor
public class Processing implements Stage {

    private final Branch branch;
    private final DateTime arriveAt;
    private final DateTime readyAt;

    @Override
    public String details() {
        return "branch:" + branch.getId() + String.format("[%s %s]", startAt(), endAt());
    }

    @Override
    public DateTime startAt() {
        return arriveAt;
    }

    @Override
    public DateTime endAt() {
        return readyAt;
    }
}
