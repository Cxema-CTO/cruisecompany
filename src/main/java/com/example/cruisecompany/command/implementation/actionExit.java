package com.example.cruisecompany.command.implementation;

import com.example.cruisecompany.command.OpenPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class actionExit implements OpenPage {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute("role", "guest");
        return "index.jsp";
    }
}
