package com.epam.am.action;

import javax.servlet.http.HttpServletRequest;

public class ShowPageAction implements Action {

    String view;

    public ShowPageAction(String str) {
        view = str;
    }

    @Override
    public ActionResult execute(HttpServletRequest req) {
        return new ActionResult(view);
    }
}
