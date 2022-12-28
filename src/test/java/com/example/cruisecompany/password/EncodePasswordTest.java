package com.example.cruisecompany.password;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EncodePasswordTest {

    @Test
    void getHashPassword() {
        String testHash = "098f6bcd4621d373cade4e832627b4f6";// MD5 "test"
        String getHash = EncodePassword.getHashPassword("test");
        assertEquals(getHash,testHash);
    }
}