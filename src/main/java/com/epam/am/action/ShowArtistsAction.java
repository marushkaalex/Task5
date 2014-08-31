package com.epam.am.action;

import com.epam.am.dao.DaoException;
import com.epam.am.dao.DaoFactory;
import com.epam.am.dao.H2DaoFactory;
import com.epam.am.dao.UserDao;
import com.epam.am.entity.User;

import javax.servlet.http.HttpServletRequest;

public class ShowArtistsAction implements Action {

    private static final ActionResult result = new ActionResult("artists");

    @Override
    public ActionResult execute(HttpServletRequest req) throws ActionException {
        DaoFactory daoFactory = new H2DaoFactory();
        try {
            UserDao userDao = daoFactory.getDaoManager().getUserDao();
            req.setAttribute("artists", userDao.getByRole(User.Role.ARTIST));
        } catch (DaoException e) {
            throw new ActionException(e);
        }
        return result;
    }
}
