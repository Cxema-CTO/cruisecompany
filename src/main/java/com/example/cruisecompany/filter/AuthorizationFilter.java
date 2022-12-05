package com.example.cruisecompany.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthorizationFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(AuthorizationFilter.class);

    boolean access = true;
    String role;
    int roleBitMask, accessBitMask;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        if (getAccess(session, request.getRequestURI())) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect("403.jsp");
        }
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }

    private boolean getAccess(HttpSession session, String requestURI) {

        role = (String) session.getAttribute("role");
        if (role != null) {
            switch (role) {
                case "admin":
                    roleBitMask = 0b00000001;
                    break;
                case "user":
                    roleBitMask = 0b00000010;
                    break;
                case "guest":
                    roleBitMask = 0b00000100;
                    break;
                case "banned":
                    roleBitMask = 0b00001000;
                    break;
            }
        } else {
            roleBitMask = 0b00000100;// for guest
        }


        if (AccessMap.bitMask.get(requestURI) == null) {
            LOGGER.error("Don't have - \"" + requestURI + "\" in AccessMap");
            accessBitMask = 0b11111111;// for redirect 404 error page
        } else {
            accessBitMask = AccessMap.bitMask.get(requestURI);
        }

        if ((roleBitMask & accessBitMask) == roleBitMask) {
            access = true;
        } else {
            access = false;
            LOGGER.error("Access denied for user with role - \"" + role + "\", it want to open URL - " + requestURI);
        }

        return access;
    }
}
