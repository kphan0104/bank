package com.exaltit.kata.bankaccount;

import com.exaltit.kata.bankaccount.errors.AccountNotFoundException;
import com.exaltit.kata.bankaccount.errors.InvalidOperationException;
import com.exaltit.kata.bankaccount.model.Account;
import com.exaltit.kata.bankaccount.model.Operation;
import com.exaltit.kata.bankaccount.model.OperationRequest;
import com.exaltit.kata.bankaccount.model.OperationType;
import com.exaltit.kata.bankaccount.repositories.AccountRepository;
import com.exaltit.kata.bankaccount.repositories.OperationRepository;
import com.exaltit.kata.bankaccount.services.BankService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class BankServiceTest {
    private final AccountRepository accountRepository = mock(AccountRepository.class);
    private final OperationRepository operationRepository = mock(OperationRepository.class);
    private final BankService service = new BankService(accountRepository, operationRepository);

    @Test
    public void test_operate() {
        final UUID accountId = UUID.randomUUID();
        final Double amount = 1000.0;
        final OperationType type = OperationType.DEPOSIT;
        final OperationRequest request = new OperationRequest(accountId, amount, type);

        final Account account = mock(Account.class);
        final Optional<Account> optionalAccount = Optional.of(account);
        doReturn(optionalAccount).when(accountRepository).findById(accountId);
        doReturn(1000.0).when(account).getBalance();

        service.operate(request);

        verify(accountRepository, times(1)).save(any());
        verify(operationRepository, times(1)).save(any());
    }

    @Test()
    public void test_operate_failed_account_not_found() {
        final UUID accountId = UUID.randomUUID();
        final Double amount = 1000.0;
        final OperationType type = OperationType.DEPOSIT;
        final OperationRequest request = new OperationRequest(accountId, amount, type);

        doReturn(Optional.empty()).when(accountRepository).findById(accountId);

        assertThrows(AccountNotFoundException.class, () -> service.operate(request));
    }

    @Test()
    public void test_withdrawal_failed_amount_too_high() {
        final UUID accountId = UUID.randomUUID();
        final Double amount = 1000.0;
        final OperationType type = OperationType.WITHDRAWAL;
        final OperationRequest request = new OperationRequest(accountId, amount, type);

        final Account account = mock(Account.class);
        final Optional<Account> optionalAccount = Optional.of(account);
        doReturn(optionalAccount).when(accountRepository).findById(accountId);
        doReturn(500.0).when(account).getBalance();

        assertThrows(InvalidOperationException.class, () -> service.operate(request));
    }

    @Test
    public void test_get_history() {
        final UUID accountId = UUID.randomUUID();
        final List<Operation> history = new ArrayList<>();

        final Account account = mock(Account.class);
        final Optional<Account> optionalAccount = Optional.of(account);
        doReturn(optionalAccount).when(accountRepository).findById(accountId);

        history.add(mock(Operation.class));
        doReturn(history).when(operationRepository).getHistory(accountId);

        List<Operation> operations = service.getHistory(accountId);
        assertFalse(operations.isEmpty());
    }
}
