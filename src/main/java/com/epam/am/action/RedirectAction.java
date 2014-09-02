package com.epam.am.action;

import javax.servlet.http.HttpServletRequest;

public class RedirectAction implements Action {
    private String link;

    public RedirectAction(String link) {
        this.link = link;
    }

    @Override
    public ActionResult execute(HttpServletRequest req) throws ActionException {
        return new ActionResult(link, true);
    }
}
