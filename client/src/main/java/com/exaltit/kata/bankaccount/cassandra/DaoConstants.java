package com.exaltit.kata.bankaccount.cassandra;

public interface DaoConstants {
    String ACCOUNT_TABLE = "account";
    String ACCOUNT_ID = "id";
    String ACCOUNT_BALANCE = "balance";

    String OPERATION_TABLE = "operation";
    String OPERATION_ACCOUNT_ID = "account_id";
    String OPERATION_ID = "id";
    String OPERATION_DATE = "date";
    String OPERATION_TYPE = "type";
    String OPERATION_AMOUNT = "amount";
}
