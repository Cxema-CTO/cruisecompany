package com.example.cruisecompany.utils;

import org.apache.log4j.Logger;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;


public class HashName {

    private static final Logger LOGGER = Logger.getLogger(HashName.class);
    private static final String MD5 = "MD5";
    private static final String STRING_ZERO = "0";
    private static final int POSITIVE_SIGN = 1;
    private static final int RADIX = 16;
    private static final int HASH_LENGTH = 32;
    private static final int ZERO_ARRAY = 0;
    private static final int OFFSET = 0;

    public static String setHashName(String input) {

        MessageDigest messageDigest;
        byte[] byteDigest = new byte[ZERO_ARRAY];
        String stringForHash = (input + LocalDateTime.now());

        try {
            messageDigest = MessageDigest.getInstance(MD5);
            messageDigest.reset();
            byteDigest = messageDigest.digest(stringForHash.getBytes());
        } catch (NoSuchAlgorithmException exception) {
            LOGGER.error(exception);
        }

        BigInteger number = new BigInteger(POSITIVE_SIGN, byteDigest);
        StringBuilder hashHex = new StringBuilder(number.toString(RADIX));
        while (hashHex.length() < HASH_LENGTH) {
            hashHex.insert(OFFSET, STRING_ZERO);
        }
        return hashHex.toString();
    }
}
