package com.epam.am.action;

import com.epam.am.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ShowLoginPageAction implements Action {

    public static final String USER = "user";
    private ActionResult login = new ActionResult("login");
    private ActionResult home = new ActionResult("", true);

    @Override
    public ActionResult execute(HttpServletRequest req) {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(USER);
        if (user != null) return home;
        return login;
    }
}
