package com.exaltit.kata.bankaccount.web;

import com.exaltit.kata.bankaccount.model.Operation;
import com.exaltit.kata.bankaccount.model.OperationRequest;
import com.exaltit.kata.bankaccount.model.OperationType;
import com.exaltit.kata.bankaccount.services.BankService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.exaltit.kata.bankaccount.web.BankResponse.SUCCESSFUL_DEPOSIT;
import static com.exaltit.kata.bankaccount.web.BankResponse.SUCCESSFUL_WITHDRAWAL;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class BankController {
    private final BankService service;

    @PostMapping("{id}/deposit/{amount}")
    public String deposit(@PathVariable final UUID id, @PathVariable final Double amount) {
        OperationRequest request = new OperationRequest(id, amount, OperationType.DEPOSIT);
        service.operate(request);
        return SUCCESSFUL_DEPOSIT;
    }

    @PostMapping("{id}/withdraw/{amount}")
    public String withdraw(@PathVariable final UUID id, @PathVariable final Double amount) {
        OperationRequest request = new OperationRequest(id, amount, OperationType.WITHDRAWAL);
        service.operate(request);
        return SUCCESSFUL_WITHDRAWAL;
    }

    @GetMapping("{id}")
    public List<Operation> getHistory(@PathVariable final UUID id) {
        return service.getHistory(id);
    }
}
