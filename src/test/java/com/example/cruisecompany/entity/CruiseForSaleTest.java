package com.example.cruisecompany.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;


class CruiseForSaleTest {

    static CruiseForSale cfs = new CruiseForSale();

    @BeforeEach
    public void init() {
        cfs.setId(1);
        cfs.setShipId(2);
        cfs.setShipName("LegendOfSea");
        cfs.setStartCruise(new Date(246927600L));
        cfs.setEndCruise(new Date(246927800L));
        cfs.setDuration(10);
        cfs.setDescriptionEng("Hello");
        cfs.setDescriptionUkr("Привіт");
        cfs.setRoute("Madrid Lisbon");
        cfs.setPhotoLink("google.com");
        cfs.setPrice(100);
        cfs.setCabinsLeft(10);
    }


    @Test
    void getCabinsLeft() {
        int cabinsLeft = cfs.getCabinsLeft();
        assertEquals(10, cabinsLeft);
    }

    @Test
    void setCabinsLeft() {
        cfs.setCabinsLeft(12);
        int cabinsLeft = cfs.getCabinsLeft();
        assertEquals(12, cabinsLeft);
    }

    @Test
    void getId() {
        int id = cfs.getId();
        assertEquals(1, id);
    }

    @Test
    void setId() {
        cfs.setId(2);
        int id = cfs.getId();
        assertEquals(2, id);
    }

    @Test
    void getShipId() {
        int shipId = cfs.getShipId();
        assertEquals(2, shipId);
    }

    @Test
    void setShipId() {
        cfs.setShipId(3);
        int shipId = cfs.getShipId();
        assertEquals(3, shipId);
    }

    @Test
    void getShipName() {
        String name = cfs.getShipName();
        assertEquals("LegendOfSea", name);
    }

    @Test
    void setShipName() {
        cfs.setShipName("DreamOfSea");
        String name = cfs.getShipName();
        assertEquals("DreamOfSea", name);
    }

    @Test
    void getStartCruise() {
        Date actDate = cfs.getStartCruise();
        Date expDate = new Date(246927600L);
        assertEquals(expDate, actDate);
    }

    @Test
    void setStartCruise() {
        cfs.setStartCruise(new Date(246927611L));
        Date actDate = cfs.getStartCruise();
        Date expDate = new Date(246927611L);
        assertEquals(expDate, actDate);
    }

    @Test
    void getEndCruise() {
        Date actDate = cfs.getEndCruise();
        Date expDate = new Date(246927800L);
        assertEquals(expDate, actDate);
    }

    @Test
    void setEndCruise() {
        cfs.setEndCruise(new Date(2469276811L));
        Date actDate = cfs.getEndCruise();
        Date expDate = new Date(2469276811L);
        assertEquals(expDate, actDate);
    }

    @Test
    void getDuration() {
        int duration = cfs.getDuration();
        assertEquals(10,duration);
    }

    @Test
    void setDuration() {
        cfs.setDuration(12);
        int duration = cfs.getDuration();
        assertEquals(12,duration);
    }

    @Test
    void getDescriptionEng() {
        String description = cfs.getDescriptionEng();
        assertEquals("Hello",description);
    }

    @Test
    void setDescriptionEng() {
        cfs.setDescriptionEng("Hi");
        String description = cfs.getDescriptionEng();
        assertEquals("Hi",description);
    }

    @Test
    void getDescriptionUkr() {
        String description = cfs.getDescriptionUkr();
        assertEquals("Привіт",description);
    }

    @Test
    void setDescriptionUkr() {
        cfs.setDescriptionUkr("Хелоу");
        String description = cfs.getDescriptionUkr();
        assertEquals("Хелоу",description);
    }

    @Test
    void getRoute() {
        String route = cfs.getRoute();
        assertEquals("Madrid Lisbon",route);
    }

    @Test
    void setRoute() {
        cfs.setDescriptionUkr("Lisbon Madrid");
        String route = cfs.getRoute();
        assertEquals("Madrid Lisbon",route);
    }

    @Test
    void getPhotoLink() {
        String url = cfs.getPhotoLink();
        assertEquals("google.com",url);
    }

    @Test
    void setPhotoLink() {
        cfs.setPhotoLink("yahoo.com");
        String url = cfs.getPhotoLink();
        assertEquals("yahoo.com",url);
    }

    @Test
    void getPrice() {
        int price = cfs.getPrice();
        assertEquals(100,price);
    }

    @Test
    void setPrice() {
        cfs.setPrice(120);
        int price = cfs.getPrice();
        assertEquals(120,price);
    }
}