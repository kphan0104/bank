package com.exaltit.kata.bankaccount.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum OperationType {
    DEPOSIT(1), WITHDRAWAL(-1);

    private final int factor;
}
