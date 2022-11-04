package com.example.cruisecompany.command.implementation;

import com.example.cruisecompany.command.OpenPage;
import com.example.cruisecompany.dao.CruiseDAO;
import com.example.cruisecompany.dao.OrderDAO;
import com.example.cruisecompany.dao.UserDAO;
import com.example.cruisecompany.entity.Cruise;
import com.example.cruisecompany.entity.Order;
import com.example.cruisecompany.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class pageBuyCruise implements OpenPage {
    String userName, whichJSPtoRedirect;
    User user;
    Cruise cruise;
    int cruiseId, setUserAccountBalance;

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        cruiseId = Integer.parseInt(req.getParameter("cruiseId"));
        userName = req.getParameter("userName");

        cruise = CruiseDAO.getCruiseFromDB(cruiseId);
        user = UserDAO.getUserFromDB(userName);

        setUserAccountBalance = user.getAccountBalance() - cruise.getCruisePrice();
        UserDAO.setMoneyUserInDB(userName, setUserAccountBalance);
        CruiseDAO.setCabinsSoldInDB(cruise.getId(), cruise.getCabinsSold() + 1);
        OrderDAO.createNewOrderInDB(user.getId(), cruise.getId(), true, false);

        String role = (String) req.getSession().getAttribute("role");
        if (role != null) {
            User userFromSession = (User) req.getSession().getAttribute("user");
            User userForIndex = UserDAO.getUserFromDB(userFromSession.getUserName());
            req.getSession().setAttribute("user", userForIndex);
        }

        whichJSPtoRedirect = "buy_cruise.jsp";


        return whichJSPtoRedirect;
    }

}
