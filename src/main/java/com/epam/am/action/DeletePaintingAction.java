package com.epam.am.action;

import com.epam.am.dao.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

public class DeletePaintingAction implements Action {

    private static final Logger LOG = LoggerFactory.getLogger(DeletePaintingAction.class);

    @Override
    public ActionResult execute(HttpServletRequest req) throws ActionException {
        Long id = Long.valueOf(req.getParameter("id"));
        String path = req.getParameter("path");
        LOG.debug("painting id: {}", id);
        LOG.debug("painting path: {}", path);
        DaoFactory daoFactory = new H2DaoFactory();
        DaoManager daoManager = null;
        try {
            daoManager = daoFactory.getDaoManager();
            daoManager.openTransaction();
            PaintingDao paintingDao = daoManager.getPaintingDao();
            paintingDao.removeLikes(id);
            paintingDao.removeByID(id);
            daoManager.commit();
        } catch (DaoException e) {
            if (daoManager != null) {
                try {
                    daoManager.rollBack();
                } catch (DaoException e1) {
                    throw new ActionException(e1);
                }
            }
            throw new ActionException(e);
        } finally {
            if (daoManager != null) {
                try {
                    daoManager.closeTransaction();
                    daoManager.close();
                } catch (DaoException e) {
                    throw new ActionException(e);
                }
            }
        }
        File file = new File(path);
        file.delete();
        return new ActionResult("gallery", true);
    }
}
