package com.firsttoy.redbeardandbread.exception;

import lombok.Getter;

@Getter
public enum Exceptions implements ExceptionInformation {
    
    // ITEM

    ITEM_ALREADY_EXISTS(600, "given item already exists"),
    ITEM_NOT_FOUND(601, "no item found"),
    ITEM_OUT_OF_STOCK(602, "out of stock"),
    ITEM_UNAVAILABLE(603, "item not available");

    private final int code;
    private final String description;

    Exceptions(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
