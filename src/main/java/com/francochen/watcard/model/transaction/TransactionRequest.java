package com.francochen.watcard.model.transaction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class TransactionRequest extends HashMap<String, String> {
    private static final int DEFAULT_RETURN_ROWS = 1000;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    public TransactionRequest(LocalDate dateFrom, LocalDate dateTo) {
        this(dateFrom, dateTo, DEFAULT_RETURN_ROWS);
    }

    public TransactionRequest(LocalDate dateFrom, LocalDate dateTo, int returnRows) {
        put("dateFrom", dateFrom.format(FORMATTER));
        put("dateTo", dateTo.format(FORMATTER));
        put("returnRows", Integer.toString(returnRows));
    }
}
