package ru.iportnyagin.logistic.v2.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * CargoDto - груз
 * destination
 * volume (*)
 * weight (*)
 */
@Getter
@RequiredArgsConstructor
public class CargoDto {

    private final BranchDto destination;

}
