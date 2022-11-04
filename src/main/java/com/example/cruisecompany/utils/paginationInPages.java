package com.example.cruisecompany.utils;

import com.example.cruisecompany.controller.Pagination;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@WebServlet(name = "paginationInPages", value = "/PAGINATION")
public class paginationInPages extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String click = request.getParameter("click");
        String orderBy = request.getParameter("orderBy");
        if (Objects.equals(click, "before")) {
            Pagination.pageBefore();
        }
        if (Objects.equals(click, "next")) {
            Pagination.pageNext();
        }

        if (orderBy != null) {
            Pagination.orderBy(orderBy);
        }
    }
}
