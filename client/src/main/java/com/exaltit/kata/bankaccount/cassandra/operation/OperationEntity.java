package com.exaltit.kata.bankaccount.cassandra.operation;

import com.exaltit.kata.bankaccount.model.OperationType;
import lombok.*;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDate;

import static com.exaltit.kata.bankaccount.cassandra.DaoConstants.*;


@Table(OPERATION_TABLE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OperationEntity {
    @PrimaryKey
    private OperationKey key;

    @Column(OPERATION_DATE)
    private LocalDate date;

    @Column(OPERATION_TYPE)
    private OperationType type;

    @Column(OPERATION_AMOUNT)
    private Double amount;
}
