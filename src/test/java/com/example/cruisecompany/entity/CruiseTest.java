package com.example.cruisecompany.entity;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class CruiseTest {
    static Cruise cruise = new Cruise();

    @BeforeEach
    public void init() {
        cruise.setId(1);
        cruise.setShipId(2);
        cruise.setCabinsSold(30);
        cruise.setStartCruise(new Date(246927600L));
        cruise.setEndCruise(new Date(3402684000L));
        cruise.setRoute("Milano Paris");
        cruise.setDuration(5);
        cruise.setCruisePrice(100);
    }


    @Test
    void getId() {
        int id = cruise.getId();
        assertEquals(1, id);
    }

    @Test
    void setId() {
        cruise.setId(2);
        int id = cruise.getId();
        assertEquals(2, id);
    }

    @Test
    void getShipId() {
//        cruise.setShipId(2);
        int id = cruise.getShipId();
        assertEquals(2, id);
    }

    @Test
    void setShipId() {
        cruise.setShipId(3);
        int id = cruise.getShipId();
        assertEquals(3, id);
    }

    @Test
    void getCabinsSold() {
        int id = cruise.getCabinsSold();
        assertEquals(30, id);
    }

    @Test
    void setCabinsSold() {
        cruise.setCabinsSold(40);
        int id = cruise.getCabinsSold();
        assertEquals(40, id);
    }


    @Test
    void getStartCruise() {
        Date expDate = new Date(246927600L);
        Date date = (Date) cruise.getStartCruise();
        assertEquals(expDate, date);
    }

    @Test
    void setStartCruise() {
        Date expDate = new Date(246927611L);
        cruise.setStartCruise(expDate);
        Date date = (Date) cruise.getStartCruise();
        assertEquals(expDate, date);
    }


    @Test
    void getEndCruise() {
        Date expDate = new Date(3402684000L);
        Date date = (Date) cruise.getEndCruise();
        assertEquals(expDate, date);
    }

    @Test
    void setEndCruise() {
        Date expDate = new Date(3402684111L);
        cruise.setEndCruise(expDate);
        Date date = (Date) cruise.getEndCruise();
        assertEquals(expDate, date);
    }

    @Test
    void getDuration() {
        int id = cruise.getDuration();
        assertEquals(5, id);
    }

    @Test
    void setDuration() {
        cruise.setDuration(7);
        int id = cruise.getDuration();
        assertEquals(7, id);
    }

    @Test
    void getRoute() {
        String route = cruise.getRoute();
        assertEquals("Milano Paris", route);
    }

    @Test
    void setRoute() {
        String newRoute = "London Berlin";
        cruise.setRoute(newRoute);
        String route = cruise.getRoute();
        assertEquals(newRoute, route);
    }

    @Test
    void getCruisePrice() {
        int id = cruise.getCruisePrice();
        assertEquals(100, id);
    }

    @Test
    void setCruisePrice() {
        cruise.setCruisePrice(120);
        int id = cruise.getCruisePrice();
        assertEquals(120, id);
    }

    @Test
    void testToString() {
        String expCruise = "Cruise{id=1, shipId=2, cabinsSold=30, startCruise=1970-01-03, endCruise=1970-02-09, duration=5, route='Milano Paris', cruisePrice=100}";
        String actCruise = cruise.toString();
        assertEquals(expCruise, actCruise);
    }
}