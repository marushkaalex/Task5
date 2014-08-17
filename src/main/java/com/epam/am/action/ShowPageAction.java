package com.epam.am.action;

import javax.servlet.http.HttpServletRequest;

public class ShowPageAction implements Action {

    private ActionResult actionResult;

    public ShowPageAction(String str) {
        actionResult = new ActionResult(str);
    }

    @Override
    public ActionResult execute(HttpServletRequest req) {
        return actionResult;
    }
}
