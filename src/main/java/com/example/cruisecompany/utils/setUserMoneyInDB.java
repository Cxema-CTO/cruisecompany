package com.example.cruisecompany.utils;

import com.example.cruisecompany.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "setUserMoneyInDB", value = "/SET_MONEY")
public class setUserMoneyInDB extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("username");
        int howMuch = Integer.parseInt(request.getParameter("howmuch"));
        if (howMuch < 99) howMuch = 100;
        if (howMuch > 100000) howMuch = 100000;
        UserDAO.setMoneyUserInDB(userName, howMuch);
    }
}
