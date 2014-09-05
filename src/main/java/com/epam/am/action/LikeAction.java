package com.epam.am.action;

import com.epam.am.dao.*;
import com.epam.am.entity.User;

import javax.servlet.http.HttpServletRequest;

public class LikeAction implements Action {

    @Override
    public ActionResult execute(HttpServletRequest req) throws ActionException {
        long paintingId = Long.parseLong(req.getParameter("like"));
        DaoFactory daoFactory = new H2DaoFactory();
        try (DaoManager daoManager = daoFactory.getDaoManager()) {
            User user = (User) req.getSession().getAttribute("user");
            if (user == null) {
                return new ActionResult("login");
            }
            PaintingDao paintingDao = daoManager.getPaintingDao();
            if (paintingDao.isLiked(user.getId(), paintingId)) {
                paintingDao.removeLike(user.getId(), paintingId);
            } else {
                paintingDao.addLike(user.getId(), paintingId);
            }
        } catch (DaoException e) {
            throw new ActionException(e);
        }
        return new ActionResult("painting?p=" + paintingId, true);
    }
}
