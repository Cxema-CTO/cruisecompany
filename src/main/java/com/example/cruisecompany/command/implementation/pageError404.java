package com.example.cruisecompany.command.implementation;

import com.example.cruisecompany.command.OpenPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class pageError404 implements OpenPage {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		return "404.jsp";
	}

}
