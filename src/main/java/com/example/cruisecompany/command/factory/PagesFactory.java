package com.example.cruisecompany.command.factory;

import com.example.cruisecompany.filter.AuthorizationFilter;
import org.apache.log4j.Logger;

import com.example.cruisecompany.command.OpenPage;
import com.example.cruisecompany.command.CommandEnum;

import javax.servlet.http.HttpServletRequest;

public final class PagesFactory {
    private static final Logger LOGGER = Logger.getLogger(AuthorizationFilter.class);

    private PagesFactory() {
    }

    public static OpenPage getURL(HttpServletRequest req) {
        String command = req.getParameter("open");
        OpenPage openPage = null;

        if (command != null) {
            try {
                openPage = CommandEnum.valueOf(command).getPage();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                openPage = CommandEnum.ERROR_404.getPage();
            }
        } else {
            LOGGER.error("404, bad command -" + command);
            openPage = CommandEnum.ERROR_404.getPage();
        }
        return openPage;
    }

}
