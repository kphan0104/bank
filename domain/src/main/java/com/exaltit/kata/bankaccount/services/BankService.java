package com.exaltit.kata.bankaccount.services;

import com.exaltit.kata.bankaccount.errors.AccountNotFoundException;
import com.exaltit.kata.bankaccount.errors.InvalidOperationException;
import com.exaltit.kata.bankaccount.model.Account;
import com.exaltit.kata.bankaccount.model.Operation;
import com.exaltit.kata.bankaccount.model.OperationRequest;
import com.exaltit.kata.bankaccount.model.OperationType;
import com.exaltit.kata.bankaccount.repositories.AccountRepository;
import com.exaltit.kata.bankaccount.repositories.OperationRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class BankService {
    private final AccountRepository accountRepository;
    private final OperationRepository operationRepository;

    public void operate(final OperationRequest request) {
        final UUID accountId = request.getAccountId();

        accountRepository.findById(accountId)
                .ifPresentOrElse(
                        account -> {
                            final OperationType operationType = request.getType();
                            final Double amount = request.getAmount();
                            final Double updatedBalance = account.getBalance() + amount * operationType.getFactor();

                            if (operationType == OperationType.WITHDRAWAL && updatedBalance < 0) {
                                throw new InvalidOperationException("Account balance is higher than withdrawal amount");
                            }

                            final Account updatedAccount = new Account(accountId, updatedBalance);
                            final Operation operation = new Operation(accountId, UUID.randomUUID(), LocalDate.now(), operationType, amount);

                            updateAccountAndSaveOperation(updatedAccount, operation);
                        }, () -> {
                            throw new AccountNotFoundException(accountId);
                        }
                );
    }

    private void updateAccountAndSaveOperation(final Account updatedAccount, final Operation operation) {
        accountRepository.save(updatedAccount);
        operationRepository.save(operation);
    }

    public List<Operation> getHistory(final UUID accountId) {
        return accountRepository.findById(accountId)
                .map(account -> operationRepository.getHistory(accountId))
                .orElseThrow(() -> new AccountNotFoundException(accountId));
    }
}
