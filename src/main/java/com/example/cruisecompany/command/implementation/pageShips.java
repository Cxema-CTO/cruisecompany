package com.example.cruisecompany.command.implementation;

import com.example.cruisecompany.command.OpenPage;
import com.example.cruisecompany.controller.Pagination;
import com.example.cruisecompany.dao.ShipDAO;
import com.example.cruisecompany.entity.Ship;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class pageShips implements OpenPage {

    List<Ship> sendShips = new ArrayList<>();
    int howShipsInDB;

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        howShipsInDB = ShipDAO.howMuchIsTheShips();

        if (Pagination.totalPages(howShipsInDB) > 1) {
            req.getSession().setAttribute("pagination", "yes");
            Pagination.setCurrentOffsetForPage("ships");
            req.getSession().setAttribute("currentPage", Pagination.currentPage);
            req.getSession().setAttribute("totalPages", Pagination.totalPages(howShipsInDB));
        } else {
            req.getSession().setAttribute("pagination", "no");
        }

        sendShips.clear();
        sendShips.addAll(ShipDAO.paginationShips(Pagination.orderBy, Pagination.orderByDirection, Pagination.limit, Pagination.offset));
        req.getSession().setAttribute("ships", sendShips);
        req.getSession().setAttribute("shipsCount", ShipDAO.howMuchIsTheShips());

        return "ships.jsp";
    }

}
