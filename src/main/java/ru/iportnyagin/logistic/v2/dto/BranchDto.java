package ru.iportnyagin.logistic.v2.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * BranchDto
 * id
 * openingAt
 * closingAt
 * processingDelay
 */
@Getter
@RequiredArgsConstructor
public class BranchDto {
    private final String id;
    private final int openingAt; // todo use DateTime
    private final int closingAt;
    private final int processingDelay;
}
