package com.example.cruisecompany.command.implementation;

import com.example.cruisecompany.command.OpenPage;
import com.example.cruisecompany.dao.UserDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class actionRegistrationNewUser implements OpenPage {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");
        String whichJSPtoRedirect = "";

        boolean isAdmin = false;
        try {
            if (request.getParameter("is_admin").equals("on")) isAdmin = true;
        } catch (NullPointerException exception) {
//            System.out.println("is_legal null");
        }

        if (UserDAO.assertHasUserInDBbyUserName(userName)) {
            request.getSession().setAttribute("error_message", "error.assert_username");
            whichJSPtoRedirect ="error.jsp";
        } else {
            if (!password.equals(confirmPassword)) {
                request.getSession().setAttribute("error_message", "error.incorrect_password");
                whichJSPtoRedirect ="error.jsp";
            } else {
                try {
                    UserDAO.createNewUserInDB(userName, password, isAdmin);
                    request.getSession().setAttribute("role", "user");
                    request.getSession().setAttribute("user_name", userName);
                    whichJSPtoRedirect ="index.jsp";
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return whichJSPtoRedirect;
    }
}
