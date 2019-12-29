package com.francochen.watcard.model.balance;

import pl.droidsonroids.jspoon.annotation.Selector;

import java.util.Map;

public class Balances {
    @Selector(value = "tbody", converter = BalancesConverter.class)
    private Map<BalanceType, BalanceInfo> balances;

    private Balances() {}

    public BalanceInfo get(BalanceType type) {
        return balances.get(type);
    }
}
