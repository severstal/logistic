package ru.iportnyagin.logistic.dto;

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
