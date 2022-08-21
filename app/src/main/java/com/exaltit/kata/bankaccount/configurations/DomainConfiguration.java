package com.exaltit.kata.bankaccount.configurations;

import com.exaltit.kata.bankaccount.cassandra.account.JpaAccountRepository;
import com.exaltit.kata.bankaccount.cassandra.operation.JpaOperationRepository;
import com.exaltit.kata.bankaccount.filters.AuditInFilter;
import com.exaltit.kata.bankaccount.filters.AuditOutFilter;
import com.exaltit.kata.bankaccount.services.BankService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class DomainConfiguration {
    @Bean
    public BankService bankService(JpaAccountRepository accountRepository, JpaOperationRepository operationRepository) {
        return new BankService(accountRepository, operationRepository);
    }

    @Bean
    public Filter auditFilter() {
        return (request, response, chain) -> {
            AuditInFilter.filter();

            chain.doFilter(request, response);

            AuditOutFilter.filter();
        };
    }
}
