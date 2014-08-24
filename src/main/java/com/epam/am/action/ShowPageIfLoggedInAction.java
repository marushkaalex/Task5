package com.epam.am.action;

import javax.servlet.http.HttpServletRequest;

public class ShowPageIfLoggedInAction implements Action {

    private String loggedIn;
    private String notLoggedIn;
    private boolean isRedirect1;
    private boolean isRedirect2;

    public ShowPageIfLoggedInAction(String loggedIn, boolean isRedirect1, String notLoggedIn, boolean isRedirect2) {
        this.loggedIn = loggedIn;
        this.notLoggedIn = notLoggedIn;
        this.isRedirect1 = isRedirect1;
        this.isRedirect2 = isRedirect2;
    }

    @Override
    public ActionResult execute(HttpServletRequest req) {
        if (req.getSession().getAttribute("user") != null) {
            return new ActionResult(loggedIn, isRedirect1);
        } else {
            return new ActionResult(notLoggedIn, isRedirect2);
        }
    }
}
