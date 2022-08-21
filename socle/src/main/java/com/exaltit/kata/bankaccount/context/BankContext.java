package com.exaltit.kata.bankaccount.context;

import lombok.Builder;
import lombok.Getter;

import java.math.BigInteger;
import java.time.Instant;

@Builder
@Getter
public class BankContext {
    private BigInteger requestId;
    private Instant start;
}
