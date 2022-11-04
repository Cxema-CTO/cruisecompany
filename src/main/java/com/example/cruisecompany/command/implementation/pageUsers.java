package com.example.cruisecompany.command.implementation;

import com.example.cruisecompany.command.OpenPage;
import com.example.cruisecompany.controller.Pagination;
import com.example.cruisecompany.dao.UserDAO;
import com.example.cruisecompany.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class pageUsers implements OpenPage {

    List<User> sendUsers = new ArrayList<>();
    int howUsersInDB;

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        howUsersInDB = UserDAO.howMuchIsTheFish();

        if (Pagination.totalPages(howUsersInDB) > 1) {
            req.getSession().setAttribute("pagination", "yes");
            Pagination.setCurrentOffsetForPage("users");
            req.getSession().setAttribute("currentPage", Pagination.currentPage);
            req.getSession().setAttribute("totalPages", Pagination.totalPages(howUsersInDB));
        } else {
            req.getSession().setAttribute("pagination", "no");
        }

        sendUsers.clear();
        sendUsers.addAll(UserDAO.paginationUsers(Pagination.orderBy, Pagination.orderByDirection, Pagination.limit, Pagination.offset));
        req.getSession().setAttribute("users", sendUsers);
        req.getSession().setAttribute("usersCount", UserDAO.howMuchIsTheFish());

        return "users.jsp";
    }
}
