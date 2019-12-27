package com.francochen.watcard.model.balance;

import java.math.BigDecimal;

public class BalanceInfo {
    private final BigDecimal limit;
    private final BigDecimal balance;

    BalanceInfo(BigDecimal limit, BigDecimal balance) {
        this.limit = limit;
        this.balance = balance;
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
