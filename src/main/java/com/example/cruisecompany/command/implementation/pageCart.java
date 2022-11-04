package com.example.cruisecompany.command.implementation;

import com.example.cruisecompany.command.OpenPage;
import com.example.cruisecompany.controller.Pagination;
import com.example.cruisecompany.dao.CruiseForSaleDAO;
import com.example.cruisecompany.dao.UserCartCruiseDAO;
import com.example.cruisecompany.dao.UserDAO;
import com.example.cruisecompany.entity.CruiseForSale;
import com.example.cruisecompany.entity.User;
import com.example.cruisecompany.entity.UserCartCruise;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class pageCart implements OpenPage {
    List<UserCartCruise> sendCruiseUserBuy = new ArrayList<>();
    int howCruisesUserBuyInDB;

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        int userId = 0;
        String role = (String) req.getSession().getAttribute("role");
        if (role != null) {
            User userFromSession = (User) req.getSession().getAttribute("user");
            userId = userFromSession.getId();
        }
        howCruisesUserBuyInDB = UserCartCruiseDAO.howMuchCruisesUserBuy(userId);

        if (Pagination.totalPages(howCruisesUserBuyInDB) > 1) {
            req.getSession().setAttribute("pagination", "yes");
            Pagination.setCurrentOffsetForPage("cart");
            req.getSession().setAttribute("currentPage", Pagination.currentPage);
            req.getSession().setAttribute("totalPages", Pagination.totalPages(howCruisesUserBuyInDB));
        } else {
            req.getSession().setAttribute("pagination", "no");
        }

        sendCruiseUserBuy.clear();
        sendCruiseUserBuy.addAll(UserCartCruiseDAO.paginationCruisesUserBuy(userId, Pagination.orderBy, Pagination.orderByDirection, Pagination.limit, Pagination.offset));


        req.getSession().setAttribute("cruisesUserBuy", sendCruiseUserBuy);
        req.getSession().setAttribute("cruisesUserBuyCount", UserCartCruiseDAO.howMuchCruisesUserBuy(userId));


        return "user_cart.jsp";
    }

}
