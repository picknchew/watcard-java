package com.francochen.watcard.model.balance;

import org.jetbrains.annotations.NotNull;
import org.jsoup.nodes.Element;
import pl.droidsonroids.jspoon.ElementConverter;
import pl.droidsonroids.jspoon.annotation.Selector;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class BalancesConverter implements ElementConverter<Map<BalanceType, BalanceInfo>> {
    private static final int ID_INDEX = 0;
    private static final int LIMIT_INDEX = 2;
    private static final int AMOUNT_INDEX = 3;

    @Override
    public Map<BalanceType, BalanceInfo> convert(@NotNull Element node, @NotNull Selector selector) {
        Map<BalanceType, BalanceInfo> balances = new HashMap<>();

        for (Element row : node.children()) {
            BalanceType type = BalanceType.getById(row.child(ID_INDEX).html());
            BigDecimal limit = new BigDecimal(row.child(LIMIT_INDEX).html().substring(1));
            BigDecimal balance = new BigDecimal(row.child(AMOUNT_INDEX).html().substring(1));

            balances.put(type, new BalanceInfo(limit, balance));
        }

        return balances;
    }
}
