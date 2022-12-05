package com.example.cruisecompany.filter;

import java.util.HashMap;
import java.util.Map;

public class AccessMap {
    protected static Map<String, Integer> bitMask = new HashMap<>();

    //        admin   =    0b00000001 +
//            user    =    0b00000010 +
//            guest   =    0b00000100 +
//            banned  =    0b00001000

    private static final String DOMAIN_NAME = "/cruisecompany_war_exploded/";

    static {
        bitMask.put(DOMAIN_NAME, 0b00001111);
        bitMask.put(DOMAIN_NAME + "403.jsp", 0b00001111);
        bitMask.put(DOMAIN_NAME + "404.jsp", 0b00001111);
        bitMask.put(DOMAIN_NAME + "500.jsp", 0b00001111);
        bitMask.put(DOMAIN_NAME + "add_cruise.jsp", 0b00000001);
        bitMask.put(DOMAIN_NAME + "add_photo.jsp", 0b00000010);
        bitMask.put(DOMAIN_NAME + "add_ship.jsp", 0b00000001);
        bitMask.put(DOMAIN_NAME + "admin.jsp", 0b00000001);
        bitMask.put(DOMAIN_NAME + "buy_cruise.jsp", 0b00000011);
        bitMask.put(DOMAIN_NAME + "cruise_detailed.jsp", 0b00000011);
        bitMask.put(DOMAIN_NAME + "error.jsp", 0b00001111);
        bitMask.put(DOMAIN_NAME + "cruises.jsp", 0b00000001);
        bitMask.put(DOMAIN_NAME + "cruises_for_sale.jsp", 0b00001111);
        bitMask.put(DOMAIN_NAME + "footer.jsp", 0b00000001);
        bitMask.put(DOMAIN_NAME + "guest.jsp", 0b00000001);
        bitMask.put(DOMAIN_NAME + "header.jsp", 0b00000001);
        bitMask.put(DOMAIN_NAME + "index.jsp", 0b00001111);
        bitMask.put(DOMAIN_NAME + "login.jsp", 0b00001111);
        bitMask.put(DOMAIN_NAME + "orders.jsp", 0b00000001);
        bitMask.put(DOMAIN_NAME + "registration.jsp", 0b00001111);
        bitMask.put(DOMAIN_NAME + "ships.jsp", 0b00000001);
        bitMask.put(DOMAIN_NAME + "startApp.jsp", 0b00001111);
        bitMask.put(DOMAIN_NAME + "style.jsp", 0b00000001);
        bitMask.put(DOMAIN_NAME + "user.jsp", 0b00000001);
        bitMask.put(DOMAIN_NAME + "user_banned.jsp", 0b00000001);
        bitMask.put(DOMAIN_NAME + "user_cart.jsp", 0b00000010);
        bitMask.put(DOMAIN_NAME + "users.jsp", 0b00000011);
    }
}
