package ru.iportnyagin.logistic.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Cargo - груз
 * destination - пункт назначения
 * volume (*)
 * weight (*)
 */
@Getter
@RequiredArgsConstructor
public class Cargo {

    private final Branch destination;

}
