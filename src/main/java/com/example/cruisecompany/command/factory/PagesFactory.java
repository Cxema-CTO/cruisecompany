package com.example.cruisecompany.command.factory;

import com.example.cruisecompany.command.OpenPage;
import com.example.cruisecompany.command.CommandEnum;

import javax.servlet.http.HttpServletRequest;

public final class PagesFactory {

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
            openPage = CommandEnum.ERROR_404.getPage();
        }
        return openPage;
    }

}
