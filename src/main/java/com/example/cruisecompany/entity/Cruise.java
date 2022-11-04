package com.example.cruisecompany.entity;

import java.util.Date;

public class Cruise {
    private int id;
    private int shipId;
    private int cabinsSold;
    private java.sql.Date startCruise;
    private java.sql.Date endCruise;
    private int duration;
    private String route;
    private int cruisePrice;



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

    public int getCabinsSold() {
        return cabinsSold;
    }

    public void setCabinsSold(int cabinsSold) {
        this.cabinsSold = cabinsSold;
    }

    public Date getStartCruise() {
        return startCruise;
    }

    public void setStartCruise(java.sql.Date startCruise) {
        this.startCruise = startCruise;
    }

    public Date getEndCruise() {
        return endCruise;
    }

    public void setEndCruise(java.sql.Date endCruise) {
        this.endCruise = endCruise;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public int getCruisePrice() {
        return cruisePrice;
    }

    public void setCruisePrice(int cruisePrice) {
        this.cruisePrice = cruisePrice;
    }


    @Override
    public String toString() {
        return "Cruise{" +
                "id=" + id +
                ", shipId=" + shipId +
                ", cabinsSold=" + cabinsSold +
                ", startCruise=" + startCruise +
                ", endCruise=" + endCruise +
                ", duration=" + duration +
                ", route='" + route + '\'' +
                ", cruisePrice=" + cruisePrice +
                '}';
    }
}
