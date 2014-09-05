package com.epam.am.action;

import com.epam.am.dao.*;
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
        try (DaoManager daoManager = daoFactory.getDaoManager()) {
            PaintingDao paintingDao = daoManager.getPaintingDao();
            List<Painting> artistsPaintings = paintingDao.getArtistsPaintings(user.getId());
            Gallery gallery = new Gallery(artistsPaintings);
            req.setAttribute("gallery", gallery);
            req.setAttribute("upload", true);
            return new ActionResult("gallery");
        } catch (DaoException e) {
            throw new ActionException(e);
        }
    }
}
