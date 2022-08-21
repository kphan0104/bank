package com.exaltit.kata.bankaccount.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class Operation {
    private final UUID accountId;
    private final UUID id;
    private final LocalDate date;
    private final OperationType type;
    private final Double amount;
}
