package com.exaltit.kata.bankaccount.cassandra.operation;

import com.exaltit.kata.bankaccount.model.Operation;
import com.exaltit.kata.bankaccount.repositories.OperationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
        dao.save(mapper.toEntity(operation));
    }

    @Override
    public List<Operation> getHistory(UUID accountId) {
        List<OperationEntity> operationEntities = dao.findByKeyAccountId(accountId);
        List<Operation> operations = new ArrayList<>();

        for(OperationEntity entity : operationEntities) {
            operations.add(mapper.fromEntity(entity));
        }

        return operations;
    }
}
