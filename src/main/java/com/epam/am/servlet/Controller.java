package com.epam.am.servlet;

import com.epam.am.action.Action;
import com.epam.am.action.ActionException;
import com.epam.am.action.ActionFactory;
import com.epam.am.action.ActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends javax.servlet.http.HttpServlet {
    private static Logger LOG = LoggerFactory.getLogger(Controller.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Action action = getAction(req);
        if (action == null) {
            resp.sendError(404, "No such action");
            return;
        }
        ActionResult result = null;
        try {
            result = action.execute(req);
        } catch (ActionException e) {
            resp.sendError(505, e.getMessage());
            return;
        }

        if (result.isRedirection()) {
            resp.sendRedirect(req.getContextPath() + "/" + result.getView());
            return;
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/jsp/" + result.getView() + ".jsp");
        requestDispatcher.forward(req, resp);
    }

    private Action getAction(HttpServletRequest req) {
        String path = req.getPathInfo();
        if (req.getPathInfo().contains("/user/")) {
            path = path.substring(0, path.lastIndexOf("/user") + "/user".length());
        }
        String actionName = req.getMethod() + path;
        LOG.debug("Controller: actionName : " + actionName);
        return ActionFactory.getAction(actionName);
    }
}
