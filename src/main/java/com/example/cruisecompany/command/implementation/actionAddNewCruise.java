package com.example.cruisecompany.command.implementation;

import com.example.cruisecompany.command.OpenPage;
import com.example.cruisecompany.dao.CruiseDAO;
import com.example.cruisecompany.dao.OrderDAO;
import com.example.cruisecompany.dao.ShipDAO;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.SQLException;

public class actionAddNewCruise implements OpenPage {

    private static final Logger LOGGER = Logger.getLogger(actionAddNewCruise.class);


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int shipId = Integer.parseInt(req.getParameter("shipId"));
        int cabins = 0;
        Date startDate = Date.valueOf(req.getParameter("startDate"));
        Date endDate = Date.valueOf(req.getParameter("endDate"));
        int duration = (int) ((endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24));
        String route = req.getParameter("route");
        int price = Integer.parseInt(req.getParameter("price"));
        String whichJSPtoRedirect = "controller?open=CRUISES";

        try {
            CruiseDAO.createNewCruiseInDB(shipId, cabins, startDate, endDate, duration, route, price);
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
        whichJSPtoRedirect = "controller?open=CRUISES";
        return whichJSPtoRedirect;
    }
}
