package com.example.cruisecompany.controller;

import com.example.cruisecompany.command.OpenPage;
import com.example.cruisecompany.command.factory.PagesFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String location = handleRequest(req, resp);
//        req.getRequestDispatcher(location).forward(req, resp);
        resp.sendRedirect(location);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String location = handleRequest(req, resp);
//        req.getRequestDispatcher(location).forward(req, resp);
        resp.sendRedirect(location);
    }

    private String handleRequest(HttpServletRequest req, HttpServletResponse resp) {
        OpenPage openPage = PagesFactory.getURL(req);
        return openPage.execute(req, resp);
    }

}
