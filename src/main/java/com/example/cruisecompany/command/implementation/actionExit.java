package com.example.cruisecompany.command.implementation;

import com.example.cruisecompany.command.OpenPage;
import com.example.cruisecompany.controller.Pagination;
import com.example.cruisecompany.dao.CruiseForSaleDAO;
import com.example.cruisecompany.dao.UserDAO;
import com.example.cruisecompany.entity.CruiseForSale;
import com.example.cruisecompany.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class actionExit implements OpenPage {
    List<CruiseForSale> sendCruisesForSale = new ArrayList<>();
    int howCruisesForSaleInDB;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        request.getSession().setAttribute("role", "guest");
        Pagination.filter = "off";

        howCruisesForSaleInDB = CruiseForSaleDAO.howMuchIsTheCruisesForSale();

        if (Pagination.totalPages(howCruisesForSaleInDB) > 1) {
            request.getSession().setAttribute("pagination", "yes");
            Pagination.setCurrentOffsetForPage("index");
            request.getSession().setAttribute("currentPage", Pagination.currentPage);
            request.getSession().setAttribute("totalPages", Pagination.totalPages(howCruisesForSaleInDB));
        } else {
            request.getSession().setAttribute("pagination", "no");
        }

        sendCruisesForSale.clear();
        sendCruisesForSale.addAll(CruiseForSaleDAO.paginationCruiseForSale(Pagination.orderBy, Pagination.orderByDirection, Pagination.limit, Pagination.offset));

        request.getSession().setAttribute("cruisesForSale", sendCruisesForSale);
        request.getSession().setAttribute("cruisesForSaleCount", CruiseForSaleDAO.howMuchIsTheCruisesForSale());

        String role = (String) request.getSession().getAttribute("role");
        if (Objects.equals(role, "admin") || Objects.equals(role, "user")) {
            User userFromSession = (User) request.getSession().getAttribute("user");
            User userForIndex = UserDAO.getUserFromDB(userFromSession.getUserName());
            request.getSession().setAttribute("user", userForIndex);
        }


        return "index.jsp";
    }


}
