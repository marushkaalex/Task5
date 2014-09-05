package com.epam.am.action;

import com.epam.am.dao.*;
import com.epam.am.entity.Gallery;
import com.epam.am.entity.Painting;
import com.epam.am.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ShowTopAction implements Action {
    private static final int MAX_PAINTINGS = 50;
    private ActionResult result = new ActionResult("top");

    @Override
    public ActionResult execute(HttpServletRequest req) throws ActionException {
        DaoFactory daoFactory = new H2DaoFactory();
        try (DaoManager daoManager = daoFactory.getDaoManager()) {
            PaintingDao paintingDao = daoManager.getPaintingDao();
            UserDao userDao = daoManager.getUserDao();
            List<Painting> artistsPaintings = paintingDao.getTop(MAX_PAINTINGS);
            Gallery gallery = new Gallery(artistsPaintings);
            Map<Map.Entry<Painting, String>, User> signedPaintings = new LinkedHashMap<>();
            for (Map.Entry<Painting, String> paintingStringEntry : gallery.getLinks().entrySet()) {
                signedPaintings.put(paintingStringEntry,
                        userDao.find(paintingStringEntry.getKey().getId()));
            }
            req.setAttribute("signedPaintings", signedPaintings);
            return result;
        } catch (DaoException e) {
            throw new ActionException(e);
        }
    }
}
