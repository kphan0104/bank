package com.exaltit.kata.bankaccount.cassandra.account;

import com.exaltit.kata.bankaccount.log.Audit;
import com.exaltit.kata.bankaccount.model.Account;
import com.exaltit.kata.bankaccount.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JpaAccountRepository implements AccountRepository {
    private final AccountEntityMapper mapper;
    private final AccountDao dao;

    @Override
    public Optional<Account> findById(UUID accountId) {
        Instant start = Instant.now();
        Optional<Account> account = dao.findById(accountId).map(mapper::fromEntity);
        Audit.log(Audit.Level.INFO, "CASSANDRA", getClass().getSimpleName() + ".findById done", start);
        return account;
    }

    @Override
    public void save(Account account) {
        Instant start = Instant.now();
        dao.save(mapper.toEntity(account));
        Audit.log(Audit.Level.INFO, "CASSANDRA", getClass().getSimpleName() + ".save done", start);
    }
}
