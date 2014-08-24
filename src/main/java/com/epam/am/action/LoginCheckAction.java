package com.epam.am.action;

import com.epam.am.dao.DaoFactory;
import com.epam.am.dao.H2DaoFactory;
import com.epam.am.dao.UserDao;
import com.epam.am.entity.User;
import com.epam.am.helper.HashCalculator;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class LoginCheckAction implements Action {
    private final static String USERNAME = "username";
    private final static String PASSWORD = "password";
    private ActionResult home = new ActionResult("", true);
    private ActionResult login = new ActionResult("login");

    @Override
    public ActionResult execute(HttpServletRequest req) {
        String username = req.getParameter(USERNAME);
        String password = req.getParameter(PASSWORD);

        password = HashCalculator.hash(password);

        DaoFactory daoFactory = new H2DaoFactory();
        UserDao userDao;
        User user = null;
        try {
            userDao = daoFactory.getUserDao();
            user = userDao.findByUsernameAndPassword(username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (user == null) {
            req.setAttribute("error", "wrong username or password"); //TODO with resource bundle
            return login;
        }

        req.getSession().setAttribute("user", user);
        req.getSession().setAttribute("language", "ru");
        return home;
    }

    private boolean isLengthInRange(String string) {
        return (string.length() > 3 && string.length() < 21);
    }
}
