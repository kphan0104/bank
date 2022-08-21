package com.exaltit.kata.bankaccount.repositories;

import com.exaltit.kata.bankaccount.model.Account;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository {
    Optional<Account> findById(UUID accountId);
    void save(Account account);
}
