package com.exaltit.kata.bankaccount.errors;

import java.util.UUID;

import static com.exaltit.kata.bankaccount.errors.ExceptionMessage.ACCOUNT_NOT_FOUND_MESSAGE;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(UUID accountId) {
        super(ACCOUNT_NOT_FOUND_MESSAGE + " " + accountId);
    }
}
