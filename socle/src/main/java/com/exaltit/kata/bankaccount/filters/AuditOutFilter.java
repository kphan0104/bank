package com.exaltit.kata.bankaccount.filters;

import com.exaltit.kata.bankaccount.context.ContextManager;
import com.exaltit.kata.bankaccount.log.Audit;

public class AuditOutFilter {
    public static void filter() {
        Audit.log(Audit.Level.INFO, "OUT-AUDIT", ContextManager.get().getStart());

        ContextManager.unset();
    }
}
