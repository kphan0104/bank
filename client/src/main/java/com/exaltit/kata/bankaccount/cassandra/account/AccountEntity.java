package com.exaltit.kata.bankaccount.cassandra.account;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

import static com.exaltit.kata.bankaccount.cassandra.DaoConstants.*;

@Table(ACCOUNT_TABLE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity {
    @PrimaryKey(ACCOUNT_ID)
    private UUID id;

    @Column(ACCOUNT_BALANCE)
    private Double balance;
}
