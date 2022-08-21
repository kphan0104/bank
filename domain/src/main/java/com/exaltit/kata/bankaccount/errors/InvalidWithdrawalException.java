package com.exaltit.kata.bankaccount.errors;

import static com.exaltit.kata.bankaccount.errors.ExceptionMessage.INVALID_WITHDRAWAL_MESSAGE;

public class InvalidWithdrawalException extends RuntimeException {
    public InvalidWithdrawalException() {
        super(INVALID_WITHDRAWAL_MESSAGE);
    }
}
