package com.epam.am.action;

import javax.servlet.http.HttpServletRequest;

public class DeletePaintingAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req) throws ActionException {
        String id = req.getParameter("id");
        String path = req.getParameter("path");
        System.out.println(id);
        System.out.println(path);
        return new ActionResult("gallery");
    }
}
