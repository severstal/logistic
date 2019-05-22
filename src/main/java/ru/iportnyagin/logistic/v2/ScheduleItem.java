package ru.iportnyagin.logistic.v2;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * ScheduleItem.
 */
@Getter
@RequiredArgsConstructor
public class ScheduleItem {

    private final DateTime dateTime; // момент времени
    private final int intValue;     // какое то числовое значение соотв для этого момента
    private final String stringValue; // какое то строковое значение соотв для этого момента

}





