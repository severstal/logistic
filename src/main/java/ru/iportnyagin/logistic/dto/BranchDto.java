package ru.iportnyagin.logistic.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * BranchDto
 * id
 * openingAt
 * closingAt
 * processingDelay
 */
@Data
@RequiredArgsConstructor
public class BranchDto {
    private final String id;
    private final int openingAt;
    private final int closingAt;
    private final int processingDelay;
}
