package com.example.cruisecompany.command.implementation;

import com.example.cruisecompany.command.OpenPage;
import com.example.cruisecompany.controller.Pagination;
import com.example.cruisecompany.dao.CruiseDAO;
import com.example.cruisecompany.dao.OrderDAO;
import com.example.cruisecompany.entity.Cruise;
import com.example.cruisecompany.entity.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class actionConfirmCruise implements OpenPage {
    List<Order> sendOrders = new ArrayList<>();
    int howOrdersInDB;
	Order order;
    int orderId;
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        orderId = Integer.parseInt(req.getParameter("orderId"));
        order = OrderDAO.getOrderFromDB(orderId);
        if(order.isConfirmed()){
            OrderDAO.updateOrderConfirmedInDB(orderId,false);
        }else {
            OrderDAO.updateOrderConfirmedInDB(orderId,true);
        }

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


