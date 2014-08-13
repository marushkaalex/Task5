package com.epam.am.servlet;

import com.epam.am.action.ActionFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;

public class Controller extends javax.servlet.http.HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String url = req.getRequestURL().toString();
        URI uri = URI.create(url);
        System.out.println(uri.getHost());
        System.out.println(url);
        String redirect = "index.jsp";
        if (url.equals("http://localhost:8080/am/con/login")) {
            redirect = ActionFactory.getAction(ActionFactory.LOGIN_CHECK).execute(req);
            req.getRequestDispatcher(redirect).forward(req, resp);
        } else {
            super.service(req, resp);
        }
    }
}
