package com.exaltit.kata.bankaccount;

import com.exaltit.kata.bankaccount.configurations.CassandraConfiguration;
import com.exaltit.kata.bankaccount.configurations.DomainConfiguration;
import com.exaltit.kata.bankaccount.configurations.Handler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({CassandraConfiguration.class, DomainConfiguration.class, Handler.class})
public class Application {
    public static void main(String[] args) {
        try {
            SpringApplication.run(Application.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
