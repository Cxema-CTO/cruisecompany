package com.example.cruisecompany.command.implementation;

import com.example.cruisecompany.command.OpenPage;
import com.example.cruisecompany.dao.UserDAO;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class actionRegistrationNewUser implements OpenPage {
    private static final Logger LOGGER = Logger.getLogger(actionRegistrationNewUser.class);

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
            LOGGER.info("Parameter \"is_admin\" off");
        }

        if (UserDAO.assertHasUserInDBbyUserName(userName)) {
            request.getSession().setAttribute("error_message", "error.assert_username");
            whichJSPtoRedirect = "error.jsp";
        } else {
            if (!password.equals(confirmPassword)) {
                request.getSession().setAttribute("error_message", "error.incorrect_password");
                whichJSPtoRedirect = "error.jsp";
            } else {
                try {
                    UserDAO.createNewUserInDB(userName, password, isAdmin);
                    request.getSession().setAttribute("role", "user");
                    request.getSession().setAttribute("user_name", userName);
                    request.getSession().setAttribute("user", UserDAO.getUserFromDB(userName));
                    whichJSPtoRedirect = "add_photo.jsp";
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return whichJSPtoRedirect;
    }
}
