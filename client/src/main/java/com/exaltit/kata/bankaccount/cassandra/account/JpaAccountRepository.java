package com.exaltit.kata.bankaccount.cassandra.account;

import com.exaltit.kata.bankaccount.model.Account;
import com.exaltit.kata.bankaccount.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JpaAccountRepository implements AccountRepository {
    private final AccountEntityMapper mapper;
    private final AccountDao dao;

    @Override
    public Optional<Account> findById(UUID accountId) {
        return dao.findById(accountId).map(mapper::fromEntity);
    }

    @Override
    public void save(Account account) {
        dao.save(mapper.toEntity(account));
    }
}
