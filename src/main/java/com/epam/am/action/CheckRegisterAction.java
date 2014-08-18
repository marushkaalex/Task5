package com.epam.am.action;

import javax.servlet.http.HttpServletRequest;

public class CheckRegisterAction implements Action {

    //TODO something

    private final static String USERNAME = "username";
    private final static String EMAIL = "email";
    private final static String PASSWORD = "password";

    private ActionResult register = new ActionResult("register", true);
    private ActionResult home = new ActionResult("home");

    @Override
    public ActionResult execute(HttpServletRequest req) {
        String username = req.getParameter(USERNAME);
        String email = req.getParameter(EMAIL);
        String password = req.getParameter(PASSWORD);
        return null;
    }
}
