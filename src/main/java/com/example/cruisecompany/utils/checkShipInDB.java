package com.example.cruisecompany.utils;

import com.example.cruisecompany.dao.ShipDAO;
import com.example.cruisecompany.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "checkShipInDB", value = "/CHECK_SHIP_IN_DB")
public class checkShipInDB extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String shipName = request.getParameter("shipname");
        boolean does = ShipDAO.assertHasShipInDBbyShipName(shipName);
        response.getWriter().write(String.valueOf(does));
        response.getWriter().flush();
        response.getWriter().close();
    }
}
