package com.epam.am.action;

import com.epam.am.dao.DaoException;
import com.epam.am.dao.DaoFactory;
import com.epam.am.dao.H2DaoFactory;
import com.epam.am.dao.UserDao;
import com.epam.am.entity.User;
import com.epam.am.util.HashCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class LoginCheckAction implements Action {
    private final static String USERNAME = "username";
    private final static String PASSWORD = "password";
    private static final Logger LOG = LoggerFactory.getLogger(LoginCheckAction.class);
    //    private ActionResult home = new ActionResult("", true);
    private ActionResult login = new ActionResult("login");

    @Override
    public ActionResult execute(HttpServletRequest req) throws ActionException {
        String username = req.getParameter(USERNAME);
        String password = req.getParameter(PASSWORD);

        password = HashCalculator.hash(password);

        DaoFactory daoFactory = new H2DaoFactory();
        UserDao userDao;
        User user = null;
        try {
            userDao = daoFactory.getDaoManager().getUserDao();
            user = userDao.findByUsernameAndPassword(username, password);
        } catch (DaoException e) {
            throw new ActionException("Exception while finding user", e);
        }
        if (user == null) {
            req.setAttribute("error", "wrong username or password"); //TODO with resource bundle
            return login;
        }

        req.getSession().setAttribute("user", user);
        req.getSession().setAttribute("lang", "en");
        return new ActionResult("user/" + user.getUsername(), true);
    }
}
