package com.firsttoy.redbeardandbread.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class StringUtils {

    public static String generateRandomString() {
        return RandomStringUtils.randomAlphanumeric(4);
    }

}
