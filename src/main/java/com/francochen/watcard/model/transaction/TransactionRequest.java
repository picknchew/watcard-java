package com.francochen.watcard.model.transaction;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class TransactionRequest extends HashMap<String, String> {
    private static final int DEFAULT_RETURN_ROWS = 1000;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");

    public TransactionRequest(Date dateFrom, Date dateTo) {
        this(dateFrom, dateTo, DEFAULT_RETURN_ROWS);
    }

    public TransactionRequest(Date dateFrom, Date dateTo, int returnRows) {
        put("dateFrom", DATE_FORMAT.format(dateFrom));
        put("dateTo", DATE_FORMAT.format(dateTo));
        put("returnRows", Integer.toString(returnRows));
    }
}
