package com.exaltit.kata.bankaccount.configurations;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@ComponentScan
@EntityScan
@EnableCassandraRepositories
public class CassandraConfiguration extends AbstractCassandraConfiguration {
    @Override
    protected String getKeyspaceName() {
        return "exaltit";
    }
}
