package com.epam.am.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ShowLoginPageAction implements Action {

    public static final String USERNAME = "username";
    private ActionResult login = new ActionResult("login");
    private ActionResult home = new ActionResult("home", true);

    @Override
    public ActionResult execute(HttpServletRequest req) {
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute(USERNAME);
        if (username != null) return home;
        return login;
    }
}
