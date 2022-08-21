package com.exaltit.kata.bankaccount.web;

import com.exaltit.kata.bankaccount.model.Operation;
import com.exaltit.kata.bankaccount.model.OperationRequest;
import com.exaltit.kata.bankaccount.model.OperationType;
import com.exaltit.kata.bankaccount.services.BankService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.exaltit.kata.bankaccount.web.BankResponse.SUCCESSFUL_DEPOSIT;
import static com.exaltit.kata.bankaccount.web.BankResponse.SUCCESSFUL_WITHDRAWAL;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BankControllerTest {
    private BankService service = mock(BankService.class);
    private BankController controller = new BankController(service);

    @Test
    public void test_deposit_success() {
        final UUID accountId = UUID.randomUUID();
        final Double amount = 1000.0;
        final OperationType type = OperationType.DEPOSIT;
        final OperationRequest request = new OperationRequest(accountId, amount, type);

        doNothing().when(service).operate(request);

        String depositResponse = controller.deposit(accountId, amount);
        assertEquals(depositResponse, SUCCESSFUL_DEPOSIT);
    }

    @Test
    public void test_withdrawal_success() {
        final UUID accountId = UUID.randomUUID();
        final Double amount = 1000.0;
        final OperationType type = OperationType.WITHDRAWAL;
        final OperationRequest request = new OperationRequest(accountId, amount, type);

        doNothing().when(service).operate(request);

        String depositResponse = controller.withdraw(accountId, amount);

        assertEquals(depositResponse, SUCCESSFUL_WITHDRAWAL);
    }

    @Test
    public void test_get_history() {
        final UUID accountId = UUID.randomUUID();
        List<Operation> operations = new ArrayList<>();
        operations.add(mock(Operation.class));
        doReturn(operations).when(service).getHistory(accountId);

        controller.getHistory(accountId);

        assertFalse(operations.isEmpty());
    }
}
