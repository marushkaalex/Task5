package com.epam.am.action;

import javax.servlet.http.HttpServletRequest;

public class ShowLoginPageAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req) {
        return new ActionResult("/loginForm.jsp", true);
    }
}
