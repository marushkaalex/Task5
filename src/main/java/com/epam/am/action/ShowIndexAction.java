package com.epam.am.action;

import com.epam.am.dao.*;
import com.epam.am.entity.Gallery;
import com.epam.am.entity.Painting;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowIndexAction implements Action {

    private static final int MAX_PAINTINGS = 12;
    private ActionResult result = new ActionResult("index");

    @Override
    public ActionResult execute(HttpServletRequest req) throws ActionException {
        DaoFactory daoFactory = new H2DaoFactory();
        try (DaoManager daoManager = daoFactory.getDaoManager()) {
            PaintingDao paintingDao = daoManager.getPaintingDao();
            List<Painting> artistsPaintings = paintingDao.getPaintingList(MAX_PAINTINGS);
            Gallery gallery = new Gallery(artistsPaintings);
            req.setAttribute("recentlyAdded", gallery);
            return result;
        } catch (DaoException e) {
            throw new ActionException(e);
        }
    }
}
