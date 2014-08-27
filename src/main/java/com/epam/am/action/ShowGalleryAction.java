package com.epam.am.action;

import com.epam.am.entity.Gallery;

import javax.servlet.http.HttpServletRequest;

public class ShowGalleryAction implements Action {

    private ActionResult result = new ActionResult("gallery");

    @Override
    public ActionResult execute(HttpServletRequest req) throws ActionException {
        Gallery gallery = new Gallery();

        return result;
    }
}
