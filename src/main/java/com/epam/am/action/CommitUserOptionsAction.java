package com.epam.am.action;

import javax.servlet.http.HttpServletRequest;

public class CommitUserOptionsAction implements Action {
    private ActionResult acc = new ActionResult("acc");

    @Override
    public ActionResult execute(HttpServletRequest req) {
        String lang = ((String) req.getParameter("lang"));
        System.out.println(lang);
        req.getSession().setAttribute("language", lang);
        //TODO set language
        return acc;
    }
}
