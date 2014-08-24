package com.epam.am.action;

import javax.servlet.http.HttpServletRequest;

public class CommitUserOptionsAction implements Action {
    private ActionResult acc = new ActionResult("acc");

    @Override
    public ActionResult execute(HttpServletRequest req) {
        req.getAttribute("language");
        //TODO set language
        return acc;
    }
}
