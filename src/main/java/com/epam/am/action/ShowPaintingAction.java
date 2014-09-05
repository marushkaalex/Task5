package com.epam.am.action;

import com.epam.am.dao.*;
import com.epam.am.entity.Gallery;
import com.epam.am.entity.Painting;
import com.epam.am.entity.User;

import javax.servlet.http.HttpServletRequest;

public class ShowPaintingAction implements Action {

    private static final String LIKED = "LIKED";
    private static final String NOT_LIKED = "LIKE";

    @Override
    public ActionResult execute(HttpServletRequest req) throws ActionException {
        long id = Long.parseLong(req.getParameter("p"));
        DaoFactory daoFactory = new H2DaoFactory();
        try (DaoManager daoManager = daoFactory.getDaoManager()) {
            PaintingDao paintingDao = daoManager.getPaintingDao();
            Painting painting = paintingDao.findById(id);
            if (painting == null) {
                throw new ActionException("painting not found");
            }
            boolean isLiked = false;
            User user = (User) req.getSession().getAttribute("user");
            if (user != null) {
                isLiked = paintingDao.isLiked(user.getId(), id);
            }
            Gallery gallery = new Gallery();
            gallery.add(painting);
            UserDao userDao = daoManager.getUserDao();
            User artist = userDao.find(painting.getArtistId());
            req.setAttribute("gallery", gallery);
            req.setAttribute("artist", artist);
            req.setAttribute("isLiked", isLiked ? LIKED : NOT_LIKED);
        } catch (DaoException e) {
            throw new ActionException(e);
        }
        return new ActionResult("painting");
    }
}
