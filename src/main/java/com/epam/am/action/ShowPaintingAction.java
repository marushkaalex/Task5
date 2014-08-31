package com.epam.am.action;

import javax.servlet.http.HttpServletRequest;

public class ShowPaintingAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req) throws ActionException {
        System.out.println(req.getAttribute("painting"));
        return null;
    }
}
