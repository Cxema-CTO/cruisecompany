package com.example.cruisecompany.entity;

import java.sql.Date;

public class CruiseForSale {
    private int id;
    private int shipId;
    private String shipName;
    private java.sql.Date startCruise;
    private java.sql.Date endCruise;
    private int duration;
    private String descriptionEng;
    private String descriptionUkr;
    private String route;
    private String photoLink;
    private int price;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShipId() {
        return shipId;
    }

    public void setShipId(int shipId) {
        this.shipId = shipId;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public Date getStartCruise() {
        return startCruise;
    }

    public void setStartCruise(Date startCruise) {
        this.startCruise = startCruise;
    }

    public Date getEndCruise() {
        return endCruise;
    }

    public void setEndCruise(Date endCruise) {
        this.endCruise = endCruise;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDescriptionEng() {
        return descriptionEng;
    }

    public void setDescriptionEng(String descriptionEng) {
        this.descriptionEng = descriptionEng;
    }

    public String getDescriptionUkr() {
        return descriptionUkr;
    }

    public void setDescriptionUkr(String descriptionUkr) {
        this.descriptionUkr = descriptionUkr;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
