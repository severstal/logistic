package ru.iportnyagin.logistic.entity;

import ru.iportnyagin.logistic.DateTime;

/**
 * Stage - этап доставки: обработка в отделении, перевозка по маршруту
 */
public interface Stage {

    String details();
    DateTime startAt();
    DateTime endAt();

}
