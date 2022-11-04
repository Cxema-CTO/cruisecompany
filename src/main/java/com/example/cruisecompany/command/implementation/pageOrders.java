package com.example.cruisecompany.command.implementation;

import com.example.cruisecompany.command.OpenPage;
import com.example.cruisecompany.controller.Pagination;
import com.example.cruisecompany.dao.OrderDAO;
import com.example.cruisecompany.entity.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class pageOrders implements OpenPage {
    List<Order> sendOrders = new ArrayList<>();
    int howOrdersInDB;


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        howOrdersInDB = OrderDAO.howMuchIsTheOrders();

        if (Pagination.totalPages(howOrdersInDB) > 1) {
            req.getSession().setAttribute("pagination", "yes");
            Pagination.setCurrentOffsetForPage("orders");
            req.getSession().setAttribute("currentPage", Pagination.currentPage);
            req.getSession().setAttribute("totalPages", Pagination.totalPages(howOrdersInDB));
        } else {
            req.getSession().setAttribute("pagination", "no");
        }

        sendOrders.clear();
        sendOrders.addAll(OrderDAO.paginationOrders(Pagination.orderBy, Pagination.orderByDirection, Pagination.limit, Pagination.offset));
        req.getSession().setAttribute("orders", sendOrders);
        req.getSession().setAttribute("ordersCount", OrderDAO.howMuchIsTheOrders());

        return "orders.jsp";
    }

}
