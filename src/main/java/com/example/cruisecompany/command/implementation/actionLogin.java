package com.example.cruisecompany.command.implementation;

import com.example.cruisecompany.command.OpenPage;
import com.example.cruisecompany.dao.UserDAO;
import com.example.cruisecompany.entity.User;
import com.example.cruisecompany.password.EncodePassword;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class actionLogin implements OpenPage {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        User user = UserDAO.getUserFromDB(userName);
        String whichJSPtoRedirect = "";
        if (user != null) {
            if (Objects.equals(user.getUserName(), userName) && Objects.equals(user.getPassword(), EncodePassword.getHashPassword(password))) {
                request.getSession().setAttribute("user", user);


                if (user.isBanned()) {
                    request.getSession().setAttribute("role", "banned");
                } else {
                    if (user.isAdmin()) {
                        request.getSession().setAttribute("role", "admin");
                    } else {
                        request.getSession().setAttribute("role", "user");
                    }
                }
                whichJSPtoRedirect = "index.jsp";
            }

            if (Objects.equals(user.getUserName(), userName) && !Objects.equals(user.getPassword(), EncodePassword.getHashPassword(password))) {
                request.getSession().setAttribute("error_message", "error.incorrect_password");
                whichJSPtoRedirect = "error.jsp";
            }
        } else {
            request.getSession().setAttribute("error_message", "error.don't_find_user");
            whichJSPtoRedirect = "error.jsp";
        }

        return whichJSPtoRedirect;
    }
}
