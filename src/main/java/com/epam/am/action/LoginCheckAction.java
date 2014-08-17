package com.epam.am.action;

import com.epam.am.dao.UserDao;
import com.epam.am.entity.User;

import javax.servlet.http.HttpServletRequest;

public class LoginCheckAction implements Action {
    private final static String USERNAME = "username";
    private final static String PASSWORD = "password";
    private ActionResult home = new ActionResult("home", true);
    private ActionResult login = new ActionResult("login");

    @Override
    public ActionResult execute(HttpServletRequest req) {
        String username = req.getParameter(USERNAME);
        String password = req.getParameter(PASSWORD);

        UserDao userDao = new UserDao();
        User user = userDao.findByCredentials(username, password);
        user = new User();
        if (user == null) {
            return login;
        }

        req.getSession().setAttribute(USERNAME, username);
        return home;
    }

    private boolean isFound(String username, String password) {
        return false;
    }
}
