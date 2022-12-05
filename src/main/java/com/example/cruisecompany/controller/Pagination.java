package com.example.cruisecompany.controller;

import java.sql.Date;
import java.util.Objects;

public class Pagination {
    public static int currentPage = 1;
    public static int totalPages = 0;
    public static int limit = 5;
    public static int offset;
    public static String oldURL;
    public static String orderBy = "id";

    public static String filter = "off";

    public static final Date FIRST_DAY = new Date(246927600L);
    public static final Date LAST_DAY = new Date(3402684000L);
    public static Date from = FIRST_DAY;
    public static Date to = LAST_DAY;
    public static int durationMin = 0;
    public static int durationMax = Integer.MAX_VALUE;

    static String orderByOLd = new StringBuilder().append(orderBy).toString();
    public static String orderByDirection = "ASC";


    public static int totalPages(int rowsInDB) {
        totalPages = rowsInDB / limit + 1;
        if (rowsInDB % limit == 0) totalPages = rowsInDB / limit;
        return totalPages;
    }

    public static void orderBy(String order) {
        orderBy = order;
        if (Objects.equals(orderByOLd, orderBy)) changeOrderDirection();
        if (!Objects.equals(orderByOLd, orderBy)) {
            orderByOLd = new StringBuilder().append(orderBy).toString();
            Pagination.orderByDirection = "ASC";
        }
    }

    static void changeOrderDirection() {
        if (Objects.equals(orderByDirection, "ASC")) {
            Pagination.orderByDirection = "DESC";
        } else {
            Pagination.orderByDirection = "ASC";
        }
    }

    public static void pageBefore() {
        currentPage--;
        if (currentPage < 1) currentPage = 1;
    }

    public static void pageNext() {
        currentPage++;
        if (currentPage > totalPages) currentPage = totalPages;
    }

    public static void setCurrentOffsetForPage(String url) {
        if (!Objects.equals(oldURL, url)) {
            Pagination.currentPage = 1;
            Pagination.orderBy = "id";
            Pagination.orderByDirection = "ASC";
            oldURL = url;
        }
        offset = (currentPage - 1) * limit;
    }
}
