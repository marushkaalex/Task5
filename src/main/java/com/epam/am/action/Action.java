package com.epam.am.action;

import javax.servlet.http.HttpServletRequest;

public interface Action {
    ActionResult execute(HttpServletRequest req) throws ActionException;

}
