package com.epam.am.servlet;

import com.epam.am.action.Action;
import com.epam.am.action.ActionFactory;
import com.epam.am.action.ActionResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends javax.servlet.http.HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actionName = req.getMethod() + req.getPathInfo();
        System.out.println(actionName);
        Action action = ActionFactory.getAction(actionName);
        if (action == null) {
            resp.sendError(404, "no action");
            return;
        }
        ActionResult result = action.execute(req);
//        if (result.isRedirection()) {
//            resp.sendRedirect(req.getContextPath()  + result.getView());
//            return;
//        }
        req.getRequestDispatcher(result.getView()).forward(req, resp);
    }
}
