package com.exaltit.kata.bankaccount.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class OperationRequest {
    private final UUID accountId;
    private final Double amount;
    private final OperationType type;
}
