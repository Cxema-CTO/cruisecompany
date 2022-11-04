package com.example.cruisecompany.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface OpenPage {

    String execute(HttpServletRequest req, HttpServletResponse resp);
}
