package com.example.cruisecompany.connectionpool;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionPoolTest {


    @Test
    void getInstance() {
        ConnectionPool poolFirst = ConnectionPool.getInstance();
        ConnectionPool poolSecond = ConnectionPool.getInstance();

        assertEquals(poolFirst, poolSecond);
    }

    @Test
    void getConnection() throws SQLException {
        assertTrue(ConnectionPool.getInstance().getConnection().isValid(10));
    }
}