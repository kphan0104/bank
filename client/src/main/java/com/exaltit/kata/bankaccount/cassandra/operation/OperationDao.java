package com.exaltit.kata.bankaccount.cassandra.operation;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OperationDao extends CassandraRepository<OperationEntity, OperationKey> {
    List<OperationEntity> findByKeyAccountId(final UUID accountId);
}
