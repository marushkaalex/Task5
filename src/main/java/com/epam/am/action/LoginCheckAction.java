package com.epam.am.action;

import javax.servlet.http.HttpServletRequest;

public class LoginCheckAction implements Action {
    private final static String USERNAME = "username";
    private final static String PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest req) {
        String username = req.getParameter(USERNAME);
        String password = req.getParameter(PASSWORD);
        req.getSession().setAttribute(USERNAME, username);
        return "/home.jsp";
    }

    private boolean isFound(String username, String password) {
        return false;
    }
}
