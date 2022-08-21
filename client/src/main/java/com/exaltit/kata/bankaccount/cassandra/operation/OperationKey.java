package com.exaltit.kata.bankaccount.cassandra.operation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.util.UUID;

import static com.exaltit.kata.bankaccount.cassandra.DaoConstants.OPERATION_ACCOUNT_ID;
import static com.exaltit.kata.bankaccount.cassandra.DaoConstants.OPERATION_ID;
import static org.springframework.data.cassandra.core.cql.PrimaryKeyType.PARTITIONED;

@PrimaryKeyClass
@Getter
@Setter
@AllArgsConstructor
public class OperationKey {
    @PrimaryKeyColumn(name = OPERATION_ACCOUNT_ID, type = PARTITIONED)
    private UUID accountId;

    @PrimaryKeyColumn(name = OPERATION_ID)
    private UUID id;
}
