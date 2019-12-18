package com.francochen.watcard.model.balance;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum BalanceType {
    RESIDENCE_PLAN("1"),
    SUPER_SAVER_MP("2"),
    SAVER_MP("3"),
    CASUAL_MP("4"),
    FLEXIBLE_1("5"),
    FLEXIBLE_2("6"),
    TRANSFER_MP("7"),
    DONS_MEAL_ALLOW("8"),
    DONS_FLEX("9"),
    UNALLOCATED("A"),
    DEPT_CHARGE("B"),
    OVERDRAFT("C");

    private static final Map<String, BalanceType> idLookupMap;

    static {
        idLookupMap = Arrays.stream(values()).collect(Collectors.toMap(BalanceType::getId, Function.identity()));
    }

    String id;
    
    BalanceType(String id) {
        this.id = id;

    }

    public String getId() {
        return id;
    }

    public static BalanceType getById(String id) {
        return idLookupMap.get(id);
    }
}
