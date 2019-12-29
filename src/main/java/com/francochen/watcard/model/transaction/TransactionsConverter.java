package com.francochen.watcard.model.transaction;

import com.francochen.watcard.model.balance.BalanceType;
import org.jetbrains.annotations.NotNull;
import org.jsoup.nodes.Element;
import pl.droidsonroids.jspoon.ElementConverter;
import pl.droidsonroids.jspoon.annotation.Selector;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TransactionsConverter implements ElementConverter<List<Transaction>> {
    private static final int DATE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final int BALANCE_TYPE_INDEX = 2;
    private static final int UNITS_INDEX = 3;
    private static final int TRANSACTION_TYPE_INDEX = 4;
    private static final int TERMINAL_INDEX = 5;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy h:mm:ss a");

    @Override
    public List<Transaction> convert(@NotNull Element node, @NotNull Selector selector) {
        List<Transaction> transactions = new ArrayList<>();

        for (Element row : node.children()) {
            LocalDateTime date = LocalDateTime.parse(row.child(DATE_INDEX).html(), FORMATTER);
            BigDecimal amount = new BigDecimal(row.child(AMOUNT_INDEX).html().replace("$", ""));
            BalanceType balanceType = BalanceType.getById(row.child(BALANCE_TYPE_INDEX).html());
            int units = Integer.parseInt(row.child(UNITS_INDEX).html());
            String transactionType = row.child(TRANSACTION_TYPE_INDEX).html();
            String terminal = row.child(TERMINAL_INDEX).html();

            transactions.add(new Transaction(date, amount, balanceType, units, transactionType, terminal));
        }

        return transactions;
    }
}
