package com.epam.am.action;

import javax.servlet.http.HttpServletRequest;

public interface Action {
    String execute(HttpServletRequest req);
}
