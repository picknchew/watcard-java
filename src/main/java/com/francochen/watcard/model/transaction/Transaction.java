package com.francochen.watcard.model.transaction;

import com.francochen.watcard.model.balance.BalanceType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    private final LocalDateTime dateTime;
    private final BigDecimal amount;
    private final BalanceType balanceType;
    private final int units;
    private final String transactionType;
    private final String terminal;

    Transaction(LocalDateTime dateTime, BigDecimal amount, BalanceType balanceType,
                int units, String transactionType, String terminal) {
        this.dateTime = dateTime;
        this.amount = amount;
        this.balanceType = balanceType;
        this.units = units;
        this.transactionType = transactionType;
        this.terminal = terminal;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BalanceType getBalanceType() {
        return balanceType;
    }

    public int getUnits() {
        return units;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public String getTerminal() {
        return terminal;
    }
}
