package com.exaltit.kata.bankaccount.cassandra.operation;

import com.exaltit.kata.bankaccount.model.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OperationEntityMapper {
    public OperationEntity toEntity(Operation operation) {
        return new OperationEntity(new OperationKey(operation.getAccountId(), operation.getId()),
                operation.getDate(), operation.getType(), operation.getAmount());
    }

    public Operation fromEntity(OperationEntity operationEntity) {
        return new Operation(operationEntity.getKey().getAccountId(), operationEntity.getKey().getId(),
                operationEntity.getDate(), operationEntity.getType(), operationEntity.getAmount());
    }
}
