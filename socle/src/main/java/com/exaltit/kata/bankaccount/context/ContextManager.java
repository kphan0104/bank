package com.exaltit.kata.bankaccount.context;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ContextManager {
    private static final InheritableThreadLocal<BankContext> myThreadLocal = new InheritableThreadLocal<>();

    public static void set(BankContext.BankContextBuilder builder) {
        myThreadLocal.set(builder.build());
    }

    public static BankContext get() {
        return myThreadLocal.get();
    }

    public static void unset() {
        myThreadLocal.remove();
    }
}
