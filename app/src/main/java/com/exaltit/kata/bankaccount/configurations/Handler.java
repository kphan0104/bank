package com.exaltit.kata.bankaccount.configurations;

import com.exaltit.kata.bankaccount.log.Audit;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class Handler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handle(Exception ex) {
        Audit.log(Audit.Level.ERROR, getClass().getSimpleName(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}
