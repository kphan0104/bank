package com.exaltit.kata.bankaccount.cassandra.operation;

import com.exaltit.kata.bankaccount.log.Audit;
import com.exaltit.kata.bankaccount.model.Operation;
import com.exaltit.kata.bankaccount.repositories.OperationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JpaOperationRepository implements OperationRepository {
    private final OperationEntityMapper mapper;
    private final OperationDao dao;

    @Override
    public void save(Operation operation) {
        Instant start = Instant.now();
        dao.save(mapper.toEntity(operation));
        Audit.log(Audit.Level.INFO, "CASSANDRA", getClass().getSimpleName() + ".save done", start);
    }

    @Override
    public List<Operation> getHistory(UUID accountId) {
        Instant start = Instant.now();
        List<OperationEntity> operationEntities = dao.findByKeyAccountId(accountId);
        Audit.log(Audit.Level.INFO, "CASSANDRA", getClass().getSimpleName() + ".getHistory done", start);

        List<Operation> operations = new ArrayList<>();

        for(OperationEntity entity : operationEntities) {
            operations.add(mapper.fromEntity(entity));
        }

        return operations;
    }
}
