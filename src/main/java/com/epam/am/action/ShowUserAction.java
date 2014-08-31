package com.epam.am.action;

import com.epam.am.dao.*;
import com.epam.am.entity.Gallery;
import com.epam.am.entity.User;

import javax.servlet.http.HttpServletRequest;

public class ShowUserAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req) throws ActionException {
        DaoFactory daoFactory = new H2DaoFactory();
        String path = req.getPathInfo();
        String username = path.substring("/user/".length());
        System.out.println("SUA path: " + path);
        try {
            DaoManager daoManager = daoFactory.getDaoManager();
            UserDao userDao = daoManager.getUserDao();
            User user = userDao.findByUsername(username);
            if (user == null) {
                return new ActionResult("user/404", false);
            }
            if (user.equals(req.getSession().getAttribute("user"))) {
                return new ActionResult("home");
            }
            req.setAttribute("shownUser", user);
            PaintingDao paintingDao = daoManager.getPaintingDao();
            Gallery likes = new Gallery(paintingDao.getUserLikes(user.getId()));
            req.setAttribute("likes", likes);
            if (user.getRole() == User.Role.ARTIST) {
                Gallery gallery = new Gallery(paintingDao.getArtistsPaintings(user.getId()));
                req.setAttribute("gallery", gallery);
            }
        } catch (DaoException e) {
            throw new ActionException(e);
        }
        return new ActionResult("user");
    }
}
