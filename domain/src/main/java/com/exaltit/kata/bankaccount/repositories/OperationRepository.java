package com.exaltit.kata.bankaccount.repositories;

import com.exaltit.kata.bankaccount.model.Operation;

import java.util.List;
import java.util.UUID;

public interface OperationRepository {
    void save(Operation operation);
    List<Operation> getHistory(UUID accountId);
}
