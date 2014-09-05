package com.epam.am.action;

import com.epam.am.dao.*;
import com.epam.am.entity.User;

import javax.servlet.http.HttpServletRequest;

public class ShowArtistsAction implements Action {

    private static final ActionResult result = new ActionResult("artists");

    @Override
    public ActionResult execute(HttpServletRequest req) throws ActionException {
        DaoFactory daoFactory = new H2DaoFactory();
        try (DaoManager daoManager = daoFactory.getDaoManager()) {
            UserDao userDao = daoManager.getUserDao();
            req.setAttribute("artists", userDao.getByRole(User.Role.ARTIST));
        } catch (DaoException e) {
            throw new ActionException(e);
        }
        return result;
    }
}
