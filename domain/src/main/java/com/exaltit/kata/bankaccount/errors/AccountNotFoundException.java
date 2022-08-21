package com.exaltit.kata.bankaccount.errors;

import java.util.UUID;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(UUID accountId) {
        super("Article " + accountId + " not found");
    }
}
