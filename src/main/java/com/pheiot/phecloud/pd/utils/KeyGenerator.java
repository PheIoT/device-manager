package com.pheiot.phecloud.pd.utils;

import com.pheiot.bamboo.common.utils.number.RandomUtil;

import java.util.UUID;

public class KeyGenerator {

    public final static int KEY_LENGTH = 32;
    public final static int SECRET_LENGTH = 12;

    public static String generateKey() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String generateSecret() {
        return RandomUtil.randomStringFixLength(SECRET_LENGTH);
    }
}
