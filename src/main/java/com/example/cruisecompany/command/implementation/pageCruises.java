package com.example.cruisecompany.command.implementation;

import com.example.cruisecompany.command.OpenPage;
import com.example.cruisecompany.controller.Pagination;
import com.example.cruisecompany.dao.CruiseDAO;
import com.example.cruisecompany.entity.Cruise;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class pageCruises implements OpenPage {

	List<Cruise> sendCruises = new ArrayList<>();
	int howCruisesInDB;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {

		howCruisesInDB = CruiseDAO.howMuchIsTheCruises();

		if (Pagination.totalPages(howCruisesInDB) > 1) {
			req.getSession().setAttribute("pagination", "yes");
			Pagination.setCurrentOffsetForPage("cruises");
			req.getSession().setAttribute("currentPage", Pagination.currentPage);
			req.getSession().setAttribute("totalPages", Pagination.totalPages(howCruisesInDB));
		} else {
			req.getSession().setAttribute("pagination", "no");
		}

		sendCruises.clear();
		sendCruises.addAll(CruiseDAO.paginationCruise(Pagination.orderBy, Pagination.orderByDirection, Pagination.limit, Pagination.offset));
		req.getSession().setAttribute("cruises", sendCruises);
		req.getSession().setAttribute("cruisesCount", CruiseDAO.howMuchIsTheCruises());

		return "cruises.jsp";
	}

}
