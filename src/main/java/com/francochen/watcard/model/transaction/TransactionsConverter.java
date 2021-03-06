package com.francochen.watcard.model.transaction;

import com.francochen.watcard.model.balance.BalanceType;
import org.jetbrains.annotations.NotNull;
import org.jsoup.nodes.Element;
import pl.droidsonroids.jspoon.ElementConverter;
import pl.droidsonroids.jspoon.annotation.Selector;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TransactionsConverter implements ElementConverter<List<Transaction>> {
    private static final int DATE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final int BALANCE_TYPE_INDEX = 2;
    private static final int UNITS_INDEX = 3;
    private static final int TRANSACTION_TYPE_INDEX = 4;
    private static final int TERMINAL_INDEX = 5;

    private static final ThreadLocal<DateFormat> DATE_FORMAT = ThreadLocal
            .withInitial(() -> new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a", Locale.ENGLISH));

    @Override
    public List<Transaction> convert(Element node, @NotNull Selector selector) {
        List<Transaction> transactions = new ArrayList<>();

        if (node == null) {
            return transactions;
        }

        for (Element row : node.children()) {
            Date date = null;

            try {
                date = DATE_FORMAT.get().parse(row.child(DATE_INDEX).html());
            } catch (ParseException e) {
                // should never happen
            }

            BigDecimal amount = new BigDecimal(row.child(AMOUNT_INDEX).html()
                    .replace("$", "")
                    .replace(",", ""));
            BalanceType balanceType = BalanceType.getById(row.child(BALANCE_TYPE_INDEX).html());
            int units = Integer.parseInt(row.child(UNITS_INDEX).html());
            String transactionType = row.child(TRANSACTION_TYPE_INDEX).html();
            String terminal = row.child(TERMINAL_INDEX).html();

            transactions.add(new Transaction(date, amount, balanceType, units, transactionType, terminal));
        }

        return transactions;
    }
}
