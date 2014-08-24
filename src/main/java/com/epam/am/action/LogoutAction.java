package com.epam.am.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.removeAttribute("username");
        session.removeAttribute("password");
        session.invalidate();
        return new ActionResult("", true);
    }
}
