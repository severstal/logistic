package ru.iportnyagin.logistic.v1.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * CargoDto
 * destination
 * volume (*)
 * weight (*)
 */
@Data
@RequiredArgsConstructor
public class CargoDto {
    private final BranchDto destination;
}
