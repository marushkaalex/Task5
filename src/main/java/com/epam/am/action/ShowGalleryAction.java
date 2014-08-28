package com.epam.am.action;

import com.epam.am.dao.DaoException;
import com.epam.am.dao.DaoFactory;
import com.epam.am.dao.H2DaoFactory;
import com.epam.am.dao.PaintingDao;
import com.epam.am.entity.Gallery;
import com.epam.am.entity.Painting;
import com.epam.am.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowGalleryAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req) throws ActionException {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            return new ActionResult("login", true);
        }
        DaoFactory daoFactory = new H2DaoFactory();
        try {
            PaintingDao paintingDao = daoFactory.getPaintingDao();
            List<Painting> userLikes = paintingDao.getArtistsPaintings(user.getId());
            Gallery gallery = new Gallery(userLikes);
            req.setAttribute("gallery", gallery);
            return new ActionResult("gallery");
        } catch (DaoException e) {
            throw new ActionException(e);
        }
    }
}
