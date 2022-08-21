package com.exaltit.kata.bankaccount.cassandra.account;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountDao extends CassandraRepository<AccountEntity, UUID> {
}
