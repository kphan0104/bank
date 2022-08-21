package com.exaltit.kata.bankaccount.cassandra.account;

import com.exaltit.kata.bankaccount.model.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountEntityMapper {
    public AccountEntity toEntity(Account account) {
        return new AccountEntity(account.getId(), account.getBalance());
    }

    public Account fromEntity(AccountEntity accountEntity) {
        return new Account(accountEntity.getId(), accountEntity.getBalance());
    }
}
