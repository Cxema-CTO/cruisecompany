package com.example.cruisecompany.command.implementation;

import com.example.cruisecompany.command.OpenPage;
import com.example.cruisecompany.dao.ShipDAO;
import com.example.cruisecompany.entity.Ship;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class pageAddCruise implements OpenPage {
	List<Ship> sendShips = new ArrayList<>();
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {

		sendShips = ShipDAO.getAllShipsFromDB();
		req.getSession().setAttribute("ships", sendShips);

		return "add_cruise.jsp";
	}

}
