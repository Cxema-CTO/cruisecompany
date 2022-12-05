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

public class pageIndex implements OpenPage {
    List<CruiseForSale> sendCruisesForSale = new ArrayList<>();
    int howCruisesForSaleInDB;

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        howCruisesForSaleInDB = CruiseForSaleDAO.howMuchIsTheCruisesForSale();

        if (Pagination.totalPages(howCruisesForSaleInDB) > 1) {
            req.getSession().setAttribute("pagination", "yes");
            Pagination.setCurrentOffsetForPage("index");
            req.getSession().setAttribute("currentPage", Pagination.currentPage);
            req.getSession().setAttribute("totalPages", Pagination.totalPages(howCruisesForSaleInDB));
        } else {
            req.getSession().setAttribute("pagination", "no");
            Pagination.offset = 0;
        }


        sendCruisesForSale.clear();
        sendCruisesForSale.addAll(CruiseForSaleDAO.paginationCruiseForSale(Pagination.orderBy, Pagination.orderByDirection, Pagination.limit, Pagination.offset));

        req.getSession().setAttribute("cruisesForSale", sendCruisesForSale);
        req.getSession().setAttribute("cruisesForSaleCount", CruiseForSaleDAO.howMuchIsTheCruisesForSale());

        String role = (String) req.getSession().getAttribute("role");
        if (Objects.equals(role, "admin") || Objects.equals(role, "user")) {
            User userFromSession = (User) req.getSession().getAttribute("user");
            User userForIndex = UserDAO.getUserFromDB(userFromSession.getUserName());
            req.getSession().setAttribute("user", userForIndex);
        }

        return "index.jsp";
    }
}
