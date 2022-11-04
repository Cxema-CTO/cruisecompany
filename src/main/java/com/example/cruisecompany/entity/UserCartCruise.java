package com.example.cruisecompany.entity;

import java.sql.Date;

public class UserCartCruise {

    int orderId;
    int cruiseId;
    int shipId;
    String shipName;
    String route;
    Boolean isConfirmed;
    String descriptionEng;
    String descriptionUkr;
    int duration;
    int price;
    Date startCruise;
    Date endCruise;
    String photoLink;
    int staff;
    int cabins;


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCruiseId() {
        return cruiseId;
    }

    public void setCruiseId(int cruiseId) {
        this.cruiseId = cruiseId;
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

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public Boolean getConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        isConfirmed = confirmed;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    public int getStaff() {
        return staff;
    }

    public void setStaff(int staff) {
        this.staff = staff;
    }

    public int getCabins() {
        return cabins;
    }

    public void setCabins(int cabins) {
        this.cabins = cabins;
    }


    @Override
    public String toString() {
        return "UserCartCruise{" +
                "orderId=" + orderId +
                ", cruiseId=" + cruiseId +
                ", shipId=" + shipId +
                ", shipName='" + shipName + '\'' +
                ", route='" + route + '\'' +
                ", isConfirmed=" + isConfirmed +
                ", descriptionEng='" + descriptionEng + '\'' +
                ", descriptionUkr='" + descriptionUkr + '\'' +
                ", duration=" + duration +
                ", price=" + price +
                ", startCruise=" + startCruise +
                ", endCruise=" + endCruise +
                ", photoLink='" + photoLink + '\'' +
                ", staff=" + staff +
                ", cabins=" + cabins +
                '}';
    }
}
