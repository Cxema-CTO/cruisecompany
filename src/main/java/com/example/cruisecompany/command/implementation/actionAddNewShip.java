package com.example.cruisecompany.command.implementation;

import com.example.cruisecompany.command.OpenPage;
import com.example.cruisecompany.dao.ShipDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class actionAddNewShip implements OpenPage {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String shipName = req.getParameter("shipname");
        int cabins = Integer.parseInt(req.getParameter("cabins"));
        String descriptionEng = req.getParameter("descriptionEng");
        String descriptionUkr = req.getParameter("descriptionUkr");
        String photoLink = req.getParameter("photoLink");
        int staff = Integer.parseInt(req.getParameter("staff"));
        String whichJSPtoRedirect;

        if (ShipDAO.assertHasShipInDBbyShipName(shipName)) {
            req.getSession().setAttribute("error_message", "error.assert_shipname");
            whichJSPtoRedirect = "error.jsp";
        } else {
            try {
                ShipDAO.createNewShipInDB(shipName, cabins, descriptionEng, descriptionUkr, photoLink, staff);
                whichJSPtoRedirect = "controller?open=SHIPS";
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return whichJSPtoRedirect;
    }
}
