package com.epam.am.servlet;

import com.epam.am.action.Action;
import com.epam.am.action.ActionException;
import com.epam.am.action.ActionFactory;
import com.epam.am.action.ActionResult;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends javax.servlet.http.HttpServlet {
    private static java.util.logging.Logger log = java.util.logging.Logger.getLogger("");

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPathInfo().contains("static")) {
            return;
        }
        String actionName = req.getMethod() + req.getPathInfo();
        System.out.println("Controller: actionName : " + actionName);
        Action action = ActionFactory.getAction(actionName);
        if (action == null) {
            resp.sendError(404, "No such action");
            return;
        }
        ActionResult result = null;
        try {
            result = action.execute(req);
        } catch (ActionException e) {
            e.printStackTrace();
        }

        if (result.isRedirection()) {
            resp.sendRedirect(req.getContextPath() + "/" + result.getView());
            return;
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/" + result.getView() + ".jsp");
        requestDispatcher.forward(req, resp);
    }
}
