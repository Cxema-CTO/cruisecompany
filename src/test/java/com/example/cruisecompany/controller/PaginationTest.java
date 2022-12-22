package com.example.cruisecompany.controller;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class PaginationTest {

    @Test
    void totalPages() {
        int totalPages = Pagination.totalPages(25);
        assertEquals(5, totalPages);
    }

    @Test
    void orderBy() {
        Pagination.orderBy("Id");
        String order = Pagination.orderBy;
        assertEquals("Id", order);
    }

    @Test
    void changeOrderDirection() {
        if (Objects.equals(Pagination.orderByDirection, "ASC")) Pagination.changeOrderDirection();
        assertEquals("DESC", Pagination.orderByDirection);
    }

    @Test
    void pageBefore() {
        Pagination.currentPage = 7;
        Pagination.pageBefore();
        assertEquals(6, Pagination.currentPage);
    }

    @Test
    void pageNext() {
        Pagination.totalPages = 11;
        Pagination.currentPage = 6;
        Pagination.pageNext();
        assertEquals(7, Pagination.currentPage);
    }

    @Test
    void setCurrentOffsetForPage() {
        Pagination.oldURL = "google";
        Pagination.setCurrentOffsetForPage("yahoo");
        int offset = Pagination.offset;
        assertEquals(0, offset);
    }
}