package com.example.cruisecompany.utils;

import com.example.cruisecompany.controller.Pagination;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
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
        String filter = request.getParameter("filter");
        Date from, to;
        int duration;


        if (Objects.equals(click, "before")) {
            Pagination.pageBefore();
        }
        if (Objects.equals(click, "next")) {
            Pagination.pageNext();
        }

        if (orderBy != null) {
            Pagination.orderBy(orderBy);
        }


        if (Objects.equals(filter, "on")) {
            from = Date.valueOf(request.getParameter("from"));
            to = Date.valueOf(request.getParameter("to"));
            duration = Integer.parseInt(request.getParameter("duration"));

            Pagination.filter = "on";
            Pagination.from = from;
            Pagination.to = to;
            if (duration == 0) {
                Pagination.durationMin = 0;
                Pagination.durationMax = Integer.MAX_VALUE;
            }
            if (duration == 1) {
                Pagination.durationMin = 0;
                Pagination.durationMax = 7;
            }
            if (duration == 2) {
                Pagination.durationMin = 7;
                Pagination.durationMax = 15;
            }
            if (duration == 3) {
                Pagination.durationMin = 15;
                Pagination.durationMax = Integer.MAX_VALUE;
            }
            request.getSession().setAttribute("filter", "on");
            request.getSession().setAttribute("filterFrom", from);
            request.getSession().setAttribute("filterTo", to);
            request.getSession().setAttribute("filterDuration", duration);
        }

        if (Objects.equals(filter, "off")) {
            request.getSession().setAttribute("filter", "off");
            Pagination.filter = "off";
            Pagination.from = Pagination.FIRST_DAY;
            Pagination.to = Pagination.LAST_DAY;
            Pagination.durationMin = 0;
            Pagination.durationMax = Integer.MAX_VALUE;

            request.getSession().setAttribute("filter", "off");
            request.getSession().setAttribute("filterFrom", Pagination.FIRST_DAY);
            request.getSession().setAttribute("filterTo", Pagination.LAST_DAY);
            request.getSession().setAttribute("filterDuration", 0);
        }

    }
}
