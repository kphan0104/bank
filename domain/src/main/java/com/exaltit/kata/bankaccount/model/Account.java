package com.exaltit.kata.bankaccount.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class Account {
    private final UUID id;
    private final Double balance;
}
