package com.example.cruisecompany.command.implementation;

import com.example.cruisecompany.command.OpenPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class pageAddShip implements OpenPage {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		return "add_ship.jsp";
	}

}
