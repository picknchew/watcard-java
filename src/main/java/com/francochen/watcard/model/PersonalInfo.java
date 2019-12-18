package com.francochen.watcard.model;

import pl.droidsonroids.jspoon.annotation.Selector;

public class PersonalInfo {
    private static final int IMAGE_INDEX = 1;

    @Selector(value = ".ow-id-fullname-xs")
    private String fullName;

    @Selector(value = ".ow-id-account-xs")
    private String accountId;

    @Selector(value = "img", index = IMAGE_INDEX, attr = "data-original")
    private String imageUrl;

    public String getFullName() {
        return fullName;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
