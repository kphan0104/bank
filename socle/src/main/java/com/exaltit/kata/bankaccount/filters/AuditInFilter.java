package com.exaltit.kata.bankaccount.filters;

import com.exaltit.kata.bankaccount.context.BankContext;
import com.exaltit.kata.bankaccount.context.ContextManager;
import com.exaltit.kata.bankaccount.log.Audit;

import java.math.BigInteger;
import java.time.Instant;
import java.util.UUID;

public class AuditInFilter {
    public static void filter() {
        final Instant start = Instant.now();
        final BigInteger requestId = new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16);

        BankContext.BankContextBuilder builder = BankContext.builder().requestId(requestId).start(start);
        ContextManager.set(builder);
        Audit.log(Audit.Level.INFO, "IN-AUDIT");
    }
}
