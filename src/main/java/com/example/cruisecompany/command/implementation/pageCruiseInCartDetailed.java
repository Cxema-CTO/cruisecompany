package com.example.cruisecompany.command.implementation;

import com.example.cruisecompany.command.OpenPage;
import com.example.cruisecompany.dao.UserCartCruiseDAO;
import com.example.cruisecompany.entity.UserCartCruise;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class pageCruiseInCartDetailed implements OpenPage {
    UserCartCruise cruiseDetailed;
    int orderId;

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        orderId = Integer.parseInt(req.getParameter("orderId"));
        cruiseDetailed = UserCartCruiseDAO.getOneCruiseWhatUserByFromDB(orderId);
        req.getSession().setAttribute("cruiseDetailed", cruiseDetailed);

        return "cruise_detailed.jsp";
    }

}
