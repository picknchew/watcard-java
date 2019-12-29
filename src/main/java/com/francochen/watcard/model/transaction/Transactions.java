package com.francochen.watcard.model.transaction;

import pl.droidsonroids.jspoon.annotation.Selector;

import java.util.List;

public class Transactions {
    @Selector(value = "tbody", converter = TransactionsConverter.class)
    private List<Transaction> transactions;

    private Transactions() {}

    public List<Transaction> get() {
        return transactions;
    }
}
