package com.example.cruisecompany.utils;

import com.example.cruisecompany.dao.UserDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "checkUserInDB", value = "/CHECK_USER_IN_DB")
public class checkUserInDB extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("username");
        boolean does = UserDAO.assertHasUserInDBbyUserName(userName);
//        System.out.println(userName);
        response.getWriter().write(String.valueOf(does));
        response.getWriter().flush();
        response.getWriter().close();
    }
}
