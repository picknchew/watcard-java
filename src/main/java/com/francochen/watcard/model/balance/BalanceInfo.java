package com.francochen.watcard.model.balance;

public class BalanceInfo {
    private final String limit;
    private final String balance;

    BalanceInfo(String limit, String balance) {
        this.limit = limit;
        this.balance = balance;
    }

    public String getLimit() {
        return limit;
    }

    public String getBalance() {
        return balance;
    }
}
